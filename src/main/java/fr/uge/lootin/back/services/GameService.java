package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.AllGamesDto;
import fr.uge.lootin.back.dto.GameDto;
import fr.uge.lootin.back.exception.Exceptions;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Image;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    public Game save(GameDto game) {
        Image image = imageService.getImage(game.getImageId()).orElseThrow(() -> Exceptions.imageNotFound(game.getImageId()));
        Game saveGame = new Game(game.getGameName(), image);
        return gameRepository.save(saveGame);
    }

    public AllGamesDto getAll() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        AllGamesDto res = new AllGamesDto(games);
        return res;
    }

    public Optional<Game> getGame(String gameName) {
        return gameRepository.findByGameName(gameName);
    }

    public AllGamesDto getGamesForUser(User user) {
        User actual = userService.getById(user.getId()).orElseThrow(() -> Exceptions.userNotFound(user.getUsername()));
        AllGamesDto res = new AllGamesDto(new ArrayList<>(actual.getGames()));
        return res;
    }
}
