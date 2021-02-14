package fr.uge.lootin.back.dto;

import java.sql.Timestamp;

public class NewMessageResponse {
    private Long id;
    private UserResponse user;

    private Timestamp sendTime;

    private String message;

    public NewMessageResponse(Long id, UserResponse user, Timestamp sendTime, String message) {
        this.id = id;
        this.user = user;
        this.sendTime = sendTime;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
