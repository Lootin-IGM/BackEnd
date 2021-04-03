package fr.uge.lootin.back.models;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "_Like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "userLikedId")
    private User userLiked;

    public Like(User user, User userLiked) {
        this.user = user;
        this.userLiked = userLiked;
    }

    public Like() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(User userLiked) {
        this.userLiked = userLiked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return id == like.id && Objects.equals(user, like.user) && Objects.equals(userLiked, like.userLiked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, userLiked);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + user +
                ", userLiked=" + userLiked +
                '}';
    }
}
