package fr.uge.lootin.back.dto;

public class Notification {
    private String type;
    private String content;


    public Notification() {
    }

    public Notification(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
