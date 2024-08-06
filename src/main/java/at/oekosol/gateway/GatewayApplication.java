package at.oekosol.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-management", r -> r.path("/user-management/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://USER-MANAGEMENT"))
                .route("auth-service", r -> r.path("/auth-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://AUTH-SERVICE"))
                .route("notification-service", r -> r.path("/notification-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://NOTIFICATION-SERVICE"))
                .build();

    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
