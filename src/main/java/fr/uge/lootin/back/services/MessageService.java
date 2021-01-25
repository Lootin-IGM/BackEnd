package fr.uge.lootin.back.services;

import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

}
