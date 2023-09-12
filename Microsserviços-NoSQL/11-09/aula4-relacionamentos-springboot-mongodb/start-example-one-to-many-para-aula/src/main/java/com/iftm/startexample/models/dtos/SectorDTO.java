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

}
