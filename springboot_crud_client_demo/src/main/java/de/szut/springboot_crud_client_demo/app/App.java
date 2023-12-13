package de.szut.springboot_crud_client_demo.app;

import de.szut.springboot_crud_client_demo.dao.PersonDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class App {

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.print("Person by param: ");
        input = br.readLine();
        PersonDao personDao = new PersonDao();
        System.out.println(personDao.readByParam(parseInt(input)));
        System.out.print("Person by url: ");
        input = br.readLine();
        System.out.println(personDao.readByUrl(parseInt(input)));
    }
}
