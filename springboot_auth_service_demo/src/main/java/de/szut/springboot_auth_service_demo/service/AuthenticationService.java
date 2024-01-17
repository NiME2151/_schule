package de.szut.springboot_auth_service_demo.service;

import de.szut.springboot_auth_service_demo.exception.EntityExistsException;
import de.szut.springboot_auth_service_demo.exception.JwtAuthenticationException;
import de.szut.springboot_auth_service_demo.model.User;
import de.szut.springboot_auth_service_demo.repository.UserRepository;
import de.szut.springboot_auth_service_demo.security.JwtToken;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    public static final String ENTITY_NOT_FOUND_EXCEPTION_MSG = "Username or password not valid.";

    public JwtToken signIn(User user) throws JwtAuthenticationException {
        Optional<User> userOptional = userRepository.findById(user.getUsername());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MSG);
        }
        if (passwordEncoder.matches(user.getPassword(), userOptional.get().getPasswordEncrypted())) {
            return jwtTokenService.generateToken(userService.getUserDetails(user.getUsername()));
        }
        else {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MSG);
        }
    }

    public JwtToken signUp(User user) throws JwtAuthenticationException {
        Optional<User> userOptional = userRepository.findById(user.getUsername());
        if (userOptional.isPresent()) {
            throw new EntityExistsException("Username \"" + user.getUsername() + "\" already exists.");
        }
        user.setPasswordEncrypted(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtTokenService.generateToken(userService.getUserDetails(user.getUsername()));
    }
}
