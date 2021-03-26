package fr.uge.lootin.back.controllers;


import fr.uge.lootin.back.dto.*;
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

@Controller
public class GreetingController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;


    @MessageMapping("/hello")
    public void mirrorMessage(NewMessageRequest message) throws Exception {
        System.out.println("hello greeting controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        Timestamp ts= new Timestamp(System.currentTimeMillis());
        String time = ts.getDay() + " " + ts.getHours() + ":" + ts.getMinutes();


        simpMessagingTemplate.convertAndSendToUser(currentPrincipalName, "/text", new NewMessageResponse(0L, 1L, time, message.getText()));
    }

    @MessageMapping("/bonjour")
    public void sendToMessage(NewMessageRequest message) throws Exception {
        System.out.println("hello greeting bonjour controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        System.out.println("send -> : " +message.getText());
        System.out.println("sender -> : " +message.getSender());

        Timestamp ts= new Timestamp(System.currentTimeMillis());
        LocalDateTime ldt = ts.toLocalDateTime();
        String time = ldt.getDayOfWeek() + ", " + ts.getHours() + ":" + ts.getMinutes();

        simpMessagingTemplate.convertAndSendToUser(message.getMatchId().toString(), "/text", new NewMessageResponse(0L, message.getSender(), time, message.getText()));

    }

    @MessageMapping("/picture")
    public void sendToPicture(NewMessagePictureRequest message) throws Exception {
        System.out.println("hello greeting bonjour controller method called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user : " + currentPrincipalName);
        System.out.println("Connected users : " + simpUserRegistry.getUsers());
        //System.out.println("IMAGE -> " + message.getPicture());
        System.out.println("MATCH ID -> : " + message.getMatchId());

        Timestamp ts= new Timestamp(System.currentTimeMillis());
        LocalDateTime ldt = ts.toLocalDateTime();
        String time = ldt.getDayOfWeek() + ", " + ts.getHours() + ":" + ts.getMinutes();

        simpMessagingTemplate.convertAndSendToUser(message.getMatchId().toString(), "/picture", new NewMessagePictureResponse(0L, message.getSender(), time, message.getPicture()));

    }
}
