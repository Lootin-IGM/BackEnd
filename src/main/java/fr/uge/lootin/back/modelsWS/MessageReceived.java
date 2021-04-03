package fr.uge.lootin.back.modelsWS;

import java.time.LocalDateTime;
import java.util.Date;

public class MessageReceived {

    private Long id_author;

    private String content;

    private LocalDateTime date;

    @Override
    public String toString() {
        return "MessageReceived{" +
                "id_author='" + id_author + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    public MessageReceived() {
    }

    public MessageReceived(Long id_author, String content, LocalDateTime date) {
        this.id_author = id_author;
        this.content = content;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

