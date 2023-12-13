package de.szut.springboot_db01_service_demo.request;

import lombok.Getter;

@Getter
public class GuestbookEntryRequest {

    private String title;
    private String comment;
    private String commenter;
}
