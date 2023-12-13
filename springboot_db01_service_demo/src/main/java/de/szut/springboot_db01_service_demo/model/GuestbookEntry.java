package de.szut.springboot_db01_service_demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Entry")
public class GuestbookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String comment;
    private String commenter;
    private Date date = new Date();
}
