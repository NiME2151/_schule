package de.szut.springboot_crud_client_demo;

import de.szut.springboot_crud_client_demo.app.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            App.run();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
