package com.example.newsletter.models;

import com.example.newsletter.models.dtos.NewsDTO;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class News {
    @Id
    private String id;
    private String title;
    private String date;

    private String editorName;
    private List<Post> posts;

    public News() {
    }

    public News(String id, String title, String date, String editorName, List<Post> posts) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.editorName = editorName;
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(date, news.date) && Objects.equals(editorName, news.editorName) && Objects.equals(posts, news.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, editorName, posts);
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", editorName='" + editorName + '\'' +
                ", posts=" + posts +
                '}';
    }
}
