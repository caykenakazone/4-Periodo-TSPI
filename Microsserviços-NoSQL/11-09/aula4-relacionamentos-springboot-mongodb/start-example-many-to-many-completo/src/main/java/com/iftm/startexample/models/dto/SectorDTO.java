package com.iftm.startexample.models.dto;

import com.iftm.startexample.models.Sector;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class SectorDTO implements Serializable {

    private String id;
    private String name;
    private String description;

    public SectorDTO() {
    }

    public SectorDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public SectorDTO(Sector sector) {
        this.id = sector.getId();
        this.name = sector.getName();
        this.description = sector.getDescription();
    }

    public Sector toSector() {
        return new Sector(
                this.id,
                this.name,
                this.description,
                null
        );
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectorDTO sectorDTO = (SectorDTO) o;
        return Objects.equals(id, sectorDTO.id) && Objects.equals(name, sectorDTO.name) && Objects.equals(description, sectorDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "SectorDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
