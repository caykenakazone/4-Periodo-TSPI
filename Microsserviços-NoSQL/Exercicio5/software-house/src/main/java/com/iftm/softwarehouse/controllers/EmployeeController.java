package com.iftm.softwarehouse.controllers;

import com.iftm.softwarehouse.models.dtos.AnnualWageDTO;
import com.iftm.softwarehouse.models.dtos.EmployeeDTO;
import com.iftm.softwarehouse.services.EmployeeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Value("${eureka.instance.instance-id}")
    private String instaceId;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @GetMapping("/wage-above/{value}")
    public ResponseEntity<List<EmployeeDTO>> findById(@PathVariable("value") double value) {
        return service.findEmployeesWithWageAboveValue(value);
    }

    @GetMapping("/instance")
    public String getInstanceId() {
        return instaceId;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) {
        return service.save(employeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeDTO employeeDTO) {
        return service.update(employeeDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        return service.delete(id);
    }

    @GetMapping("annual-wage/{employee-id}")
    public ResponseEntity<AnnualWageDTO> calculateAnnualWage(@PathVariable String id) {
        return service.getAnnualWage(id);
    }
}
