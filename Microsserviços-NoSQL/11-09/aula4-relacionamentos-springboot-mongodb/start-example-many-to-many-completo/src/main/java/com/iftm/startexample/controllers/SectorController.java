package com.iftm.startexample.controllers;

import com.iftm.startexample.models.Employee;
import com.iftm.startexample.models.Sector;
import com.iftm.startexample.models.dto.EmployeeDTO;
import com.iftm.startexample.models.dto.SectorDTO;
import com.iftm.startexample.services.SectorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sectors")
public class SectorController {

    @Autowired
    private SectorService service;

    @GetMapping
    public ResponseEntity<List<SectorDTO>> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SectorDTO> findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/employee/id/{id}")
    public ResponseEntity<SectorDTO> findByEmployeeId(@PathVariable("id") String id) {
        return service.findSectorByEmployeeId(id);
    }

    @PostMapping
    public ResponseEntity<SectorDTO> create(@RequestBody SectorDTO sectorDTO) {
        return service.save(sectorDTO);
    }

    @PostMapping("/sector/{id}/add-employee")
    public ResponseEntity<SectorDTO> addEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) {
        return service.addEmployeeInSector(id, employeeDTO);
    }

    @PutMapping
    public ResponseEntity<Sector> update(@RequestBody Sector sector) {
        return service.update(sector);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
