package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.*;
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
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MatchRepository matchRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    /**
     * TODO supprimer ça
     * @param newMessageRequest
     * @param user
     * @return
     */
    public NewMessageResponse newMessageOLD(NewMessageRequest newMessageRequest, User user){
        var match = verifyMatch(newMessageRequest.getMatchId(), user);
        var msg = save(new Message(match, newMessageRequest.getText(), user, TypeMessage.TEXT));
        MatchResponse mr;
        if (match.getUser1().getId() == user.getId()){
            return new NewMessageResponse(msg, match.getUser2());
        }else{
            return new NewMessageResponse(msg, match.getUser1());
        }
    }

    public Message newMessage(String content, Match match, User user, TypeMessage typeMessage){
        return save(new Message(match, content, user, typeMessage));
    }

    public List<MessageResponse> findByMatchId(MessageRequest messageRequest, User user){
        verifyMatch(messageRequest.getMatchId(), user);
        var page = PageRequest.of(messageRequest.getPage(), messageRequest.getNb(), Sort.by("sendTime").descending());

        var res = messageRepository.findByMatchId(messageRequest.getMatchId(), page);
        var formatRes = new ArrayList<MessageResponse>();
        for (var m  : res){
            formatRes.add(new MessageResponse(m));
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
        return new MessageResponse(msg);
    }
}
