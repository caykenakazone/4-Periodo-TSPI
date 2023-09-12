package com.iftm.startexample.models.dtos;

import com.iftm.startexample.models.Employee;
import com.iftm.startexample.models.Sector;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SectorDTO implements Serializable {

    private String id;
    private String name;
    private String description;
    private List<EmployeeDTO> employees;

    public SectorDTO() { }

    public SectorDTO(String name, String description, List<EmployeeDTO> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public SectorDTO(Sector sector) {
        if(sector.getId() != null)
            this.id = sector.getId().toString();
        this.name = sector.getName();
        this.description = sector.getDescription();
        if(sector.getEmployees() != null)
            this.employees = sector.getEmployees().stream().map(employee -> {
                return new EmployeeDTO(employee);
            }).collect(Collectors.toList());
    }

    public Sector toSector() {
        ObjectId id = null;
        if(this.id != null)
            id = new ObjectId(this.id);

        List<Employee> employees = null;
        if(this.employees != null)
            employees = this.employees.stream().map(employeeDTO -> {
                return employeeDTO.toEmployee();
            }
            ).collect(Collectors.toList());

        return new Sector(id,
                this.name,
                this.description,
                employees);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectorDTO sectorDTO = (SectorDTO) o;
        return Objects.equals(id, sectorDTO.id) && Objects.equals(name, sectorDTO.name) && Objects.equals(description, sectorDTO.description) && Objects.equals(employees, sectorDTO.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, employees);
    }

    @Override
    public String toString() {
        return "SectorDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }
}
