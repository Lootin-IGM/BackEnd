package fr.uge.lootin.back.controllers;


import fr.uge.lootin.back.modelsWS.MessageReceived;
import fr.uge.lootin.back.modelsWS.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class GreetingController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;


    @MessageMapping("/hello")
    public void mirrorMessage(MessageSend message) throws Exception {
        System.out.println("hello greeting controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        LocalDateTime now = LocalDateTime.now();
        simpMessagingTemplate.convertAndSendToUser(currentPrincipalName, "/reply", new MessageReceived(message.getId_author(), message.getContent(), now));
    }

    @MessageMapping("/bonjour")
    public void sendToMessage(MessageSend message) throws Exception {
        System.out.println("hello greeting controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        LocalDateTime now = LocalDateTime.now();
        simpMessagingTemplate.convertAndSendToUser(message.getSendTo().toString(), "/reply", new MessageReceived(message.getId_author(), message.getContent(), now));
    }

}
