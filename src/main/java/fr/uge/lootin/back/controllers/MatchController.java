package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.MatchRequest;
import fr.uge.lootin.back.dto.MatchResponse;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        User principal =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(matchService.getMatchs(principal));
    }

    @PostMapping
    public ResponseEntity<List<MatchResponse>> getMatchesPage(@Valid @RequestBody MatchRequest matchRequest) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(matchService.getMatchesPage(user, matchRequest));
    }


}
