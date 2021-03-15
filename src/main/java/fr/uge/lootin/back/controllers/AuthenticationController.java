package fr.uge.lootin.back.controllers;

import fr.uge.lootin.back.dto.LoginRequest;
import fr.uge.lootin.back.dto.LoginResponse;
import fr.uge.lootin.back.dto.RegisterRequest;
import fr.uge.lootin.back.dto.RegisterResponse;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.services.AuthenticationService;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@Validated
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/login")
    @PostMapping
    public LoginResponse createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) throws  Exception {
       return authenticationService.login(loginRequest);
    }

    @RequestMapping("/register")
    @PostMapping
    public RegisterResponse registerUser(@Valid RegisterRequest registerRequest) throws IOException {
        return authenticationService.register(registerRequest);
    }

    @RequestMapping("/showLogin")
    @GetMapping
    public String showLogin() {
        User principal =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "User " + principal.getUsername() + " is currently logged in";
    }

}
