package com.example.startexample.services;

import com.example.startexample.models.User;
import com.example.startexample.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    //CRUD


    // Read
    // All

    public ResponseEntity<List<User>> findAll() {
        var dbUsers = repository.findAll();
        if (dbUsers.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dbUsers);
    }

    public ResponseEntity<User> findById(ObjectId objectId) {
        Optional<User> dbUser;
        dbUser = repository.findById(objectId);
        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dbUser.get());
    }
    public ResponseEntity<User> save(User user){
        //verificacao
        if (user.getName().isBlank() || user.getAge() <= 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(repository.save(user));
    }
    public ResponseEntity<User> update(User user){
        if (user.getId() == null || user.getId().toString().isBlank())
            return  ResponseEntity.badRequest().build();
        var dbUser = repository.findById(user.getId());

        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        var dbUserObj = dbUser.get();
        dbUserObj.setName(user.getName());
        dbUserObj.setAge(user.getAge());
        dbUserObj.setAddress(user.setAddress());
        return ResponseEntity.ok(repository.save(dbUserObj));
    }
    public ResponseEntity<?> delete(ObjectId objectId){
        repository.deleteById(objectId);
        var dbUser = repository.findById(objectId);
        if (dbUser.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
}
