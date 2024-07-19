package at.oekosol.gateway.service;

import at.oekosol.gateway.entity.AuthenticationRequest;
import at.oekosol.gateway.security.CustomUserDetailsService;
import at.oekosol.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JwtService {

    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public Mono<String> createJwtToken(AuthenticationRequest authenticationRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword()))
                .flatMap(auth -> userDetailsService.findByUsername(authenticationRequest.getUsername()))
                .map(jwtUtil::generateToken);
    }
}