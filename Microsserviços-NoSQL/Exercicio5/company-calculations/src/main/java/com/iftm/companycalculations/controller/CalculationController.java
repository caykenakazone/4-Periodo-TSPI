package com.iftm.companycalculations.controller;

import com.iftm.companycalculations.models.dtos.AnnualWageDTO;
import com.iftm.companycalculations.models.dtos.EmployeeDTO;
import com.iftm.companycalculations.service.CalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CalculationController {

    @Autowired
    private CalculationsService service;

    @GetMapping("/response")
    public ResponseEntity<AnnualWageDTO> getResponse(@RequestBody EmployeeDTO employee) {
        return service.calculateAnnualWage(employee);
    }
}
