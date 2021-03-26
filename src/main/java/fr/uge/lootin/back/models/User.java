package fr.uge.lootin.back.models;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "User")
public class User implements UserDetails {
    public enum Authority {
        USER
    }

    public enum Gender {
        FEMALE,
        MALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @ManyToMany
    @JoinTable(name = "User_Game", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "game_id") })
    private Set<Game> games;

    @Embedded
    private Login login;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Authority authority;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    private int age;

    @NotNull
    @Lob
    private String description;

    @NotNull
    @ManyToOne
    @JoinTable(name = "User_Image", joinColumns = { @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Image image;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Attraction attraction;

    public User() {
    }

    public User(String firstName, String lastName, Set<Game> games, Login login, Gender gender, int age, String description, Image image, Attraction attraction, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.games = games;
        this.login = login;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.image = image;
        this.attraction = attraction;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(games, user.games) && Objects.equals(login, user.login) && authority == user.authority && gender == user.gender && Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, games, login, authority, gender, age, description);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", games=" + games +
                ", login=" + login +
                ", authority=" + authority +
                ", gender=" + gender +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", attraction=" + attraction +
                '}';
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority.name()));
    }

    public String getPassword() { return login.getPassword(); }

    public void setPassword(String password) {
        login.setPassword(password);
    }

    public String getUsername() { return login.getUsername(); }

    public void setUsername(String username) { login.setUsername(username); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
