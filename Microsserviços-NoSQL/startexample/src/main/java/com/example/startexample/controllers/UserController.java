package com.example.startexample.controllers;

import com.example.startexample.models.User;
import com.example.startexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService service;
    public ResponseEntity<List<User>> findAll(){
        return service.findAll();
    }
}
