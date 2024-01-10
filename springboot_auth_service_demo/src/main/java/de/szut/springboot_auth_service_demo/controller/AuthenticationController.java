package de.szut.springboot_auth_service_demo.controller;

import de.szut.springboot_auth_service_demo.converter.TokenConverter;
import de.szut.springboot_auth_service_demo.converter.UserConverter;
import de.szut.springboot_auth_service_demo.dto.AuthenticationResponse;
import de.szut.springboot_auth_service_demo.dto.SignInRequest;
import de.szut.springboot_auth_service_demo.dto.SignUpRequest;
import de.szut.springboot_auth_service_demo.model.User;
import de.szut.springboot_auth_service_demo.security.JwtToken;
import de.szut.springboot_auth_service_demo.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TokenConverter tokenConverter;

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        User user = userConverter.convertRequestToModel(request);
        JwtToken jwtToken = authenticationService.signIn(user);
        AuthenticationResponse response = tokenConverter.convertModelToResponse(jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        User user = userConverter.convertRequestToModel(request);
        JwtToken jwtToken = authenticationService.signUp(user);
        AuthenticationResponse response = tokenConverter.convertModelToResponse(jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
