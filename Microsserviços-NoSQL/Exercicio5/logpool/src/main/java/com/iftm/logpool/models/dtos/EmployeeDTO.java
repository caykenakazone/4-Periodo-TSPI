package com.iftm.logpool.models.dtos;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeDTO implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String level;
    private double wage;

    public EmployeeDTO() { }

    public EmployeeDTO(String id, String firstName, String lastName, String level, double wage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.wage = wage;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Double.compare(that.wage, wage) == 0 && Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, level, wage);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", level='" + level + '\'' +
                ", wage=" + wage +
                '}';
    }
}
