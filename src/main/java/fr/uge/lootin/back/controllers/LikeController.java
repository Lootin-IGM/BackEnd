package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.LikeRequest;
import fr.uge.lootin.back.dto.LikeResponse;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeResponse> like(@Valid @RequestBody LikeRequest likeRequest) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var res = likeService.addLike(user, likeRequest.getUserLikedId());
        return ResponseEntity.ok(res);
    }
}
