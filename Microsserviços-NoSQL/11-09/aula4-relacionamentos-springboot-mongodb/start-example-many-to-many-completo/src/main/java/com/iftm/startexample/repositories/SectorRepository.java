package com.iftm.startexample.repositories;

import com.iftm.startexample.models.Sector;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends MongoRepository<Sector, String> {
    @Query("{'employees._id': ?0}")
    public Optional<Sector> findSectorByEmployeeId(String employeeId);
}
