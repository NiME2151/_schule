package de.szut.springboot_auth_service_demo.converter;

import de.szut.springboot_auth_service_demo.dto.AuthenticationResponse;
import de.szut.springboot_auth_service_demo.security.JwtToken;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter {

    public AuthenticationResponse convertModelToResponse(JwtToken jwtToken) {
        return new AuthenticationResponse(jwtToken.getJwtToken());
    }
}
