package fr.uge.lootin.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/user");
        config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Ici que le client se connecte en premier lieu pour le handshake
        registry.addEndpoint("/secured/room").setAllowedOrigins("*");
        //registry.addEndpoint("/secured/room").setAllowedOrigins("http://localhost:8080");

    }

}
