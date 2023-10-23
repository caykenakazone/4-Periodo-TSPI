package com.example.newsletter.services;

import com.example.newsletter.mensages.RabbitMqSendLog;
import com.example.newsletter.models.News;
import com.example.newsletter.models.NotificationMessage;
import com.example.newsletter.models.dtos.LogDTO;
import com.example.newsletter.models.dtos.NewsDTO;
import com.example.newsletter.repositories.NewsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private RabbitMqSendLog rabbitMqSendLog;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    public ResponseEntity<List<NewsDTO>> findAll() {
        var dbNews = repository.findAll();
        if (dbNews.isEmpty()) return ResponseEntity.notFound().build();
        var dbNewsDto = dbNews.stream().map(news -> {
            return new NewsDTO(news);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dbNewsDto);
    }

    public ResponseEntity<NewsDTO> findById(ObjectId id) {
        if (id == null) return ResponseEntity.badRequest().build();

        var news = repository.findById(id);
        if (news.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new NewsDTO(news.get()));
    }

    public ResponseEntity<NewsDTO> save(NewsDTO news) {
        if (news.getTitle().isBlank() || news.getEditorName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        var savedNews = repository.save(news.toNews());
        rabbitMqSendLog.sendLog(
                new LogDTO("create",
                        Date.from(Instant.now()),
                        new NewsDTO(savedNews),
                        savedNews.getClass().toString()));

        firebaseMessagingService.sendNotification(new NotificationMessage(
                "...",
                news.getTitle(),
                "Postagem adicionada.",
                "https://play-lh.googleusercontent.com/ZrsPit-BgpiMdm3am82N-4XV5DJJTkf1JzWFi26F39dWX6gCBFylt3t4iL93NOYeVhM=w240-h480",
                new HashMap<>()
        ));
        return ResponseEntity.ok(new NewsDTO(savedNews));
    }


    public ResponseEntity<NewsDTO> update(NewsDTO news) {
        if (news.getId() == null) return ResponseEntity.badRequest().build();
        if (!repository.findById(new ObjectId(news.getId())).isPresent())
            return ResponseEntity.notFound().build();

        var dbNews = repository.findById(new ObjectId(news.getId())).get();
        dbNews.setDate(news.getDate());
        dbNews.setTitle(news.getTitle());
        dbNews.setPosts(news.getPosts());
        dbNews.setEditorName(news.getEditorName());

        rabbitMqSendLog.sendLog(
                new LogDTO("update",
                        Date.from(Instant.now()),
                        dbNews,
                        dbNews.getClass().toString()));
        return ResponseEntity.ok(new NewsDTO(repository.save(dbNews)));
    }

    public ResponseEntity<?> delete(ObjectId id) {
        if (id == null) return ResponseEntity.badRequest().build();
        var logNews = new NewsDTO(repository.findById(id).get());
        repository.deleteById(id);
        var news = repository.findById(id);
        if (news.isPresent()) return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

        rabbitMqSendLog.sendLog(
                new LogDTO("delete",
                        Date.from(Instant.now()),
                        logNews,
                        logNews.getClass().toString()));
        return ResponseEntity.ok().build();
    }


}
