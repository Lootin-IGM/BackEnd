package fr.uge.lootin.back.repositories;
import fr.uge.lootin.back.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
