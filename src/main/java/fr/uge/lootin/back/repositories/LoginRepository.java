package fr.uge.lootin.back.repositories;

import fr.uge.lootin.back.model.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long> {
}
