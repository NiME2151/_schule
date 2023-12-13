package de.szut.springboot_db02_service_demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "articles")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "designation", nullable = false)
    private String designation;
    @Column(name = "price", nullable = false)
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

}
