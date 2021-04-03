package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.MatchRepository;
import fr.uge.lootin.back.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MatchRepository matchRepository;

    /**
     *
     * @param matchId
     * @param page
     * @param nb
     * @return
     */
    @GetMapping("/{matchId}/{nb}/{page}")
    public ResponseEntity<ListMessageResponse> getMsgPage(@PathVariable Long matchId, @PathVariable Integer page, @PathVariable Integer nb) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {


            verifyMatch(matchId, user);

            var res = messageService.findByMatchId(matchId,page, nb);
            return ResponseEntity.ok(new ListMessageResponse(res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable Long id){
        return ResponseEntity.ok(messageService.getById(id));
    }
    
    @GetMapping("picture/{match}/{id}")
    public ResponseEntity<MessageResponse> getMessageByIdAndMatch(@PathVariable Long match, @PathVariable Long id){
    	 var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         try {
             verifyMatch(match, user);

             return ResponseEntity.ok(messageService.getById(id));
         } catch (IllegalArgumentException e){
             return ResponseEntity.badRequest().build();
         }    	
    }

    private Match verifyMatch(Long matchId, User user) {
        var oMatch = matchRepository.findById(matchId);

        if (oMatch.isEmpty()){
            throw new IllegalArgumentException();
        }
        var match = oMatch.get();
        if (!(match.getUser1().getId() == user.getId() || match.getUser2().getId() == user.getId())){
            throw new IllegalArgumentException();
        }
        return match;
    }
}
