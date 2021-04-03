package fr.uge.lootin.back.dto;

public class MessageRequest {
    private int nb;
    private int page;
    private Long matchId;

    public MessageRequest(int nb, int page, Long matchId) {
        this.nb = nb;
        this.page = page;
        this.matchId = matchId;
    }

    public MessageRequest() {
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
