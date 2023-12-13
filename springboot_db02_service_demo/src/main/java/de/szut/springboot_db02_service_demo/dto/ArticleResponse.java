package de.szut.springboot_db02_service_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ArticleResponse {

    private long id;
    private String designation;
    private double price;
}
