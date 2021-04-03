package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.User;

import java.util.Base64;
import java.util.Set;

public class FullProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Set<Game> games;
    private String image;
    private String description;
    private int age;
    private String gender;

    public FullProfileResponse(Long id, String firstName, String lastName, String login, Set<Game> games, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.games = games;
        this.image = image;
    }

    public FullProfileResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getUsername();
        this.games = user.getGames();
        this.description = user.getDescription();
        this.age = user.getAge();
        this.gender = user.getGender().toString();
        this.image = Base64.getEncoder().encodeToString(user.getImage().getImage());
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
