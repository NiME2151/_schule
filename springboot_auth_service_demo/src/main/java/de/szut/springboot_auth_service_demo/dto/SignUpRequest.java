package de.szut.springboot_auth_service_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String mail;
    private String username;
    private String password;
}
