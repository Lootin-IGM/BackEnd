package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.LoginRequest;
import fr.uge.lootin.back.dto.LoginResponse;
import fr.uge.lootin.back.dto.RegisterRequest;
import fr.uge.lootin.back.dto.RegisterResponse;
import fr.uge.lootin.back.exception.Exceptions;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Image;
import fr.uge.lootin.back.models.Login;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.GameRepository;
import fr.uge.lootin.back.repositories.ImageRepository;
import fr.uge.lootin.back.repositories.UserRepository;
import fr.uge.lootin.back.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
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

    @Autowired
    private ImageRepository imageRepository;

    public LoginResponse login(LoginRequest loginRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw Exceptions.INVALID_CREDENTIALS;
        }
        System.out.println("received valid Username : " + loginRequest.getUsername() + " password : " + loginRequest.getPassword());
        User user = userService.loadUserByUsername(loginRequest.getUsername());
        final String jtwToken = jwtUtil.generateToken(user);
        return new LoginResponse(jtwToken, user.getChannelToken(), user.getId());
    }

    public RegisterResponse register(RegisterRequest registerRequest) throws IOException {
    	Optional<User> testUser = userRepository.findByLoginUsername(registerRequest.getUsername());
    	if(!testUser.isEmpty()) {
    		throw Exceptions.usernameAlreadyExist(registerRequest.getUsername());
    	}
    	testUser = userRepository.findByEmail(registerRequest.getEmail());
    	if(!testUser.isEmpty()) {
    		throw Exceptions.emailAlreadyExist(registerRequest.getEmail());
    	}
        User user = new User();
        Login login = new Login();
        Image image = new Image();
        System.out.println("username : " + registerRequest.getUsername() + " " + "password : " + registerRequest.getPassword() + " " + "firstname : " + registerRequest.getFirstName() + " " + "lastname : " + registerRequest.getLastName() + " " + "games : " + registerRequest.getGames());

        image.setName(registerRequest.getUsername());
        byte[] imageGetted = registerRequest.getFile().getBytes();
        image.setImage(imageGetted);
        login.setUsername(registerRequest.getUsername());
        login.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setChannelToken(java.util.UUID.randomUUID().toString());
        user.setAuthority(User.Authority.USER);
        user.setLogin(login);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setDescription(registerRequest.getDescription());
        user.setAge(registerRequest.getAge());
        user.setImage(image);
        user.setAttraction(registerRequest.getAttraction());
        if(!registerRequest.getEmail().contains("@")) {
            throw Exceptions.INVALID_EMAIL_ADDRESS;
        }
        user.setEmail(registerRequest.getEmail());
        if (registerRequest.getGender().equals("F")) {
            user.setGender(User.Gender.FEMALE);
        }else{
            user.setGender(User.Gender.MALE);
        }


        Set<Game> targetSet = new HashSet<>();
        //TODO a améliorer avec l'Optional
        registerRequest.getGames().forEach(x -> {System.out.println("game=" + x); targetSet.add(gameRepository.findByGameName(x).orElseThrow(() -> new IllegalArgumentException("game " + x + " doesn't exist")));});
        user.setGames(targetSet);
        imageRepository.save(image);
        userRepository.save(user);
        return new RegisterResponse(registerRequest.getUsername());
    }
}
