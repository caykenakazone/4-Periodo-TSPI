package com.iftm.ex1.controllers;

import com.iftm.ex1.models.Developer;
import com.iftm.ex1.models.dtos.DeveloperDTO;
import com.iftm.ex1.services.DeveloperService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/developers")
public class DeveloperController {
    @Autowired
    private DeveloperService service;

    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> findAll(){
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DeveloperDTO> findById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<DeveloperDTO> create(@RequestBody Developer developer) {
        return service.save(developer);
    }

    @PutMapping
    public ResponseEntity<DeveloperDTO> update(@RequestBody DeveloperDTO developerDTO) {
        return service.update(developerDTO);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        return service.delete(id);
    }
}
