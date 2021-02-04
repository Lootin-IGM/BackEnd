package fr.uge.lootin.back.dto;

public class NewMessageRequest {
    private String text;
    private Long matchId;

    public NewMessageRequest(String text, Long matchId) {
        this.text = text;
        this.matchId = matchId;
    }

    public NewMessageRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "NewMessageRequest{" +
                "text='" + text + '\'' +
                ", matchId=" + matchId +
                '}';
    }
}
