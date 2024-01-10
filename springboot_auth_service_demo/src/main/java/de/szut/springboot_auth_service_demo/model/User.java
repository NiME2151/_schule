package de.szut.springboot_auth_service_demo.model;

import de.szut.springboot_auth_service_demo.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String passwordEncrypted;
    private Role role;
}
