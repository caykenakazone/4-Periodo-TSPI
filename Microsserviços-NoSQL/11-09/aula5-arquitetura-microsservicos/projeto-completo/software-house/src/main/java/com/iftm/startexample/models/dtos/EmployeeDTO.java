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

    public EmployeeDTO(String firstName, String lastName, String level, double wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.wage = wage;
    }

    public EmployeeDTO(Employee employee) {
        if(employee.getId() != null)
            this.id = employee.getId().toString();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.level = employee.getLevel();
        this.wage = employee.getWage();
    }

    public Employee toEmployee() {
        ObjectId id = null;
        if(this.id != null)
            id = new ObjectId(this.id);

        return new Employee(id,
                this.firstName,
                this.lastName,
                this.level,
                this.wage);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
