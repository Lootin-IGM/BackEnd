package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
}
