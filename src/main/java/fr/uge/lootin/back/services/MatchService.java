package fr.uge.lootin.back.services;

import fr.uge.lootin.back.model.Match;
import fr.uge.lootin.back.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public Match save(Match match) {
        return matchRepository.save(match);
    }
}
