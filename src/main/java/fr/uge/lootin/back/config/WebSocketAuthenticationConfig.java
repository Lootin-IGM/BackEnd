package fr.uge.lootin.back.config;



import fr.uge.lootin.back.modelsWS.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketAuthenticationConfig.class);


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authorization = accessor.getNativeHeader("X-Authorization");
                    logger.debug("X-Authorization: {}", authorization);

                    String accessToken = authorization.get(0).split(" ")[1];
                    System.out.println("id : " + accessToken);
                    /*Jwt jwt = jwtDecoder.decode(accessToken);
                    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
                    Authentication authentication = converter.convert(jwt);*/
                    Set set = new HashSet<GrantedAuthority>();
                    set.add(new Authority("User"));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(accessToken, accessToken, set);
                    accessor.setUser(authentication);
                }
                return message;
            }
        });
    }
}

