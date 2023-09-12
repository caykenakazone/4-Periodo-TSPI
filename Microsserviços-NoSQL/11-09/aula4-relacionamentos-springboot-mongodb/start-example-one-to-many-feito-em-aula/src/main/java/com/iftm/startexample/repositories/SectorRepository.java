package com.iftm.startexample.repositories;

import com.iftm.startexample.models.Sector;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends MongoRepository<Sector, ObjectId> {

    // Obter um setor de determinado employee
    public Optional<Sector> findSectorByEmployeeId(ObjectId id);
}
