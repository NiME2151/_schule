package de.szut.springboot_auth_service_demo.service;

import de.szut.springboot_auth_service_demo.security.JwtToken;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public JwtToken generateToken() {
        return new JwtToken("Eine beliebige Zeichenkette");
    }
}
