package fr.uge.lootin.back.dto;

public class LikeResponse {
    private boolean newMatch;
    private boolean newLike;

    public LikeResponse(boolean newMatch, boolean newLike) {
        this.newMatch = newMatch;
        this.newLike = newLike;
    }

    public boolean isNewMatch() {
        return newMatch;
    }

    public void setNewMatch(boolean newMatch) {
        this.newMatch = newMatch;
    }

    public boolean isNewLike() {
        return newLike;
    }

    public void setNewLike(boolean newLike) {
        this.newLike = newLike;
    }
}
