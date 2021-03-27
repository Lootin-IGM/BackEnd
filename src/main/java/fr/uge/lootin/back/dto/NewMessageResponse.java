package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.utils.TypeMessage;

import java.sql.Timestamp;

public class NewMessageResponse {
    private Long id;
    private Long sender;

    private Timestamp sendTime;

    private String message;

    private TypeMessage typeMessage;

    public NewMessageResponse(Long id, Long sender, Timestamp sendTime, String message, TypeMessage typeMessage) {
        this.id = id;
        this.sender = sender;
        this.sendTime = sendTime;
        this.message = message;
        this.typeMessage = typeMessage;
    }

    /**
     * TODO NEVER USE THAT
     * @param message
     * @param user
     */
    public NewMessageResponse(Message message, User user) {
        this.id = message.getMatch().getId();
        this.sender = user.getId();
        this.sendTime = message.getSendTime();
        this.message = message.getMessage();
        this.typeMessage = TypeMessage.TEXT;
    }

    public static NewMessageResponse createFromMessage(Message message){
        return new NewMessageResponse(message.getId(), message.getUser().getId(),message.getSendTime(),message.getMessage(), message.getTypeMessage() );
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

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
