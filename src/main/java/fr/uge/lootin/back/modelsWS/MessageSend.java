package fr.uge.lootin.back.modelsWS;

import java.io.Serializable;

public class MessageSend {


    private Long id_author;

    private String content;

    private String sendTo;

    public MessageSend(Long id_author, String content, String sendTo) {
        this.id_author = id_author;
        this.content = content;
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "MessageSend{" +
                "id_author='" + id_author + '\'' +
                ", content='" + content + '\'' +
                ", sendTo='" + sendTo + '\'' +
                '}';
    }

    public MessageSend() {
    }

    public Long getId_author() {
        return id_author;
    }

    public void setId_author(Long id_author) {
        this.id_author = id_author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
}

