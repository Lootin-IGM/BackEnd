package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.utils.TypeMessage;

import java.sql.Timestamp;

public class MessageResponse {
    private Long id;
    private Long sender;

    private Timestamp sendTime;

    private String message;

    private TypeMessage typeMessage;

    public MessageResponse(Long id, Timestamp sendTime, String message, Long sender, TypeMessage typeMessage) {
        this.id = id;
        this.sendTime = sendTime;
        this.message = message;
        this.sender = sender;
        this.typeMessage = typeMessage;
    }


    public static MessageResponse createFromMessage(Message message){
        return new MessageResponse(message.getId(), message.getSendTime(), message.getMessage(), message.getUser().getId(),message.getTypeMessage());
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

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
