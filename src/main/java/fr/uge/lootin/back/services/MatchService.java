package fr.uge.lootin.back.services;

import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.User;
import fr.uge.lootin.back.repositories.MatchRepository;
import fr.uge.lootin.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
