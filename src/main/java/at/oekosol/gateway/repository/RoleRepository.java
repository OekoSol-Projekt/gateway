package at.oekosol.gateway.repository;

import at.oekosol.gateway.entity.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    /**
     * Find a role by its name.
     *
     * @param name The name of the role to find
     * @return the role with the given name
     */
    Mono<Role> findByName(String name);
}
