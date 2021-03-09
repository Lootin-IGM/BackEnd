package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.models.Image;
import fr.uge.lootin.back.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("/images/")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Image> upload(@RequestParam("name") String name, @RequestParam("image") MultipartFile file) throws IOException {
        byte[] image = file.getBytes();
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.save(new Image(name, image)));
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> get(@PathVariable Long imageId){
        return ResponseEntity.of(imageService.getImage(imageId));
    }
}
