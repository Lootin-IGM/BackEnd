package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginUsername(String username);

    List<User> findByGamesGameName(String gameName);
}
