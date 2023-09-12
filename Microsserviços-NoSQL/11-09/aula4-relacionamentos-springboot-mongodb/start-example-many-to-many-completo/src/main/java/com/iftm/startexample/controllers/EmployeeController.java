package com.iftm.startexample.controllers;

import com.iftm.startexample.models.Employee;
import com.iftm.startexample.models.dto.EmployeeDTO;
import com.iftm.startexample.services.EmployeeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/sector/id/{id}")
    public ResponseEntity<List<EmployeeDTO>> findEmployeeBySectorId(@PathVariable("id") String id) {
        return service.findEmployeeBySectorId(id);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) {
        return service.save(employeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> update(@RequestBody Employee employeeDTO) {
        return service.update(employeeDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
