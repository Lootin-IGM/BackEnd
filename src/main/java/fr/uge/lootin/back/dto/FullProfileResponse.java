package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.User;

import java.util.Set;

public class FullProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Set<Game> games;

    public FullProfileResponse(Long id, String firstName, String lastName, String login, Set<Game> games) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.games = games;
    }

    public FullProfileResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getUsername();
        this.games = user.getGames();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
