package ru.itis.models;

import java.util.List;

public class User {
    private Long id;
    private String email;
    private String hashPassword;

    public User(Long id, String email, String hashPassword) {
        this.id = id;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public User(String email, String hashPassword) {
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getHashPassword() {
        return hashPassword;
    }
}
