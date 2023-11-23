package com.iftm.softwarehouse.services;


import com.iftm.softwarehouse.feign.FeignConsume;
import com.iftm.softwarehouse.mensages.RabbitMqSendLog;
import com.iftm.softwarehouse.models.dtos.AnnualWageDTO;
import com.iftm.softwarehouse.models.dtos.EmployeeDTO;
import com.iftm.softwarehouse.models.dtos.LogDTO;
import com.iftm.softwarehouse.repositories.EmployeeRepository;
import com.iftm.softwarehouse.repositories.SectorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private RabbitMqSendLog rabbitMqSendLog;

    @Autowired
    private FeignConsume feignConsume;

    public ResponseEntity<List<EmployeeDTO>> findAll() {
        var dbEmployees = repository.findAll();
        if(dbEmployees.isEmpty())
            return ResponseEntity.notFound().build();

        var employeeDtos = dbEmployees.stream().map(employee -> {
            return new EmployeeDTO(employee);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(employeeDtos);
    }

    public ResponseEntity<EmployeeDTO> findById(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        var dbEmployee = repository.findById(id);
        if(dbEmployee.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EmployeeDTO(dbEmployee.get()));
    }

    public ResponseEntity<List<EmployeeDTO>> findEmployeesWithWageAboveValue(double value) {
        var dbEmployees = repository.findEmployeesWithWageAboveValue(value);
        if(dbEmployees.isEmpty())
            return ResponseEntity.notFound().build();

        var employeeDtos = dbEmployees.get().stream().map(employee -> {
            return new EmployeeDTO(employee);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(employeeDtos);
    }

    public ResponseEntity<EmployeeDTO> save(EmployeeDTO employeeDTO) {
        // validar employeeDTO
        if(employeeDTO.getFirstName().isBlank() 
                || employeeDTO.getLastName().isBlank() 
                || employeeDTO.getLevel().isBlank())
            return ResponseEntity.badRequest().build();
        var dbEmployee = repository.save(employeeDTO.toEmployee());

        rabbitMqSendLog.sendLog(new LogDTO<EmployeeDTO>("created", new EmployeeDTO(dbEmployee)));

        return ResponseEntity.ok(new EmployeeDTO(dbEmployee));
    }

    public ResponseEntity<EmployeeDTO> update(EmployeeDTO employeeDTO) {
        // validar employeeDTO
        if(employeeDTO.getId() == null)
            return ResponseEntity.badRequest().build();

        var objectId = new ObjectId(employeeDTO.getId());
        var dbEmployee = repository.findById(objectId);
        if(dbEmployee.isEmpty())
            return ResponseEntity.notFound().build();
        // atualizar
        var dbEmployeeObj = dbEmployee.get();
        dbEmployeeObj.setFirstName(employeeDTO.getFirstName());
        dbEmployeeObj.setLastName(employeeDTO.getLastName());
        dbEmployeeObj.setLevel(employeeDTO.getLevel());
        dbEmployeeObj.setWage(employeeDTO.getWage());
        return ResponseEntity.ok(new EmployeeDTO(repository.save(dbEmployeeObj)));
    }

    //@Transactional
    public ResponseEntity<?> delete(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        var sector = sectorRepository.findSectorByEmployeeId(id);

        repository.deleteById(id);

        var dbEmployee = repository.findById(id);
        if(dbEmployee.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

        if(!sector.isEmpty()) {
            sector.get().getEmployees().removeIf(employee -> employee.getId().toString().equals(id.toString()));
            sectorRepository.save(sector.get());
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AnnualWageDTO> getAnnualWage(String id) {
        var employee = repository.findById(new ObjectId(id));

        if (!employee.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var response = feignConsume.getResponse(new EmployeeDTO(employee.get()));

        if(response.getBody() == null)
            ResponseEntity.internalServerError();

        return response;
    }
}
