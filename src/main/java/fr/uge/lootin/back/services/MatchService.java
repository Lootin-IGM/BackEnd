package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.MatchRequest;
import fr.uge.lootin.back.dto.MatchResponse;
import fr.uge.lootin.back.dto.MessageResponse;
import fr.uge.lootin.back.dto.UserResponse;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.Message;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.MatchRepository;
import fr.uge.lootin.back.repositories.MessageRepository;
import fr.uge.lootin.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    private void findMatchWithGame(User actualUser, Game game, List<Match> res){
        List<User> matchs = userRepository.findByGamesGameName(game.getGameName());
        for (User u: matchs) {
            if(!u.equals(actualUser)) {
                Match m = new Match(u, actualUser);
                matchRepository.save(m);
                res.add(m);
            }
        }
    }

    //TODO improve to not get all match and don't get same matchs twice
    //TODO gérer les Optionals
    //TODO ne get que les nouveaux matchs
    //TODO utiliser un matchDto
    public List<Match> getMatchs(User principal) {
        List<User> users = new ArrayList<>();
        List<Match> matchs = new ArrayList<>();
        Optional<User> p = userRepository.findByLoginUsername(principal.getUsername());
        /*
        p.get().getGames().forEach(x -> userRepository.findByGamesGameName(x.getGameName()).forEach(y -> users.add(y)));

        users.forEach(x -> matchs.add(new Match(p.get(), x)));*/
        p.get().getGames().forEach(x -> findMatchWithGame(p.get(), x, matchs));
        return matchs;
    }

    public List<MatchResponse> getMatchesPage(User user, MatchRequest matchRequest){
        var page = PageRequest.of(matchRequest.getPage(), matchRequest.getNbMatches(), Sort.by("id").descending());

        var res =  matchRepository.findByUser1IdOrUser2Id(user.getId(), user.getId(), page);

        page = PageRequest.of(0, 1, Sort.by("sendTime").descending() );
        List<Message> listMessage;
        Message lastMessage;
        MessageResponse messageResponse;
        var formatRes = new ArrayList<MatchResponse>();
        for (var m  : res){
            listMessage = messageRepository.findByMatchId(m.getId(), page);
            if(!listMessage.isEmpty()){
                lastMessage = listMessage.get(0);
                messageResponse = new MessageResponse(lastMessage);
            }else{
                messageResponse = null;
            }
            if (m.getUser1().getId() == user.getId()){
                formatRes.add(new MatchResponse(m.getId(), new UserResponse(m.getUser2()), messageResponse));
            }else{
                formatRes.add(new MatchResponse(m.getId(),new UserResponse(m.getUser1()), messageResponse));
            }
        }

        return formatRes;
    }

    public List<MatchResponse> getEmptyMatchesPage(User user, MatchRequest matchRequest) {
        var page = PageRequest.of(matchRequest.getPage(), matchRequest.getNbMatches(), Sort.by("id").descending());

        var res = matchRepository.getEmptyMatches(user.getId(),page);

        var formatRes = new ArrayList<MatchResponse>();

        for (var m  : res){
            if (m.getUser1().getId() == user.getId()){
                formatRes.add(new MatchResponse(m.getId(), new UserResponse(m.getUser2()), null));
            }else{
                formatRes.add(new MatchResponse(m.getId(),new UserResponse(m.getUser1()), null));
            }
        }
        return formatRes;
    }


    public List<MatchResponse> getMatchesLastMsgPage(User user, MatchRequest matchRequest){
        var page = PageRequest.of(matchRequest.getPage(), matchRequest.getNbMatches(), Sort.by("msg.sendTime").descending());

        var res =  matchRepository.getMatchesLastMsg(user.getId(), page);

        List<Message> listMessage;
        Message lastMessage;
        MessageResponse messageResponse;
        var formatRes = new ArrayList<MatchResponse>();
        for (var m  : res){
            listMessage = messageRepository.findByMatch_IdOrderBySendTimeDesc(m.getId());
            if(!listMessage.isEmpty()){
                lastMessage = listMessage.get(0);
                messageResponse = new MessageResponse(lastMessage);
            }else{
                messageResponse = null;
            }
            if (m.getUser1().getId() == user.getId()){
                formatRes.add(new MatchResponse(m.getId(), new UserResponse(m.getUser2()), messageResponse));
            }else{
                formatRes.add(new MatchResponse(m.getId(),new UserResponse(m.getUser1()), messageResponse));
            }
        }

        return formatRes;
    }

}
