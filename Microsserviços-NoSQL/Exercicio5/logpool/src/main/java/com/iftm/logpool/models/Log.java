package com.iftm.logpool.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "softwareHouseLogs")
public class Log<T> {

    @Id
    private String id;
    private String action;
    private Date date = new Date();
    private T object;
    private String objectType;

    public Log() {
    }

    public Log(String action, Date date, T object, String objectType) {
        this.action = action;
        this.date = date;
        this.object = object;
        this.objectType = objectType;
    }

    public Log(String id, String action, Date date, T object, String objectType) {
        this.id = id;
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
        Log<?> log = (Log<?>) o;
        return Objects.equals(id, log.id) && Objects.equals(action, log.action) && Objects.equals(date, log.date) && Objects.equals(object, log.object) && Objects.equals(objectType, log.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, date, object, objectType);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", object=" + object +
                ", objectType='" + objectType + '\'' +
                '}';
    }
}
