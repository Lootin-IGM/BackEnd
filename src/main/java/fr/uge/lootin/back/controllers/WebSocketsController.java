package fr.uge.lootin.back.controllers;


import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.MatchService;
import fr.uge.lootin.back.services.MessageService;
import fr.uge.lootin.back.services.UserService;
import fr.uge.lootin.back.utils.TypeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/*TODO laisser cette classe ou regrouper le tout  dans le MessageController ...*/

@Controller
public class WebSocketsController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    private void log(String text, Long sender){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        System.out.println("send -> : " + text);
        System.out.println("sender -> : " +sender);
    }

    @Transactional
    @MessageMapping("/bonjour")
    public void sendToMessage(NewMessageRequest messageRequest) throws Exception {
        System.out.println("new message text controller method called");
        log(messageRequest.getText(), messageRequest.getSender());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = Long.valueOf(authentication.getName());
        //if(userId != messageRequest.getSender()) throw new IllegalArgumentException("User" + userId + " doesn't exist");

        // check id
        var user = userService.getById(messageRequest.getSender()).orElseThrow(() -> new IllegalArgumentException("User" + userId + " doesn't exist"));

        // check user is in match
        var match = verifyUserMatch(messageRequest.getMatchId(), userId).orElseThrow(() -> new IllegalArgumentException("you have not match with id" + messageRequest.getMatchId()));

        System.out.println("LE MATCH EN QUESTION ---> " + match);
        
        Message m = new Message(match,  messageRequest.getText() , user, TypeMessage.TEXT);

        System.out.println("AVANT INSeRT");

        Message message = messageService.save(m);
        simpMessagingTemplate.convertAndSendToUser(
                messageRequest.getMatchId().toString(),
                "/text",
                NewMessageResponse.createFromMessage(message));
    }

    @Transactional
    @MessageMapping("/picture")
    public void sendToPicture(NewMessagePictureRequest messageRequest) throws Exception {
        System.out.println("new message picture controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        // check user

        Long userId = Long.valueOf(authentication.getName());
        //if(userId != messageRequest.getSender()) throw new IllegalArgumentException("User" + userId + " doesn't exist");

        // check id
        var user = userService.getById(userId).orElseThrow(() -> new IllegalArgumentException("User" + userId + " doesn't exist"));

        // check user is in match
        var match = verifyUserMatch(messageRequest.getMatchId(), userId).orElseThrow(() -> new IllegalArgumentException("you have not match with id" + messageRequest.getMatchId()));


        System.out.println("CREATION MESSAGE");
        Message m = new Message(match,  messageRequest.getPicture() , user, TypeMessage.AUDIO);


        System.out.println("AVANT INSeRT");

        Message message = messageService.save(m);
        ///System.out.println("APRES INSeRT");

        simpMessagingTemplate.convertAndSendToUser(
                messageRequest.getMatchId().toString(),
                "/picture",
                NewMessageResponse.createFromMessage(message));
    }

    private Optional<Match> verifyUserMatch(Long matchId, Long userId) {
        var oMatch = matchService.findById(matchId);

        if (oMatch.isEmpty()) return Optional.empty();
        var match = oMatch.get();
        if (!(match.getUser1().getId() == userId || match.getUser2().getId() == userId)){
            return Optional.empty();
        }
        return Optional.of(match);
    }
}

