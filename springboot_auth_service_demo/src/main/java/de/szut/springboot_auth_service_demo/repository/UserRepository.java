package de.szut.springboot_auth_service_demo.repository;

import de.szut.springboot_auth_service_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
