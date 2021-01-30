package ru.itis.models;

public class Session {
    private Long id;
    private String sessionId;
    private Long userId;

    public Session(Long id, String sessionId, Long userId) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Session(String sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }
}
