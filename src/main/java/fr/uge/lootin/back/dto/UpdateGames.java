package fr.uge.lootin.back.dto;

import java.util.List;

public class UpdateGames {
    private List<String> games;

    public UpdateGames(){}

    public UpdateGames(List<String> games) {
        this.games = games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }

    public List<String> getGames() {
        return games;
    }
}
