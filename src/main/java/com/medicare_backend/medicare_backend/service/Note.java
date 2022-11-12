package com.medicare_backend.medicare_backend.service;

import java.util.Map;

public class Note {
    private String subject;
    private String content;

    Note() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getData() {
        return null;
    }

}
