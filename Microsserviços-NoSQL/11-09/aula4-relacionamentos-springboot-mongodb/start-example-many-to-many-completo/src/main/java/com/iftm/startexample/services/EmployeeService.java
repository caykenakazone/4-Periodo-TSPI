package com.iftm.startexample.services;

import com.iftm.startexample.models.Employee;
import com.iftm.startexample.models.dto.EmployeeDTO;
import com.iftm.startexample.models.dto.SectorDTO;
import com.iftm.startexample.repositories.EmployeeRepository;
import com.iftm.startexample.repositories.SectorRepository;
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

    public ResponseEntity<List<EmployeeDTO>> findAll() {
        var dbEmployees = repository.findAll();
        if(dbEmployees.isEmpty())
            return ResponseEntity.notFound().build();
        var dbEmplyeesDTO = dbEmployees.stream().map(employee -> {
            return new EmployeeDTO(employee);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dbEmplyeesDTO);
    }

    public ResponseEntity<EmployeeDTO> findById(String id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        var dbEmployee = repository.findById(id);
        if(dbEmployee.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EmployeeDTO(dbEmployee.get()));
    }

    public ResponseEntity<List<EmployeeDTO>> findEmployeeBySectorId(String sectorId) {
        if(sectorId == null)
            return ResponseEntity.badRequest().build();
        var dbEmployess = repository.findEmployeesBySectorId(sectorId);
        if(dbEmployess.isEmpty())
            return ResponseEntity.notFound().build();
        var dbEmployessDTO = dbEmployess.get().stream().map(employees -> {
            return new EmployeeDTO(employees);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dbEmployessDTO);
    }

    public ResponseEntity<EmployeeDTO> save(EmployeeDTO employee) {
        // validar employee
        if(employee.getFirstName().isBlank() 
                || employee.getLastName().isBlank() 
                || employee.getLevel().isBlank())
            return ResponseEntity.badRequest().build();
        var dbEmployee = repository.save(employee.toEmployee());
        return ResponseEntity.ok(new EmployeeDTO(dbEmployee));
    }

    public ResponseEntity<EmployeeDTO> update(Employee employee) {
        // validar employee
        if(employee.getId() == null)
            return ResponseEntity.badRequest().build();

        var dbEmployee = repository.findById(employee.getId());
        if(dbEmployee.isEmpty())
            return ResponseEntity.notFound().build();
        // atualizar
        var dbEmployeeObj = dbEmployee.get();
        dbEmployeeObj.setFirstName(employee.getFirstName());
        dbEmployeeObj.setLastName(employee.getLastName());
        dbEmployeeObj.setLevel(employee.getLevel());
        dbEmployeeObj.setWage(employee.getWage());
        return ResponseEntity.ok(new EmployeeDTO(repository.save(dbEmployeeObj)));
    }

    //@Transactional
    public ResponseEntity<?> delete(String id) {
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
}
