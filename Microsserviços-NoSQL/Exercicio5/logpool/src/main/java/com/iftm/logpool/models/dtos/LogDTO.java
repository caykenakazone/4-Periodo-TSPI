package com.iftm.logpool.models.dtos;

import com.iftm.logpool.models.Log;

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

    public LogDTO(String action, T object, String objectType) {
        this.action = action;
        this.object = object;
        this.objectType = objectType;
    }

    public LogDTO(Log log) {
        this.id = log.getId();
        this.action = log.getAction();
        this.date = log.getDate();
        this.object = (T)log.getObject();
        this.objectType = log.getObjectType();
    }

    public Log toLog() {

        String id;
        var log = new Log<T>();
        log.setAction(this.action);
        log.setDate(this.date);
        log.setObject(this.object);

        if(this.id != null && !this.id.isBlank())
            log.setId(this.id);

        if(this.objectType != null && !this.objectType.isBlank())
            log.setObjectType(this.objectType);

        return log;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        LogDTO<?> logDTO = (LogDTO<?>) o;
        return Objects.equals(id, logDTO.id) && Objects.equals(action, logDTO.action) && Objects.equals(date, logDTO.date) && Objects.equals(object, logDTO.object) && Objects.equals(objectType, logDTO.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, date, object, objectType);
    }

    @Override
    public String toString() {
        return "LogDTO{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", object=" + object +
                ", objectType='" + objectType + '\'' +
                '}';
    }
}
