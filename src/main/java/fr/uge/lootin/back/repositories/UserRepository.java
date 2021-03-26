package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Attraction;
import fr.uge.lootin.back.models.Game;
import fr.uge.lootin.back.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginUsername(String username);

    List<User> findByGamesGameName(String gameName);

    //possible opti get only userId
    List<User> findDistinctByGamesIn(Set<Game> games);

    List<User> findDistinctByGamesInAndGender(Set<Game> games, User.Gender gender);
}
