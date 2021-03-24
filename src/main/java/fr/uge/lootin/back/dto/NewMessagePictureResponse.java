package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;
import org.springframework.web.multipart.MultipartFile;

public class NewMessagePictureResponse {
    private Long id;
    private Long sender;

    private String sendTime;

    private String picture;

    public NewMessagePictureResponse(Long id, Long sender , String sendTime, String picture) {
        this.id = id;
        this.sender = sender;
        this.sendTime = sendTime;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
