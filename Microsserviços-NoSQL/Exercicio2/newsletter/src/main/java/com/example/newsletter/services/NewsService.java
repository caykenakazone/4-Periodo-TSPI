package com.example.newsletter.services;

import com.example.newsletter.models.News;
import com.example.newsletter.models.dtos.NewsDTO;
import com.example.newsletter.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private  NewsRepository repository;

    public List<News> findAll() {
        return repository.findAll();
    }

    public News findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public News create(NewsDTO newsDTO) {
        return repository.save(new News(newsDTO));
    }

    public News update(News news) {
        return repository.save(news);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
