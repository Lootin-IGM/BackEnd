package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Validated
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @PostMapping
    public ResponseEntity<LikeResponse> like(@Valid @RequestBody LikeRequest likeRequest) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var res = likeService.addLike(user, likeRequest.getUserLikedId());
        if (res.isNewMatch()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("loot detected between :" + Long.valueOf(user.getId()).toString() + " and " + likeRequest.getUserLikedId().toString());

            simpMessagingTemplate.convertAndSendToUser(Long.valueOf(user.getId()).toString(), "/notification", new Notification("loot", likeRequest.getUserLikedId().toString()));
            simpMessagingTemplate.convertAndSendToUser(likeRequest.getUserLikedId().toString(), "/notification", new Notification("loot", "0"));
        }
        return ResponseEntity.ok(res);
    }
}