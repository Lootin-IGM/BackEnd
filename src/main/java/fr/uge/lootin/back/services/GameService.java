package fr.uge.lootin.back.services;

import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> getAll() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        return games;
    }

    public Optional<Game> getGame(String gameName) {
        return gameRepository.findByGameName(gameName);
    }
}
