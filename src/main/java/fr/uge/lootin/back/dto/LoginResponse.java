package fr.uge.lootin.back.dto;

import java.util.Objects;

public class LoginResponse {

    private final String jwt;
    private final String channelToken;
    private final Long id;

    public LoginResponse(String jwt, String channelToken, Long id){
        this.jwt = Objects.requireNonNull(jwt);
        this.channelToken = channelToken;
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getChannelToken(){
        return channelToken;
    }
    public String getJwt(){
        return jwt;
    }
}
