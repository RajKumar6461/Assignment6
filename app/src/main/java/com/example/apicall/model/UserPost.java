package com.example.apicall.model;

import java.io.Serializable;

public class UserPost implements Serializable {
    private String userId;
    private String title;
    private String id;
    private String body;

    public UserPost(String userId, String title, String id, String body) {
        this.userId = userId;
        this.title = title;
        this.id = id;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
