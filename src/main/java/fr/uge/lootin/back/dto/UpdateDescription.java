package fr.uge.lootin.back.dto;

public class UpdateDescription {
    private String description;

    public UpdateDescription(){}

    public UpdateDescription(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
