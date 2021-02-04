package fr.uge.lootin.back.dto;

import java.sql.Timestamp;

public class MessageResponse {
    private Timestamp sendTime;

    private String message;

    private UserResponse sender;

    public MessageResponse(Timestamp sendTime, String message, UserResponse sender) {
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
}
