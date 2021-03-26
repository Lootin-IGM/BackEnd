package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;
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

    @PostMapping("/empty")
    public ResponseEntity<ListMatches> getEmptyMatchesPage(@Valid @RequestBody MatchRequest matchRequest) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new ListMatches(matchService.getEmptyMatchesPage(user, matchRequest)));
    }

    @PostMapping("/lastMsg")
    public ResponseEntity<ListMatches> getMatchesLastMsgPage(@Valid @RequestBody MatchRequest matchRequest) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new ListMatches(matchService.getMatchesLastMsgPage(user, matchRequest)));
    }

    @GetMapping("/test")
    public void test() throws Exception {
        System.out.println("hello greeting controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        LocalDateTime now = LocalDateTime.now();
        simpMessagingTemplate.convertAndSendToUser("4", "/notification", new Notification("match", "new match"));
    }


}
