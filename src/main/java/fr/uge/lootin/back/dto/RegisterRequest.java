package fr.uge.lootin.back.dto;


import java.util.List;

public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> games;
    private String description;
    private int age;
    private String gender;

    public RegisterRequest(String username, String password, String firstName, String lastName, List<String> games, String description, int age, String gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.games = games;
        this.description = description;
        this.age = age;
        this.gender = gender;
    }

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<String> getGames() { return games; }

    public void setGames(List<String> games) { this.games = games; }

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