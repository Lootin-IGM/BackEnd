package fr.uge.lootin.back.dto;

public class DescriptionDto {
    private String description;

    public DescriptionDto(){}

    public DescriptionDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
