package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findByUser1IdOrUser2Id(Long user1, Long user2, Pageable pageable);
}
