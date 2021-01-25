package fr.uge.lootin.back.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Login {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Login{" +
                "login='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return username.equals(login1.username) &&
                password.equals(login1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
