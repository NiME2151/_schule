package de.szut.springboot_auth_service_demo.service;

import de.szut.springboot_auth_service_demo.exception.EntityExistsException;
import de.szut.springboot_auth_service_demo.model.User;
import de.szut.springboot_auth_service_demo.repository.UserRepository;
import de.szut.springboot_auth_service_demo.security.JwtToken;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public JwtToken signIn(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUsername());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return jwtService.generateToken();
    }

    public JwtToken signUp(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUsername());
        if (userOptional.isPresent()) {
            throw new EntityExistsException();
        }
        userRepository.save(user);
        return jwtService.generateToken();
    }
}
