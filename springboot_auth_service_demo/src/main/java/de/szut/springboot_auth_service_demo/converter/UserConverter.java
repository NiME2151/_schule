package de.szut.springboot_auth_service_demo.converter;

import de.szut.springboot_auth_service_demo.dto.SignInRequest;
import de.szut.springboot_auth_service_demo.dto.SignUpRequest;
import de.szut.springboot_auth_service_demo.dto.UserCompleteResponse;
import de.szut.springboot_auth_service_demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConverter {

    public User convertRequestToModel(SignInRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    public User convertRequestToModel(SignUpRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMail(request.getMail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    public List<UserCompleteResponse> convertModelsToResponses(List<User> users) {
        List<UserCompleteResponse> responses = new ArrayList<>();
        for (User user : users) {
            UserCompleteResponse ucs = new UserCompleteResponse(
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMail(),
                    user.getPassword(),
                    user.getRole()
            );
            responses.add(ucs);
        }
        return responses;
    }
}
