package fr.uge.lootin.back.dto;

import java.util.List;

public class LiteProfileResponse {
    private List<UserResponse> users;

    public LiteProfileResponse(List<UserResponse> users) {
        this.users = users;
    }

    public LiteProfileResponse() {
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }
}
