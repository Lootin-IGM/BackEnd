package fr.uge.lootin.back.services;

import fr.uge.lootin.back.models.Image;
import fr.uge.lootin.back.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image save(Image image){
        return imageRepository.save(image);
    }

    public Optional<Image> getImage(Long imageId) {
        return imageRepository.findById(imageId);
    }
}
