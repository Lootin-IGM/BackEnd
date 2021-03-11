package fr.uge.lootin.back.dto;

import java.util.List;

public class ListMatches {

    private List<MatchResponse> data;
    public ListMatches(List<MatchResponse> data) {
        this.data = data;
    }
    public ListMatches() {}

    public List<MatchResponse> getData() {
        return data;
    }

    public void setData(List<MatchResponse> data) {
        this.data = data;
    }
}
