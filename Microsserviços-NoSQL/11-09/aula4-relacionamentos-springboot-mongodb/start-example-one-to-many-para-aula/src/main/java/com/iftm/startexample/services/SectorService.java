package com.iftm.startexample.services;

import com.iftm.startexample.models.Employee;
import com.iftm.startexample.models.dtos.EmployeeDTO;
import com.iftm.startexample.models.dtos.SectorDTO;
import com.iftm.startexample.repositories.EmployeeRepository;
import com.iftm.startexample.repositories.SectorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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

        var sectorDtos = dbSectors.stream().map(sectorDTO -> {
            return new SectorDTO(sectorDTO);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(sectorDtos);
    }

    public ResponseEntity<SectorDTO> findById(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        var dbSector = repository.findById(id);
        if(dbSector.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new SectorDTO(dbSector.get()));
    }

    public ResponseEntity<SectorDTO> findSectorByEmployeeId(ObjectId employeeId) {
        return null;
    }

    public ResponseEntity<SectorDTO> save(SectorDTO sectorDTO) {
        //validar sectorDTO
        if(sectorDTO.getName().isBlank())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new SectorDTO(repository.save(sectorDTO.toSector())));
    }

    public ResponseEntity<SectorDTO> update(SectorDTO sectorDTO) {
        // validar sectorDTO
        if(sectorDTO.getId() == null)
            return ResponseEntity.badRequest().build();

        var objectId = new ObjectId(sectorDTO.getId());
        var dbSector = repository.findById(objectId);
        if(dbSector.isEmpty())
            return ResponseEntity.notFound().build();
        // atualizar
        var dbSectorObj = dbSector.get();
        dbSectorObj.setName(sectorDTO.getName());
        dbSectorObj.setDescription(sectorDTO.getDescription());
        dbSectorObj.setEmployees(sectorDTO.getEmployees().stream().map(employeeDTO -> {
            return employeeDTO.toEmployee();
        }).collect(Collectors.toList()));
        return ResponseEntity.ok(new SectorDTO(repository.save(dbSectorObj)));
    }

    public ResponseEntity<?> delete(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        repository.deleteById(id);

        var dbSector = repository.findById(id);
        if(dbSector.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.ok().build();
    }
}
