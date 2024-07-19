package at.oekosol.gateway.service;

import at.oekosol.gateway.entity.User;
import at.oekosol.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}