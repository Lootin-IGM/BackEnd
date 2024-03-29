package fr.uge.lootin.back.dto;

import java.util.Objects;

public class RegisterResponse {

    private final String response;

    public RegisterResponse(String username){
        this.response = "User : " + Objects.requireNonNull(username) + " successfully registered";
    }

    public String getResponse(){
        return response;
    }
}
