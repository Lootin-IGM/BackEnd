package fr.uge.lootin.back.services;

import fr.uge.lootin.back.model.Login;
import fr.uge.lootin.back.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login save(Login login) {
        return loginRepository.save(login);
    }
}
