package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.FullProfileResponse;
import fr.uge.lootin.back.dto.LiteProfileResponse;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<LiteProfileResponse> getLiteProfiles() {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var res = profileService.getLiteProfile(user);
        return ResponseEntity.ok(res);
    }

    @GetMapping("full/{id}")
    public ResponseEntity<FullProfileResponse> getFullProfileById(@PathVariable String id) {
        var res = profileService.getFullProfileById(Long.valueOf(id));
        return ResponseEntity.ok(res);
    }
}
