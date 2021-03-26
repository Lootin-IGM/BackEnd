package fr.uge.lootin.back.dto;

public class NewMessageRequest {
    private String text;
    private Long matchId;
    private Long sender;

    public NewMessageRequest(String text, Long matchId, Long sender) {
        this.text = text;
        this.matchId = matchId;
        this.sender = sender;

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

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "NewMessageRequest{" +
                "text='" + text + '\'' +
                ", matchId=" + matchId +
                '}';
    }
}
