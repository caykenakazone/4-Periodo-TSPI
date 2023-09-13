package com.iftm.startexample.models.dtos;

import com.iftm.startexample.models.Employee;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String level;
    private double wage;

    public EmployeeDTO() { }
}
