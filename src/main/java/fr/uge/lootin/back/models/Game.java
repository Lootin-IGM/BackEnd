package fr.uge.lootin.back.models;

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

    @NotNull
    private String imageURL;

    public Game() {
    }

    public Game(String gameName, String imageURL) {
        this.gameName = gameName;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                gameName.equals(game.gameName) &&
                imageURL.equals(game.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, imageURL);
    }
}
