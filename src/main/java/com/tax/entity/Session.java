package com.tax.entity;

public class Session {
    private final String token;
    private final User user;

    public Session(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
