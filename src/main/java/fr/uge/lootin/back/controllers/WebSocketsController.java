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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

    @MessageMapping("/bonjour")
    public void sendToMessage(NewMessageRequest messageRequest) throws Exception {
        System.out.println("hello greeting text controller method called");
        log(messageRequest.getText(), messageRequest.getSender());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Long userId = Long.valueOf(currentPrincipalName);
        if(userId != messageRequest.getSender()) return;

        var optUser = userService.getById(userId);
        if(optUser.isEmpty()) return;

        // check id
        if(userId != messageRequest.getSender()) return;
        // check user is in match
        var optMatch = verifyUserMatch(messageRequest.getMatchId(), userId);
        if(optUser.isEmpty()) return;

        Message message = messageService.newMessage(messageRequest.getText(), optMatch.get(), optUser.get(), TypeMessage.TEXT);
        simpMessagingTemplate.convertAndSendToUser(
                messageRequest.getMatchId().toString(),
                "/text",
                NewMessageResponse.createFromMessage(message));
    }

    @MessageMapping("/picture")
    public void sendToPicture(NewMessagePictureRequest messageRequest) throws Exception {
        System.out.println("hello greeting picture controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);

        // check user

        Long userId = Long.valueOf(currentPrincipalName);
        if(userId != messageRequest.getSender()) return;

        var optUser = userService.getById(userId);
        if(optUser.isEmpty()) return;

        // check id
        if(userId != messageRequest.getSender()) return;
        // check user is in match
        var optMatch = verifyUserMatch(messageRequest.getMatchId(), userId);
        if(optUser.isEmpty()) return;

        Message message = messageService.newMessage(messageRequest.getPicture(), optMatch.get(), optUser.get(), TypeMessage.AUDIO);
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

