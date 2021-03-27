package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findByUser1IdOrUser2Id(Long user1, Long user2, Pageable pageable);

    @Query("from _Match m where (m.user1.id = :user1 and m.user2.id = :user2) or (m.user2.id = :user1 and m.user1.id = :user2) ")
    Optional<Match> findMatch(@Param("user1") Long user1, @Param("user2") Long user2);

    @Query("select distinct ma from _Match ma left join Message msg on msg.match.id = ma.id where msg.id = NULL and (ma.user1.id = :userId or ma.user2.id = :userId )")
    List<Match> getEmptyMatches(@Param("userId") Long userId, Pageable pageable);

    @Query("select distinct ma from _Match ma join Message msg on msg.match.id = ma.id where (ma.user1.id = :userId or ma.user2.id = :userId ) ")
    List<Match> getMatchesLastMsg(@Param("userId") Long userId, Pageable pageable);

    @Query("select distinct ma from _Match ma where (ma.user1.id = :userId or ma.user2.id = :userId ) ")
    List<Match> getUserAlreadyMatch(@Param("userId") Long userId);




}
