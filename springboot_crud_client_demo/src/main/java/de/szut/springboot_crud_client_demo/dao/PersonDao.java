package de.szut.springboot_crud_client_demo.dao;

import de.szut.springboot_crud_client_demo.model.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class PersonDao {

    private final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
    private final String URL_PERSON = "http://localhost:8080/person";
    private final String URL_PERSON_URL = URL_PERSON + "/";
    private final String URL_PERSON_PARAM = URL_PERSON + "?id=";

    public Person readByUrl(int id) {
        return read(id, URL_PERSON_URL);
    }

    public Person readByParam(int id) {
        return read(id, URL_PERSON_PARAM);
    }

    private Person read(int id, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MEDIA_TYPE));
        headers.setContentType(MEDIA_TYPE);

        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> responseEntity = restTemplate.exchange(url + id, HttpMethod.GET, requestEntity, Person.class);
        HttpStatusCode httpStatus = responseEntity.getStatusCode();

        return responseEntity.getBody();
    }
}
