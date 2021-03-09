package fr.uge.lootin.back.dto;

public class GameDto {
    private String gameName;
    private Long imageId;

    public GameDto(){}

    public GameDto(String gameName, Long imageId) {
        this.gameName = gameName;
        this.imageId = imageId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameName='" + gameName + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
