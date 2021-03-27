package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.mappers.MessageMapper;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.MatchRepository;
import fr.uge.lootin.back.repositories.MessageRepository;
import fr.uge.lootin.back.utils.TypeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MatchRepository matchRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public Message newMessage(String content, Match match, User user, TypeMessage typeMessage){
        return save(new Message(match, content, user, typeMessage));
    }
    public List<MessageResponse> findByMatchId(Long matchId, int pages, int sizePage){
        var page = PageRequest.of(pages, sizePage, Sort.by("sendTime").descending());
        System.out.println("avant requete pour les avoir");

        var res = messageRepository.findByMatchId(matchId, page);
        System.out.println("après la requete les rhey");
        System.out.println(res.toString());

        return res.stream().map(MessageMapper.INSTANCE::toMessageResponse).collect(Collectors.toList());
    }




    public MessageResponse getById(Long id) {
        var msg = messageRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return MessageMapper.INSTANCE.toMessageResponse(msg);
    }
}
