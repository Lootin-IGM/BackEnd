package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
