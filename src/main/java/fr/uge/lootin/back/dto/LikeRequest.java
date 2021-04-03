package fr.uge.lootin.back.dto;

public class LikeRequest {
    private Long userLikedId;

    public LikeRequest(Long userLikedId) {
        this.userLikedId = userLikedId;
    }

    public LikeRequest() {
    }

    public Long getUserLikedId() {
        return userLikedId;
    }

    public void setUserLikedId(Long userLikedId) {
        this.userLikedId = userLikedId;
    }
}
