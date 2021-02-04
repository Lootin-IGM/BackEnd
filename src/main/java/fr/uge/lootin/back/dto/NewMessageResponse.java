package fr.uge.lootin.back.dto;

import java.sql.Timestamp;

public class NewMessageResponse {
    private MatchResponse match;

    private Timestamp sendTime;

    private String message;

    public NewMessageResponse(MatchResponse match, Timestamp sendTime, String message) {
        this.match = match;
        this.sendTime = sendTime;
        this.message = message;
    }

    public MatchResponse getMatch() {
        return match;
    }

    public void setMatch(MatchResponse match) {
        this.match = match;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
