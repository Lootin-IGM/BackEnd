package fr.uge.lootin.back.dto;

import java.sql.Timestamp;

public class MessageResponse {
    private Long id;
    private Timestamp sendTime;

    private String message;

    private UserResponse sender;

    public MessageResponse(Long id, Timestamp sendTime, String message, UserResponse sender) {
        this.id = id;
        this.sendTime = sendTime;
        this.message = message;
        this.sender = sender;
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

    public UserResponse getSender() {
        return sender;
    }

    public void setSender(UserResponse sender) {
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
