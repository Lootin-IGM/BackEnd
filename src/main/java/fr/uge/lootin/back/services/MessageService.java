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

    public List<MessageResponse> findByMatchId(MessageRequest messageRequest, User user){
        verifyMatch(messageRequest.getMatchId(), user);
        var page = PageRequest.of(messageRequest.getPage(), messageRequest.getNb(), Sort.by("sendTime").descending());

        var res = messageRepository.findByMatchId(messageRequest.getMatchId(), page);
        var formatRes = new ArrayList<MessageResponse>();
        for (Message m  : res){
            formatRes.add(MessageMapper.INSTANCE.toMessageResponse(m));
        }
        return formatRes;
    }

    private Match verifyMatch(Long matchId, User user) {
        var oMatch = matchRepository.findById(matchId);

        if (oMatch.isEmpty()){
            throw new IllegalArgumentException();
        }
        var match = oMatch.get();
        if (!(match.getUser1().getId() == user.getId() || match.getUser2().getId() == user.getId())){
            throw new IllegalArgumentException();
        }
        return match;
    }


    public MessageResponse getById(Long id) {
        var msg = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        return MessageMapper.INSTANCE.toMessageResponse(msg);
    }
}
