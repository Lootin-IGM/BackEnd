package fr.uge.lootin.back.models;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "matchId")
    private Match match;

    @Column(name = "sendTime")
    @Type(type="timestamp")
    private Timestamp sendTime;

    @Column(name ="message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User user;


    public Message(Match match, Timestamp sendTime, String message, User user) {
        this.match = match;
        this.sendTime = sendTime;
        this.message = message;
        this.user = user;
    }

    public Message(Match match, Timestamp sendTime, String message) {
        this.match = match;
        this.sendTime = sendTime;
        this.message = message;
    }

    public Message(Match match, String message, User user) {
        this(match, new Timestamp(System.currentTimeMillis()), message, user);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id && Objects.equals(match, message1.match) && Objects.equals(sendTime, message1.sendTime) && Objects.equals(message, message1.message) && Objects.equals(user, message1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match, sendTime, message, user);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", match=" + match +
                ", sendTime=" + sendTime +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
