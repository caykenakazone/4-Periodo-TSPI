package com.iftm.imobiliarianosqql.services;

import com.iftm.imobiliarianosqql.model.Imovel;
import com.iftm.imobiliarianosqql.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private  ImovelRepository repository;

    @Cacheable(value = "imoveisCache", key = "#root.methodName")
    public List<Imovel> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "imoveisCache", key = "#id")
    public Optional<Imovel> findById(String id) {
        return repository.findById(id);
    }

    @CacheEvict(value = "imoveisCache", allEntries = true)
    public Imovel save(Imovel imovel) {
        return repository.save(imovel);
    }

    @CacheEvict(value = "imoveisCache", key = "#id")
    public Imovel update(String id, Imovel novoImovel) {
        Optional<Imovel> optionalImovel = repository.findById(id);

        if (optionalImovel.isPresent()) {
            Imovel imovel = optionalImovel.get();
            imovel.setEndereco(novoImovel.getEndereco());
            imovel.setTipoImovel(novoImovel.getTipoImovel());
            imovel.setArea(novoImovel.getArea());
            imovel.setPreco(novoImovel.getPreco());
            return repository.save(imovel);
        } else {
            return null;
        }
    }

    @CacheEvict(value = "imoveisCache", key = "#id")
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
