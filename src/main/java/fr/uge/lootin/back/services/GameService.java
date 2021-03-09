package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.GameDto;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Image;
import fr.uge.lootin.back.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ImageService imageService;

    public Game save(GameDto game) {
        Image image = imageService.getImage(game.getImageId()).orElseThrow(() -> new IllegalArgumentException("Image " + game.getImageId() + " doesn't exist"));
        Game saveGame = new Game(game.getGameName(), image);
        return gameRepository.save(saveGame);
    }

    public List<Game> getAll() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        return games;
    }

    public Optional<Game> getGame(String gameName) {
        return gameRepository.findByGameName(gameName);
    }
}
