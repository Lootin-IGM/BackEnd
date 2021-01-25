package fr.uge.lootin.back.services;

import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
