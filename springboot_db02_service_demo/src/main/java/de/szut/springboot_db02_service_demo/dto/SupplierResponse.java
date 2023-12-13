package de.szut.springboot_db02_service_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SupplierResponse {

    private long id;
    private String name;
    private String phone;
    private String email;
}
