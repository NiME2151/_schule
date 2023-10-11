package de.neuland.springboot_crud_service_demo.dao;

import de.neuland.springboot_crud_service_demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonDao {

    private Map<Integer, Person> personsHashMap;

    public PersonDao() {
        initHashMap();
    }

    private void initHashMap() {
        if (personsHashMap == null) {
            personsHashMap = new HashMap<>();
        }
    }

    public Person create(Person person) {
        return personsHashMap.put(person.getId(), person);
    }

    public Person read(int id) {
        return personsHashMap.get(id);
    }

    public Map<Integer, Person> read() {
        return personsHashMap;
    }

    public Person update(Person person) {
        return personsHashMap.put(person.getId(), person);
    }

    public void delete(int id) {
        personsHashMap.remove(id);
    }
}
