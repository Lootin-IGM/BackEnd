package fr.uge.lootin.back.services;

import fr.uge.lootin.back.model.Game;
import fr.uge.lootin.back.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
