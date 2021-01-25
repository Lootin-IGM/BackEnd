package fr.uge.lootin.back.dto;

import java.util.Objects;

public class LoginResponse {

    private final String jwt;

    public LoginResponse(String jwt){
        this.jwt = Objects.requireNonNull(jwt);
    }

    public String getJwt(){
        return jwt;
    }
}
