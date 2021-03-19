package fr.uge.lootin.back.dto;

public class UpdateResponse {
    private String response;

    public UpdateResponse(){}

    public UpdateResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
