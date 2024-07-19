package at.oekosol.gateway.repository;

import at.oekosol.gateway.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    /**
     * Find a user by its username.
     *
     * @param username The username of the user to find
     * @return the user with
     */
    Mono<User> findByUsername(String username);
}
