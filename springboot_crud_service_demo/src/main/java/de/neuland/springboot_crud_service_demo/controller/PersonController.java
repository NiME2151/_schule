package de.neuland.springboot_crud_service_demo.controller;

import com.sun.source.tree.ArrayTypeTree;
import de.neuland.springboot_crud_service_demo.dao.PersonDao;
import de.neuland.springboot_crud_service_demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PersonController {

    private final PersonDao personDao;

    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping(value = "/person/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Person> getPersonByUrl(@PathVariable("id") int id) {
        return getPerson(id);
    }

    @GetMapping(value = "/person", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Person> getPersonByParam(@RequestParam("id") int id) {
        return getPerson(id);
    }

    private ResponseEntity<Person> getPerson(int id) {
        Person person = this.personDao.read(id);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping(value = "/persons", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Map<Integer, Person>> getAllPersons() {
        Map<Integer, Person> persons = this.personDao.read();
        if (persons == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(persons);
    }

    @PostMapping(value = "/person", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    @ResponseBody
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personDao.create(person));
    }

    @PutMapping(value = "/person", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    @ResponseBody
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.personDao.update(person));
    }

    @DeleteMapping(value = "/person/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Void> deletePersonByUrl(@PathVariable("id") int id) {
        return deletePerson(id);
    }

    @DeleteMapping(value = "/person", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Void> deletePersonByParam(@RequestParam("id") int id) {
        return deletePerson(id);
    }

    private ResponseEntity<Void> deletePerson(int id) {
        personDao.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/")
    private ResponseEntity<String> welcome() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World!");
    }
}
