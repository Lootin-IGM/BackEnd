package fr.uge.lootin.back.dto;

public class MatchResponse {
    private Long id;
    private UserResponse user;
    //TODO add last message of match


    public MatchResponse(Long id, UserResponse user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
