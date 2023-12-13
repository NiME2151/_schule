package de.szut.springboot_db02_service_demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SupplierRequest {

    @NotBlank(message = "Name is mandatory.")
    @Size(max = 50, message = "Name must not have more than 50 characters.")
    private String name;
    private String phone;
    @NotBlank(message = "Email is mandatory.")
    private String email;
}
