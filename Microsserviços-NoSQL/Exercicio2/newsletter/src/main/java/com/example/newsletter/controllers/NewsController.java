package com.example.newsletter.controllers;

import com.example.newsletter.models.News;
import com.example.newsletter.models.dtos.NewsDTO;
import com.example.newsletter.services.NewsService;
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
    public List<News> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public News findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public News create(@RequestBody NewsDTO newsDto) {
        return service.create(newsDto);
    }

    @PutMapping
    public News update(@RequestBody News news) {
        return service.update(news);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
