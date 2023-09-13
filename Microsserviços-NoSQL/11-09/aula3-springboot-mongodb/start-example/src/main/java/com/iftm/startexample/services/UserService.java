package com.iftm.startexample.services;

import com.iftm.startexample.models.User;
import com.iftm.startexample.models.dtos.UserDTO;
import com.iftm.startexample.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseEntity<List<UserDTO>> findAll() {
        var dbUsers = repository.findAll();
        if(dbUsers.isEmpty())
            return ResponseEntity.notFound().build();

        var userDtos = dbUsers.stream().map(user -> {
            var userDTO = new UserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(userDtos);
    }

    public ResponseEntity<UserDTO> findById(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        var dbUser = repository.findById(id);
        if(dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDTO( dbUser.get()));
    }

    public ResponseEntity<UserDTO> save(User user) {
        //validar user
        if(user.getName().isBlank() || user.getAge() <= 0)
            return ResponseEntity.badRequest().build();
        user.setId(ObjectId.get());
        return ResponseEntity.ok(new UserDTO(repository.save(user)));
    }

    public ResponseEntity<UserDTO> update(UserDTO user) {
        // validar user
        if(user.getId() == null)
            return ResponseEntity.badRequest().build();

        var objectId = new ObjectId(user.getId());
        var dbUser = repository.findById(objectId);
        if(dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        // atualizar
        var dbUserObj = dbUser.get();
        dbUserObj.setName(user.getName());
        dbUserObj.setAge(user.getAge());
        return ResponseEntity.ok(new UserDTO(repository.save(dbUserObj)));
    }

    public ResponseEntity<?> delete(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        repository.deleteById(id);

        var dbUser = repository.findById(id);
        if(dbUser.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.ok().build();
    }
}
