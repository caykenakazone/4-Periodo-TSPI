package com.example.newsletter.models.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LogDTO<T> implements Serializable {

    private String id;
    private String action;
    private Date date = new Date();
    private T object;
    private String objectType;

    public LogDTO() {
    }

    public LogDTO(String action, Date date, T object, String objectType) {
        this.action = action;
        this.date = date;
        this.object = object;
        this.objectType = objectType;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDTO<?> logDto = (LogDTO<?>) o;
        return Objects.equals(id, logDto.id) && Objects.equals(action, logDto.action) && Objects.equals(date, logDto.date) && Objects.equals(object, logDto.object) && Objects.equals(objectType, logDto.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, date, object, objectType);
    }

    @Override
    public String toString() {
        return "LogDto{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", object=" + object +
                ", objectType='" + objectType + '\'' +
                '}';
    }
}

