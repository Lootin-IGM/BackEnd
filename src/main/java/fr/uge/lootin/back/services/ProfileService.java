package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.exception.Exceptions;
import fr.uge.lootin.back.models.Attraction;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.GameRepository;
import fr.uge.lootin.back.repositories.ImageRepository;
import fr.uge.lootin.back.repositories.MatchRepository;
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

    @Autowired
    private MatchRepository matchRepository;

    public LiteProfileResponse getLiteProfile(User user) {
        var userId = user.getId();
        user = userRepository.findById(userId).orElseThrow(() -> Exceptions.userNotFound(userId));
        List<User> userPotentialMatches;

        var userMatches = matchRepository.getUserAlreadyMatch(userId);
        var userIdMatches = new ArrayList<Long>();

        for (var m : userMatches){
            if (m.getUser1().getId() == userId){
                userIdMatches.add(m.getUser2().getId());
            }else{
                userIdMatches.add(m.getUser1().getId());
            }
        }

        switch (user.getAttraction()){
            case MEN : userPotentialMatches = userRepository.findDistinctByGamesInAndGender(user.getGames(), User.Gender.MALE); break;
            case WOMEN : userPotentialMatches = userRepository.findDistinctByGamesInAndGender(user.getGames(), User.Gender.FEMALE); break;
            case BOTH : userPotentialMatches = userRepository.findDistinctByGamesIn(user.getGames()); break;
            default:
                throw Exceptions.attractionNotFound(user.getAttraction().toString());
        }

        var res = new LiteProfileResponse();
        List<UserResponse> users = new ArrayList<>();
        Collections.shuffle(userPotentialMatches);
        var cpt = 0;
        for (var u : userPotentialMatches) {
            if (u.getId() != userId && !userIdMatches.contains(u.getId())) {
                users.add( new UserResponse(u));
                ++cpt;
            }
            if (cpt == 20) break;
        }
        res.setUsers(users);
        return res;
    }

    public FullProfileResponse getFullProfileById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> Exceptions.userNotFound(id));
        return new FullProfileResponse(user);
    }
    
    public UserResponse getSimpleProfileById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> Exceptions.userNotFound(id));
        return new UserResponse(user);
    }

    public UpdateResponse modifyDescription(User user, String description) {
        var username = user.getUsername();
        user = userRepository.findById(user.getId()).orElseThrow(() -> Exceptions.userNotFound(username));
        user.setDescription(description);
        userRepository.save(user);
        return new UpdateResponse("description updated successfully");
    }

    public UpdateResponse modifyImage(User user, byte[] image) {
        var username = user.getUsername();
        User actual = userRepository.findById(user.getId()).orElseThrow(() -> Exceptions.userNotFound(username));
        var oldImage = imageRepository.findById(actual.getImage().getId()).orElseThrow(() -> Exceptions.imageNotFound(actual.getImage().getId()));
        oldImage.setImage(image);
        imageRepository.save(oldImage);
        return new UpdateResponse("image updated successfully");
    }

    public UpdateResponse modifyGames(User user, List<String> games) {
        var username = user.getUsername();
        User actual = userRepository.findById(user.getId()).orElseThrow(() -> Exceptions.userNotFound(username));
        Set<Game> newGames= new HashSet<>();
        games.forEach(g -> newGames.add(gameRepository.findByGameName(g).orElseThrow(() -> Exceptions.gameNotFound(g))));
        actual.setGames(newGames);
        userRepository.save(actual);
        return new UpdateResponse("games updated successfully");
    }

    public DescriptionDto getMyDescritpion(User user) {
        return new DescriptionDto(user.getDescription());
    }
}
