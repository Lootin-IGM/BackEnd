package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.LiteProfileResponse;
import fr.uge.lootin.back.dto.UserResponse;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public LiteProfileResponse getLiteProfile(User user) {
        user = userRepository.findById(user.getId()).get();
        var tmp = userRepository.findDistinctByGamesIn(user.getGames());
        var res = new LiteProfileResponse();
        List<UserResponse> users = new ArrayList<>();
        Collections.shuffle(tmp);
        var cpt = 0;
        for (var u : tmp) {
            if (u.getId() != user.getId()) {
                users.add( new UserResponse(u.getId(), u.getFirstName(), u.getLastName(), u.getLogin().getUsername()));
                ++cpt;
            }
            if (cpt == 20) break;
        }
        res.setUsers(users);
        return res;
    }
}
