package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByMatchId(Long matchId, Pageable pageable);

    List<Message> findByMatch_Id(Long id, Pageable pageable);

    List<Message> findByMatch_IdOrderBySendTimeDesc(Long id);
}
