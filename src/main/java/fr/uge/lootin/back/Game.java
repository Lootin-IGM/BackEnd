package fr.uge.lootin.back;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String gameName;

    public Game() {
    }

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && Objects.equals(gameName, game.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName);
    }
}
