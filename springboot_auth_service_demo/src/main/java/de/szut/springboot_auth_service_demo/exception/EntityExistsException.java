package de.szut.springboot_auth_service_demo.exception;

public class EntityExistsException extends RuntimeException {

    private final String message;

    public EntityExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
