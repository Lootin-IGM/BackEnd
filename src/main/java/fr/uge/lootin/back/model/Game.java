package fr.uge.lootin.back.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String gameName;

    public Game() {
    }

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public long getId() {
        return id;
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
