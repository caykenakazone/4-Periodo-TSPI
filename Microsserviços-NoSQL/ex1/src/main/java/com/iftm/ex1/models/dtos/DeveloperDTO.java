package com.iftm.ex1.models.dtos;

import com.iftm.ex1.models.Address;
import com.iftm.ex1.models.Developer;
import com.iftm.ex1.models.Sector;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class DeveloperDTO implements Serializable {

    private String id;
    private String name;
    private String lastName;
    private String level;
    private String specialization;
    private Address address;
    private Sector sector;

    public DeveloperDTO() {
    }

    public DeveloperDTO(Developer developer) {
        this.id = developer.getId().toString();
        this.name = developer.getName();
        this.lastName = developer.getLastName();
        this.level = developer.getLevel();
        this.specialization = developer.getSpecialization();
        this.address = developer.getAddress();
        this.sector = developer.getSector();
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDTO that = (DeveloperDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(level, that.level) && Objects.equals(specialization, that.specialization) && Objects.equals(address, that.address) && Objects.equals(sector, that.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, level, specialization, address, sector);
    }

    @Override
    public String toString() {
        return "DeveloperDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", level='" + level + '\'' +
                ", specialization='" + specialization + '\'' +
                ", address=" + address +
                ", sector=" + sector +
                '}';
    }
}
