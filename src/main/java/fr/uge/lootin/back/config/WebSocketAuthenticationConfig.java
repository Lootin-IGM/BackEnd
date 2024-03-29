package fr.uge.lootin.back.config;


import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.modelsWS.Authority;
import fr.uge.lootin.back.services.UserService;
import fr.uge.lootin.back.utils.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketAuthenticationConfig.class);

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {



            @Autowired
            private JwtUtil jwtUtil;

            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authorization = accessor.getNativeHeader("X-Authorization");
                    logger.debug("X-Authorization: {}", authorization);
                    String accessToken = authorization.get(0).split(" ")[1];
                    System.out.println("id : " + accessToken);
                    System.out.println(" OKAY 1 " + Long.valueOf(accessToken.split("_")[0]));
                    Optional<User> user = userService.getById(Long.valueOf(accessToken.split("_")[0]));
                    System.out.println(" OKAY 2");

                    System.out.println("OKAYTEST1 : " + user.get().getChannelToken());
                    System.out.println("OKAYTEST2 : " + accessToken.split("_")[1]);

                    /*if (!(user.get().getChannelToken().equals(accessToken.split("_")[1]))) {
                        throw new BadCredentialsException("Bad credentials for user ");
                    }*/
                    System.out.println(" OKAY 3");

                    Set set = new HashSet<GrantedAuthority>();
                    set.add(new Authority("User"));
                    System.out.println(" OKAY 4");
                    Authentication authentication = new UsernamePasswordAuthenticationToken(Long.valueOf(accessToken.split("_")[0]), Long.valueOf(accessToken.split("_")[0]), set);
                    accessor.setUser(authentication);
                    System.out.println(" OKAY 5");

                }
                return message;
            }
        });
    }
}

