package fr.uge.lootin.back.dto;

import org.springframework.web.multipart.MultipartFile;

public class GameDto {
    private String gameName;
    private MultipartFile file;

    public GameDto(){}

    public GameDto(String gameName, MultipartFile file) {
        this.gameName = gameName;
        this.file = file;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameName='" + gameName + '\'' +
                '}';
    }
}
