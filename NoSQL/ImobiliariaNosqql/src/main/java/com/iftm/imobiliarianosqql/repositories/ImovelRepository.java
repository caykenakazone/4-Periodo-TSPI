package com.iftm.imobiliarianosqql.repositories;

import com.iftm.imobiliarianosqql.model.Imovel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImovelRepository extends MongoRepository<Imovel, String> {
}
