package com.example.newsletter.models;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class NotificationMessage implements Serializable {
    private String recipientToken;
    private String title;
    private String body;
    private String image;
    private Map<String, String> data;

    public NotificationMessage() {
    }

    public NotificationMessage(String recipientToken, String title, String body, String image, Map<String, String> data) {
        this.recipientToken = recipientToken;
        this.title = title;
        this.body = body;
        this.image = image;
        this.data = data;
    }

    public String getRecipientToken() {
        return recipientToken;
    }

    public void setRecipientToken(String recipientToken) {
        this.recipientToken = recipientToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationMessage that = (NotificationMessage) o;
        return Objects.equals(recipientToken, that.recipientToken) && Objects.equals(title, that.title) && Objects.equals(body, that.body) && Objects.equals(image, that.image) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipientToken, title, body, image, data);
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "recipientToken='" + recipientToken + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", image='" + image + '\'' +
                ", data=" + data +
                '}';
    }
}

