package at.oekosol.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-management", r -> r.path("/user-management/**")
                        .filters(f -> f.stripPrefix(1))
//                                .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()))
//                                .retry(config -> config.setRetries(3).setStatuses(HttpStatus.GATEWAY_TIMEOUT))
//                                .addResponseHeader("X-Frame-Options", "DENY")
//                                .addResponseHeader("X-Content-Type-Options", "nosniff")
//                                .addResponseHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains")
//                                .circuitBreaker(config -> config.setName("userManagementCircuitBreaker").setFallbackUri("forward:/fallback/user-management")))
                        .uri("lb://USER-MANAGEMENT"))
                .route("auth-service", r -> r.path("/auth-service/**")
                        .filters(f -> f.stripPrefix(1))
//                                .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()))
//                                .retry(config -> config.setRetries(3).setStatuses(HttpStatus.GATEWAY_TIMEOUT))
//                                .addResponseHeader("X-Frame-Options", "DENY")
//                                .addResponseHeader("X-Content-Type-Options", "nosniff")
//                                .addResponseHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains")
//                                .circuitBreaker(config -> config.setName("authServiceCircuitBreaker").setFallbackUri("forward:/fallback/auth-service")))
                        .uri("lb://AUTH-SERVICE"))
                .route("notification-service", r -> r.path("/notification-service/**")
                        .filters(f -> f.stripPrefix(1)
                                .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()))
                                .retry(config -> config.setRetries(3).setStatuses(HttpStatus.GATEWAY_TIMEOUT))
                                .addResponseHeader("X-Frame-Options", "DENY")
                                .addResponseHeader("X-Content-Type-Options", "nosniff")
                                .addResponseHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains")
                                .circuitBreaker(config -> config.setName("notificationServiceCircuitBreaker").setFallbackUri("forward:/fallback/notification-service")))
                        .uri("lb://NOTIFICATION-SERVICE"))
                .route("site-management", r -> r.path("/site-management/**")
                        .filters(f -> f.stripPrefix(1))
//                                .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()))
//                                .retry(config -> config.setRetries(3).setStatuses(HttpStatus.GATEWAY_TIMEOUT))
//                                .addResponseHeader("X-Frame-Options", "DENY")
//                                .addResponseHeader("X-Content-Type-Options", "nosniff")
//                                .addResponseHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains")
//                                .circuitBreaker(config -> config.setName("siteManagementCircuitBreaker").setFallbackUri("forward:/fallback/site-management")))
                        .uri("lb://SITE-MANAGEMENT"))
                .route("ftp-management-service", r -> r.path("/ftp-management-service/**")
                        .filters(f -> f.stripPrefix(1)
                                .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()))
                                .retry(config -> config.setRetries(3).setStatuses(HttpStatus.GATEWAY_TIMEOUT))
                                .addResponseHeader("X-Frame-Options", "DENY")
                                .addResponseHeader("X-Content-Type-Options", "nosniff")
                                .addResponseHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains")
                                .circuitBreaker(config -> config.setName("ftpManagementServiceCircuitBreaker").setFallbackUri("forward:/fallback/ftp-management-service")))
                        .uri("lb://FTP-MANAGEMENT-SERVICE"))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20);
    }


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
