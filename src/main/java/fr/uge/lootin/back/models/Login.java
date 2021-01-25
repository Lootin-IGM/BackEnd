package fr.uge.lootin.back.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Login {
    @NotNull
    private String login;
    @NotNull
    private String password;

    public Login() {
    }

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return login.equals(login1.login) &&
                password.equals(login1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
