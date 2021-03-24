package fr.uge.lootin.back.dto;

import org.springframework.web.multipart.MultipartFile;

public class NewMessagePictureRequest {
    private String picture;
    private Long matchId;

    public NewMessagePictureRequest(String picture, Long matchId) {
        this.picture = picture;
        this.matchId = matchId;
    }

    public NewMessagePictureRequest() {
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
                "picture='" + picture + '\'' +
                ", matchId=" + matchId +
                '}';
    }
}
