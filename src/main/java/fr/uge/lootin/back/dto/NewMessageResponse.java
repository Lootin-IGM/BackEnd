package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;

import java.sql.Timestamp;

public class NewMessageResponse {
    private Long id;
    private Long sender;

    private String sendTime;

    private String message;

    public NewMessageResponse(Long id, Long sender , String sendTime, String message) {
        this.id = id;
        this.sender = sender;
        this.sendTime = sendTime;
        this.message = message;
    }

    public NewMessageResponse(Message message, User user) {
        this.id = message.getMatch().getId();
        this.sender = user.getId();
        this.sendTime = message.getSendTime().toString();
        this.message = message.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}