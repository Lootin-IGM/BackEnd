package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.LoginRequest;
import fr.uge.lootin.back.dto.LoginResponse;
import fr.uge.lootin.back.dto.RegisterRequest;
import fr.uge.lootin.back.dto.RegisterResponse;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Login;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.GameRepository;
import fr.uge.lootin.back.repositories.UserRepository;
import fr.uge.lootin.back.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        System.out.println("received Username : " + loginRequest.getUsername() + " password : " + loginRequest.getPassword());
        User user = userService.loadUserByUsername(loginRequest.getUsername());
        final String jtwToken = jwtUtil.generateToken(user);
        return new LoginResponse(jtwToken);
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User();
        Login login = new Login();
        System.out.println("username : " + registerRequest.getUsername() + " " + "password : " + registerRequest.getPassword() + " " + "firstname : " + registerRequest.getFirstName() + " " + "lastname : " + registerRequest.getLastName() + " " + "games : " + registerRequest.getGames());
        /*
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));*/
        login.setUsername(registerRequest.getUsername());
        login.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAuthority(User.Authority.USER);
        user.setLogin(login);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());

        Set<Game> targetSet = new HashSet<>();
        //TODO a améliorer avec l'Optional
        registerRequest.getGames().forEach(x -> targetSet.add(gameRepository.findByGameName(x).get()));
        user.setGames(targetSet);
        userRepository.save(user);
        return new RegisterResponse(registerRequest.getUsername());
    }
}
