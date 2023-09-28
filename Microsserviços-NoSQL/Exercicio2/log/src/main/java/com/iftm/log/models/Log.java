package com.iftm.log.models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;

public class Log<T> {
    @Id
    private String id;
    private String action;
    private Date date;
    private Object objeto;
    private String classType;

    public Log() {
    }

    public Log(String id, String action, Date date, Object objeto, String classType) {
        this.id = id;
        this.action = action;
        this.date = date;
        this.objeto = objeto;
        this.classType = classType;
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

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log<T> log = (Log<T>) o;
        return Objects.equals(id, log.id) && Objects.equals(action, log.action) && Objects.equals(date, log.date) && Objects.equals(objeto, log.objeto) && Objects.equals(classType, log.classType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, date, objeto, classType);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", objeto=" + objeto +
                ", classType='" + classType + '\'' +
                '}';
    }
}
