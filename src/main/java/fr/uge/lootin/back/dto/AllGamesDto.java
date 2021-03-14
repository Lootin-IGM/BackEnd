package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Game;

import java.util.List;

public class AllGamesDto {

    private List<Game> games;

    public AllGamesDto() {}

    public AllGamesDto(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
