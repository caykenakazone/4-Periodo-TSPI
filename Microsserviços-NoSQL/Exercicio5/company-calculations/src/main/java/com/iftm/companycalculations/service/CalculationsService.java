package com.iftm.companycalculations.service;

import com.iftm.companycalculations.models.dtos.AnnualWageDTO;
import com.iftm.companycalculations.models.dtos.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CalculationsService {


    public ResponseEntity<AnnualWageDTO> calculateAnnualWage(EmployeeDTO employee) {
        return ResponseEntity.ok(new AnnualWageDTO(employee.getFirstName(), employee.getWage()*12));
    }
}