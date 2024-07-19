package at.oekosol.gateway.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String jwt;
    private String message;

    // Constructor for success response
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
        this.message = null;
    }

    // Constructor for error response
    public AuthenticationResponse(String message, boolean isError) {
        this.message = message;
        this.jwt = null;
    }
}
