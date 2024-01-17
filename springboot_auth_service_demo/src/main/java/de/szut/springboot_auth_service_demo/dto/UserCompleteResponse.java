package de.szut.springboot_auth_service_demo.dto;

import de.szut.springboot_auth_service_demo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserCompleteResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String passwordEncrypted;
    private Role role;
}
