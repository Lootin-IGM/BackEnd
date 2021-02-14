package fr.uge.lootin.back.dto;

public class MatchResponse {
    private Long id;
    private UserResponse user;
    private MessageResponse lastMessage;

    public MatchResponse(Long id, UserResponse user, MessageResponse lastMessage) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
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

    public MessageResponse getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageResponse lastMessage) {
        this.lastMessage = lastMessage;
    }
}
