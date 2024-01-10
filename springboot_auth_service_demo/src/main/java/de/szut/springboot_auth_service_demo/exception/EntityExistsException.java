package de.szut.springboot_auth_service_demo.exception;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException() {}

    @Override
    public String getMessage() {
        return "Username ist bereits vergeben.";
    }
}
