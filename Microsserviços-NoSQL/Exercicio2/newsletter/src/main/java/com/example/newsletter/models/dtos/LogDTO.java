package com.example.newsletter.models.dtos;

import java.io.Serializable;
import java.util.Date;

public class LogDTO<T> implements Serializable {
    private String action;
    private Date date;
    private T objeto;
}
