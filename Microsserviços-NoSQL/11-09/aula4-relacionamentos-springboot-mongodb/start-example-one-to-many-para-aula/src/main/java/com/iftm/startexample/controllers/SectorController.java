package com.iftm.startexample.controllers;

import com.iftm.startexample.models.dtos.EmployeeDTO;
import com.iftm.startexample.models.dtos.SectorDTO;
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
    public ResponseEntity<SectorDTO> findById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<SectorDTO> create(@RequestBody SectorDTO sectorDTO) {
        return service.save(sectorDTO);
    }

    @PutMapping
    public ResponseEntity<SectorDTO> update(@RequestBody SectorDTO sectorDTO) {
        return service.update(sectorDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        return service.delete(id);
    }
}
