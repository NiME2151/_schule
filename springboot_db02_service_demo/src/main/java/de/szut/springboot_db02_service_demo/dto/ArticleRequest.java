package de.szut.springboot_db02_service_demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequest {

    @NotBlank(message = "Name is mandatory.")
    @Size(max = 50, message = "Name must not have more than 50 characters.")
    private String designation;
    @Min(value = 0, message = "Price must be equal or higher than 0.")
    private double price;
    private long supplierId;
}
