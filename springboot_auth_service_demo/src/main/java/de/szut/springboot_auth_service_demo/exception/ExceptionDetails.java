package de.szut.springboot_auth_service_demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionDetails {

    private Date timeStamp;
    private String message;
    private String details;
}
