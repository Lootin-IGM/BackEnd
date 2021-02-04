package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.MessageRequest;
import fr.uge.lootin.back.dto.MessageResponse;
import fr.uge.lootin.back.dto.NewMessageRequest;
import fr.uge.lootin.back.dto.NewMessageResponse;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/newMessage")
    public ResponseEntity<NewMessageResponse> newMessage(@Valid @RequestBody NewMessageRequest newMessageRequest){
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            var res = messageService.newMessage(newMessageRequest, user);
            return ResponseEntity.ok(res);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }



    }

    @PostMapping
    public ResponseEntity<List<MessageResponse>> getMsgPage(@Valid @RequestBody MessageRequest messageRequest) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            var res = messageService.findByMatchId(messageRequest, user);
            return ResponseEntity.ok(res);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
