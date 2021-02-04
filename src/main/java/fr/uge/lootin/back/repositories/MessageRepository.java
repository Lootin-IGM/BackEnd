package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Match;
import fr.uge.lootin.back.models.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByMatchId(Long matchId, Pageable pageable);
}
