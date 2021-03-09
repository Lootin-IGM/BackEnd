package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.GameDto;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/games/")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody GameDto game) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(game));
    }

    @GetMapping("/{gameName}")
    public ResponseEntity<Game> getGame(@PathVariable String gameName) {
        return ResponseEntity.of(gameService.getGame(gameName));
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAll());
    }
}
