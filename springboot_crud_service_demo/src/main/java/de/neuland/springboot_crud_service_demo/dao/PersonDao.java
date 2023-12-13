package de.neuland.springboot_crud_service_demo.dao;

import de.neuland.springboot_crud_service_demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonDao {

    private Map<Integer, Person> persons;

    public PersonDao() {
        initHashMap();
    }

    private void initHashMap() {
        if (persons == null) {
            persons = new HashMap<>();
        }
    }

    public Person create(Person person) {
        return persons.put(person.getId(), person);
    }

    public Person read(int id) {
        return persons.get(id);
    }

    public Map<Integer, Person> read() {
        return persons;
    }

    public Person update(Person person) {
        return persons.put(person.getId(), person);
    }

    public void delete(int id) {
        persons.remove(id);
    }
}
