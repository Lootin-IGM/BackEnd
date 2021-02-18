package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.LiteProfileResponse;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<LiteProfileResponse> getLiteProfile() {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var res = profileService.getLiteProfile(user);
        return ResponseEntity.ok(res);
    }
}
