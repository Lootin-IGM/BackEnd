package fr.uge.lootin.back.services;

import fr.uge.lootin.back.dto.MatchRequest;
import fr.uge.lootin.back.dto.MatchResponse;
import fr.uge.lootin.back.dto.UserResponse;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.MatchRepository;
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

        var formatRes = new ArrayList<MatchResponse>();
        for (var m  : res){
            if (m.getUser1().getId() == user.getId()){
                formatRes.add(new MatchResponse(m.getId(), new UserResponse(m.getUser2().getFirstName(), m.getUser2().getLastName(), m.getUser2().getLogin().getUsername())));
            }else{
                formatRes.add(new MatchResponse(m.getId(),new UserResponse(m.getUser1().getFirstName(), m.getUser1().getLastName(), m.getUser1().getLogin().getUsername())));
            }
        }

        return formatRes;
    }
}
