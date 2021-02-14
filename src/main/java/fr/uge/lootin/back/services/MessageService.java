package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.*;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.MatchRepository;
import fr.uge.lootin.back.repositories.MessageRepository;
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

    public NewMessageResponse newMessage(NewMessageRequest newMessageRequest, User user){
        var match = verifyMatch(newMessageRequest.getMatchId(), user);
        var msg = save(new Message(match, newMessageRequest.getText(), user));
        MatchResponse mr;
        if (match.getUser1().getId() == user.getId()){
            return new NewMessageResponse(match.getId(),new UserResponse(match.getUser2().getFirstName(), match.getUser2().getLastName(), match.getUser2().getLogin().getUsername()), msg.getSendTime(), msg.getMessage());
        }else{
            return new NewMessageResponse(match.getId(),new UserResponse(match.getUser1().getFirstName(), match.getUser1().getLastName(), match.getUser1().getLogin().getUsername()), msg.getSendTime(), msg.getMessage());
        }
    }

    public List<MessageResponse> findByMatchId(MessageRequest messageRequest, User user){
        verifyMatch(messageRequest.getMatchId(), user);
        var page = PageRequest.of(messageRequest.getPage(), messageRequest.getNb(), Sort.by("sendTime").descending());

        var res = messageRepository.findByMatchId(messageRequest.getMatchId(), page);
        var formatRes = new ArrayList<MessageResponse>();
        for (var m  : res){
            var u = m.getUser();
            formatRes.add(new MessageResponse(m.getSendTime(), m.getMessage(), new UserResponse(u.getFirstName(), u.getLastName(), u.getLogin().getUsername())));
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

}
