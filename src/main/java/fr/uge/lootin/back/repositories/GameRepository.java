package fr.uge.lootin.back.repositories;
import fr.uge.lootin.back.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findByGameName(String gameName);
}
