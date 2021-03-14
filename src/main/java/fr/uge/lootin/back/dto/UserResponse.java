package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.User;

import java.util.Base64;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String image;

    public UserResponse(Long id, String firstName, String lastName, String login, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.image = image;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getUsername();
        this.image = Base64.getEncoder().encodeToString(user.getImage().getImage());
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
