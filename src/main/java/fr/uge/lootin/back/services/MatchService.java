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
        var page = matchRequest.getPage();
        var pageRequest = PageRequest.of(page, matchRequest.getNbMatches(), Sort.by("id").descending());

        var res =  matchRepository.findByUser1IdOrUser2Id(user.getId(), user.getId(), pageRequest);

        var pageRequestMsg = PageRequest.of(0, 1, Sort.by("sendTime").descending() );
        List<Message> listMessage;
        var formatRes = new ArrayList<MatchResponse>();
        while (formatRes.size() < matchRequest.getNbMatches() && res.size() > 0){
            for (var m  : res){
                listMessage = messageRepository.findByMatchId(m.getId(), pageRequestMsg);
                if(listMessage.isEmpty()){
                    if (m.getUser1().getId() == user.getId()){
                        formatRes.add(new MatchResponse(m.getId(), new UserResponse(m.getUser2()),  null));
                    }else{
                        formatRes.add(new MatchResponse(m.getId(),new UserResponse(m.getUser1()), null));
                    }
                }
            }
            page++;
            pageRequest = PageRequest.of(page, matchRequest.getNbMatches(), Sort.by("id").descending());
            res = matchRepository.findByUser1IdOrUser2Id(user.getId(), user.getId(), pageRequest);
        }
        return formatRes;
    }

    public List<MatchResponse> getMatchesLastMsgPage(User user, MatchRequest matchRequest) {
        var page = matchRequest.getPage();
        var pageRequest = PageRequest.of(page, matchRequest.getNbMatches(), Sort.by("id").descending());
        var loop = 0;
        var res =  matchRepository.findByUser1IdOrUser2Id(user.getId(), user.getId(), pageRequest);
        System.out.println(res.size());
        var pageRequestMsg = PageRequest.of(0, 1, Sort.by("sendTime").descending() );
        List<Message> listMessage;
        var formatRes = new ArrayList<MatchResponse>();
        while (formatRes.size() < matchRequest.getNbMatches() && res.size() > 0){
            System.out.println("loop : " + loop);
            System.out.println("formatRes.size() : " + formatRes.size());

            System.out.println("in loop : size : " + res.size());

            for (var m  : res){
                System.out.println("match id : " + m.getId());
                listMessage = messageRepository.findByMatch_Id(m.getId(), pageRequestMsg);
                System.out.println(listMessage);
                if(!listMessage.isEmpty()){
                    System.out.println("message ok for match " + m.getId());
                    if (m.getUser1().getId() == user.getId()){
                        formatRes.add(new MatchResponse(m.getId(), new UserResponse(m.getUser2()), new MessageResponse(listMessage.get(0))));
                    }else{
                        formatRes.add(new MatchResponse(m.getId(),new UserResponse(m.getUser1()), new MessageResponse(listMessage.get(0))));
                    }
                }
            }
            page++;
            pageRequest = PageRequest.of(page, matchRequest.getNbMatches(), Sort.by("id").descending());
            res = matchRepository.findByUser1IdOrUser2Id(user.getId(), user.getId(), pageRequest);
            loop++;
        }
        System.out.println("loop : " + loop);
        System.out.println("formatRes.size() : " + formatRes.size());

        System.out.println("after loop : size : " + res.size());
        return formatRes;
    }
}
