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

    @ManyToOne
    @JoinColumn(name = "imageId")
    private Image image;

    public Game() {
    }

    public Game(String gameName, Image image) {
        this.gameName = gameName;
        this.image = image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                gameName.equals(game.gameName) &&
                image.equals(game.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, image);
    }
}
