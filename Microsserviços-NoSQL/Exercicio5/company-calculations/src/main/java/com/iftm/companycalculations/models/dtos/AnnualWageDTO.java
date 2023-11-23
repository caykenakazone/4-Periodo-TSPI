package com.iftm.companycalculations.models.dtos;

import java.io.Serializable;

public class AnnualWageDTO implements Serializable {
    private String firstName;
    private double annualWage;

    public AnnualWageDTO(String firstName, double annualWage) {
        this.firstName = firstName;
        this.annualWage = annualWage;
    }

    public AnnualWageDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getAnnualWage() {
        return annualWage;
    }

    public void setAnnualWage(double annualWage) {
        this.annualWage = annualWage;
    }
}
