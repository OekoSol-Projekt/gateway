package at.oekosol.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user-management")
    public ResponseEntity<String> userManagementFallback() {
        return ResponseEntity.status(503).body("User Management Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/auth-service")
    public ResponseEntity<String> authServiceFallback() {
        return ResponseEntity.status(503).body("Auth Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/notification-service")
    public ResponseEntity<String> notificationServiceFallback() {
        return ResponseEntity.status(503).body("Notification Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/site-management")
    public ResponseEntity<String> siteManagementFallback() {
        return ResponseEntity.status(503).body("Site Management Service is currently unavailable. Please try again later.");
    }
}
