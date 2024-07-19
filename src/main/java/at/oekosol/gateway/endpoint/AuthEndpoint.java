package at.oekosol.gateway.endpoint;

import at.oekosol.gateway.entity.AuthenticationRequest;
import at.oekosol.gateway.entity.AuthenticationResponse;
import at.oekosol.gateway.entity.User;
import at.oekosol.gateway.service.JwtService;
import at.oekosol.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthEndpoint {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthenticationResponse>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return jwtService.createJwtToken(authenticationRequest)
                .map(jwt -> ResponseEntity.ok(new AuthenticationResponse(jwt)))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(new AuthenticationResponse("Invalid credentials", true))));
    }

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody User user) {
        return userService.findByUsername(user.getUsername())
                .flatMap(existingUser -> Mono.just(ResponseEntity.badRequest().body("Username is already taken.")))
                .switchIfEmpty(userService.save(user).then(Mono.just(ResponseEntity.ok("User registered successfully."))));
    }
}
