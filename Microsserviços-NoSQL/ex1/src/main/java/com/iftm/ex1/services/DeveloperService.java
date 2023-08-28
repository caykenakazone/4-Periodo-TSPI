package com.iftm.ex1.services;

import com.iftm.ex1.models.Developer;
import com.iftm.ex1.models.dtos.DeveloperDTO;
import com.iftm.ex1.repositories.DeveloperRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository repository;
    public ResponseEntity<List<DeveloperDTO>>  findAll(){
        var dbDev = repository.findAll();
        if (dbDev.isEmpty())
            return ResponseEntity.ok(Collections.emptyList());
        var devDtos = dbDev.stream().map(dev ->{
            var devDTO = new DeveloperDTO(dev);
            return devDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(devDtos);
    }

    public ResponseEntity<DeveloperDTO> findById(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        var dbDev = repository.findById(id);
        if(dbDev.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DeveloperDTO( dbDev.get()));
    }

    public ResponseEntity<DeveloperDTO> save(Developer developer) {
        if(developer.getName().isBlank())
            return ResponseEntity.badRequest().build();
        developer.setId(ObjectId.get());
        return ResponseEntity.ok(new DeveloperDTO(repository.save(developer)));
    }

    public ResponseEntity<DeveloperDTO> update(DeveloperDTO developerDTO) {
        if(developerDTO.getId() == null)
            return ResponseEntity.badRequest().build();

        var objectId = new ObjectId(developerDTO.getId());
        var dbDev = repository.findById(objectId);
        if(dbDev.isEmpty())
            return ResponseEntity.notFound().build();

        var dbDevObj = dbDev.get();
        dbDevObj.setName(developerDTO.getName());
        dbDevObj.setLastName(developerDTO.getLastName());
        dbDevObj.setLevel(developerDTO.getLevel());
        dbDevObj.setSpecialization(developerDTO.getSpecialization());
        dbDevObj.setAddress(developerDTO.getAddress());
        dbDevObj.setSector(developerDTO.getSector());
        return ResponseEntity.ok(new DeveloperDTO(repository.save(dbDevObj)));
    }

    public ResponseEntity<?> delete(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        repository.deleteById(id);

        var dbDev = repository.findById(id);
        if(dbDev.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.ok().build();
    }
}
