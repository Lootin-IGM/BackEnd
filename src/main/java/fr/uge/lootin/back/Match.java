package fr.uge.lootin.back;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "_Match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "user1Id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2Id")
    private User user2;

    public Match() {
    }

    public Match(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id && Objects.equals(user1, match.user1) && Objects.equals(user2, match.user2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user1, user2);
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }
}
