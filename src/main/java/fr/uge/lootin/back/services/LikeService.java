package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.LikeResponse;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Like;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.LikeRepository;
import fr.uge.lootin.back.repositories.MatchRepository;
import fr.uge.lootin.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    public LikeResponse addLike(User user, Long userIdLiked) {
        var testLike = likeRepository.findByUserIdAndUserLikedId(user.getId(), userIdLiked);
        var testMatch = likeRepository.findByUserIdAndUserLikedId(userIdLiked, user.getId());
        var userLiked = userRepository.findById(userIdLiked).get();
        if (!testMatch.isEmpty()){
            likeRepository.save(new Like(user, userLiked));
            createMatch(user, userLiked);
            return new LikeResponse(true, true);
        }
        if (!testLike.isEmpty()){
            return new LikeResponse(false, false);
        }

        likeRepository.save(new Like(user, userLiked));
        return new LikeResponse(false, true);
    }

    private void createMatch(User user, User userLiked) {
        var testMatch = matchRepository.findMatch(user.getId(), userLiked.getId());
        if (testMatch.isEmpty()){
            matchRepository.save(new Match(user, userLiked));
        }
    }


}
