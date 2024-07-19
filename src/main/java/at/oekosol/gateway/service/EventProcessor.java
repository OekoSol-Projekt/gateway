package at.oekosol.gateway.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class EventProcessor {

    @Bean
    public Function<Flux<String>, Flux<String>> processEvent() {
        return flux -> flux.map(message -> {
            // Process your message here
            return "Processed: " + message;
        });
    }
}
