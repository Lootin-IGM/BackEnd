package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Supprimer ceci :)
     * @param newMessageRequest contains
     * @return
     */
    /*
    @PostMapping
    public ResponseEntity<NewMessageResponse> newMessage(@Valid @RequestBody NewMessageRequest newMessageRequest){

        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{

            NewMessageResponse res = null ; //messageService.newMessage(newMessageRequest, user);
            return ResponseEntity.ok(res);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
    */

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
            var res = messageService.findByMatchId(new MessageRequest(nb, page, matchId), user);
            return ResponseEntity.ok(new ListMessageResponse(res));
        }catch (IllegalArgumentException e){
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

}
