package fr.uge.lootin.back.dto;

import java.util.List;

public class ListMessageResponse {

    private List<MessageResponse> data;

    public ListMessageResponse(List<MessageResponse> data) {
        this.data = data;
    }
    public ListMessageResponse() {
    }

    public List<MessageResponse> getData() {
        return data;
    }

    public void setData(List<MessageResponse> data) {
        this.data = data;
    }
}
