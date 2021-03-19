package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.ImageService;
import fr.uge.lootin.back.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/description")
    public ResponseEntity<UpdateResponse> modifyDescription(@RequestBody UpdateDescription description) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var res = profileService.modifyDescription(user, description.getDescription());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/image")
    public ResponseEntity<UpdateResponse> modifyImage(@RequestParam("image") MultipartFile file) throws IOException {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        byte[] image = file.getBytes();
        var res = profileService.modifyImage(user, image);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/games")
    public ResponseEntity<UpdateResponse> modifyGames(@RequestBody UpdateGames games) {
        var user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var res = profileService.modifyGames(user, games.getGames());
        return ResponseEntity.ok(res);
    }
}
