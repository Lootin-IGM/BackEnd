package fr.uge.lootin.back.dto;

public class MatchRequest {
    private int nbMatches;
    private int page;

    public MatchRequest() {
    }

    public MatchRequest(int nbMatches, int page) {
        this.nbMatches = nbMatches;
        this.page = page;
    }

    public int getNbMatches() {
        return nbMatches;
    }

    public void setNbMatches(int nbMatches) {
        this.nbMatches = nbMatches;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
