package com.aram.connect.persistence.dao;

import java.io.Serializable;

import javax.persistence.Entity;

public class PrelimsAnswer implements Serializable {
    public NewOnlineTestQuestion newOnlineTestQuestion;
    public String answer;
    public Boolean isCorrect;
    public Long mark;
}