package com.iftm.startexample.services;

import com.iftm.startexample.models.Employee;
import com.iftm.startexample.models.Sector;
import com.iftm.startexample.models.dto.EmployeeDTO;
import com.iftm.startexample.models.dto.SectorDTO;
import com.iftm.startexample.repositories.EmployeeRepository;
import com.iftm.startexample.repositories.SectorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService {

    @Autowired
    private SectorRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<List<SectorDTO>> findAll() {
        var dbSectors = repository.findAll();
        if(dbSectors.isEmpty())
            return ResponseEntity.notFound().build();
        var dbSectorsDTO = dbSectors.stream().map(sector -> {
            return new SectorDTO(sector);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dbSectorsDTO);
    }

    public ResponseEntity<SectorDTO> findById(String id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        var dbSector = repository.findById(id);
        if(dbSector.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new SectorDTO(dbSector.get()));
    }

    public ResponseEntity<SectorDTO> findSectorByEmployeeId(String employeeId) {
        if(employeeId == null)
            return ResponseEntity.badRequest().build();
        var dbSector = repository.findSectorByEmployeeId(employeeId);
        if(dbSector.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new SectorDTO(dbSector.get()));
    }

    public ResponseEntity<SectorDTO> save(SectorDTO sector) {
        //validar sector
        if(sector.getName().isBlank())
            return ResponseEntity.badRequest().build();
        var dbSector = repository.save(sector.toSector());
        return ResponseEntity.ok(new SectorDTO(dbSector));
    }

    public ResponseEntity<Sector> update(Sector sector) {
        // validar sector
        if(sector.getId() == null)
            return ResponseEntity.badRequest().build();

        var dbSector = repository.findById(sector.getId());
        if(dbSector.isEmpty())
            return ResponseEntity.notFound().build();
        // atualizar
        var dbSectorObj = dbSector.get();
        dbSectorObj.setName(sector.getName());
        dbSectorObj.setDescription(sector.getDescription());
        dbSectorObj.setEmployees(sector.getEmployees());
        return ResponseEntity.ok(repository.save(dbSectorObj));
    }

    public ResponseEntity<SectorDTO> addEmployeeInSector(String sectorId, EmployeeDTO employee) {
        if(sectorId == null || employee.getId() == null)
            return ResponseEntity.badRequest().build();
        var dbSector = repository.findById(sectorId);
        var dbEmployee = employeeRepository.findById(employee.getId());
        if(dbSector.isEmpty() || dbEmployee.isEmpty())
            return ResponseEntity.notFound().build();

        var curSector = dbSector.get();
        List<Employee> employees;
        if(curSector.getEmployees() == null) {
            employees = new ArrayList<Employee>();
            curSector.setEmployees(employees);
        }

        List<Sector> sectors;
        if(dbEmployee.get().getSectors() == null) {
            sectors = new ArrayList<Sector>();
            dbEmployee.get().setSectors(sectors);
        }

        var newSector = new Sector();
        newSector.setId(sectorId);
        dbEmployee.get().getSectors().add(newSector);
        employeeRepository.save(dbEmployee.get());

        curSector.getEmployees().add(dbEmployee.get());
        return ResponseEntity.ok(new SectorDTO(repository.save(curSector)));
    }

    public ResponseEntity<?> delete(String id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        repository.deleteById(id);

        var dbSector = repository.findById(id);
        if(dbSector.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.ok().build();
    }
}
