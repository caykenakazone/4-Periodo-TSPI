package com.example.newsletter.controllers;

import com.example.newsletter.models.News;
import com.example.newsletter.models.dtos.NewsDTO;
import com.example.newsletter.services.NewsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {
    @Autowired
    private NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<NewsDTO>> findAll(){
        return service.findAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable("id") ObjectId id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<NewsDTO> create(@RequestBody NewsDTO news) {
        return service.save(news);
    }

    @PutMapping
    public ResponseEntity<NewsDTO> update(@RequestBody NewsDTO news) {
        return service.update(news);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id){
        return service.delete(id);
    }
}
