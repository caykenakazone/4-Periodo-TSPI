package com.iftm.log.controllers;

import com.iftm.log.models.Log;
import com.iftm.log.models.dtos.LogDTO;
import com.iftm.log.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    @Autowired
    private LogService service;

    @GetMapping
    public ResponseEntity<List<LogDTO>> findAll() {
        return service.findAll();
    }

}


