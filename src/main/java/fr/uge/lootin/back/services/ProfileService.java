package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.FullProfileResponse;
import fr.uge.lootin.back.dto.LiteProfileResponse;
import fr.uge.lootin.back.dto.UpdateResponse;
import fr.uge.lootin.back.dto.UserResponse;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.GameRepository;
import fr.uge.lootin.back.repositories.ImageRepository;
import fr.uge.lootin.back.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfileService {
    private String SUCCES_UPDATE = "informations updated successfully";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private GameRepository gameRepository;

    public LiteProfileResponse getLiteProfile(User user) {
        user = userRepository.findById(user.getId()).get();
        var tmp = userRepository.findDistinctByGamesIn(user.getGames());
        var res = new LiteProfileResponse();
        List<UserResponse> users = new ArrayList<>();
        Collections.shuffle(tmp);
        var cpt = 0;
        for (var u : tmp) {
            if (u.getId() != user.getId()) {
                users.add( new UserResponse(u));
                ++cpt;
            }
            if (cpt == 20) break;
        }
        res.setUsers(users);
        return res;
    }

    public FullProfileResponse getFullProfileById(Long id) {
        var user = userRepository.findById(id).get();
        return new FullProfileResponse(user);
    }

    public UpdateResponse modifyDescription(User user, String description) {
        user = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        user.setDescription(description);
        userRepository.save(user);
        return new UpdateResponse("description updated successfully");
    }

    public UpdateResponse modifyImage(User user, byte[] image) {
        User actual = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        var oldImage = imageRepository.findById(actual.getImage().getId()).orElseThrow(() -> new IllegalArgumentException("image " + actual.getImage().getId() + " not found"));
        oldImage.setImage(image);
        imageRepository.save(oldImage);
        return new UpdateResponse("description updated successfully");
    }

    public UpdateResponse modifyGames(User user, List<String> games) {
        User actual = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        Set<Game> newGames= new HashSet<>();
        games.forEach(g -> newGames.add(gameRepository.findByGameName(g).orElseThrow(() -> new IllegalArgumentException("game " + g + " doesn't exist"))));
        actual.setGames(newGames);
        userRepository.save(actual);
        return new UpdateResponse("description updated successfully");
    }
}
