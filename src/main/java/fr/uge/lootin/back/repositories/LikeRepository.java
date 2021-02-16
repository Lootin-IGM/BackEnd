package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.models.Like;
import fr.uge.lootin.back.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    Optional<Like> findByUserIdAndUserLikedId(Long userID, Long userIdLiked);
}
