package com.example.newsletter.models;

import java.util.List;
import java.util.Objects;

public class Post {
    private String title;
    private String authorName;
    private String body;
    private List<Tag> tags;

    public Post() {
    }

    public Post(String title, String authorName, String body, List<Tag> tags) {
        this.title = title;
        this.authorName = authorName;
        this.body = body;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) && Objects.equals(authorName, post.authorName) && Objects.equals(body, post.body) && Objects.equals(tags, post.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authorName, body, tags);
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                '}';
    }
}
