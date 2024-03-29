package fr.uge.lootin.back.dto;

import fr.uge.lootin.back.models.User;

import java.util.Base64;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String image;
    private User.Gender gender;
    private int age;
    private String description;

    public UserResponse(Long id, String firstName, String lastName, String login, String image, User.Gender gender, int age, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.image = image;
        this.gender = gender;
        this.age = age;
        this.description = description;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getUsername();
        this.image = Base64.getEncoder().encodeToString(user.getImage().getImage());
        this.gender = user.getGender();
        this.age = user.getAge();
        this.description = user.getDescription();
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

    public User.Gender getGender() {
        return gender;
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
