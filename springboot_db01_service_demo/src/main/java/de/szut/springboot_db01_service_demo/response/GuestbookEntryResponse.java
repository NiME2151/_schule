package de.szut.springboot_db01_service_demo.response;

import lombok.Data;

import java.util.Date;

@Data
public class GuestbookEntryResponse {

    private String title;
    private String comment;
    private String commenter;
    private Date date;
}
