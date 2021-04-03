package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.AllGamesDto;
import fr.uge.lootin.back.dto.GameDto;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@Validated
@RequestMapping("/games/")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestParam("name") String name, @RequestParam("image") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(name, file));
    }

    @GetMapping("/{gameName}")
    public ResponseEntity<Game> getGame(@PathVariable String gameName) {
        return ResponseEntity.of(gameService.getGame(gameName));
    }

    @GetMapping
    public ResponseEntity<AllGamesDto> getAllGames() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/my")
    public ResponseEntity<AllGamesDto> getGamesForUser() {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(gameService.getGamesForUser(user));
    }
}
