package fr.uge.lootin.back;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;


public class Message {
    private long id;
    private Match match;
    private Timestamp sendTime;
    private String message;


    public Message(Match match, Timestamp sendTime, String message) {
        this.match = match;
        this.sendTime = sendTime;
        this.message = message;
    }

    public Message(Match match, String message) {
        this.match = match;
        this.sendTime = new Timestamp(System.currentTimeMillis());
        this.message = message;
    }

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id && Objects.equals(match, message1.match) && Objects.equals(sendTime, message1.sendTime) && Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match, sendTime, message);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", match=" + match +
                ", sendTime=" + sendTime +
                ", message='" + message + '\'' +
                '}' + "\n";
    }
}