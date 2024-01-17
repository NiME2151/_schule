package de.szut.springboot_auth_service_demo.controller;

import de.szut.springboot_auth_service_demo.converter.UserConverter;
import de.szut.springboot_auth_service_demo.dto.UserCompleteResponse;
import de.szut.springboot_auth_service_demo.model.User;
import de.szut.springboot_auth_service_demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public ResponseEntity<List<UserCompleteResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserCompleteResponse> responses = userConverter.convertModelsToResponses(users);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
