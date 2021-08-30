package org.alishev.spring.dao;

import org.alishev.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++COUNT, "Tom"));
        people.add(new Person(++COUNT, "Bob"));
        people.add(new Person(++COUNT, "Mike"));
        people.add(new Person(++COUNT, "Katy"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public void save(Person person) {
        person.setId(++COUNT);
        people.add(person);
    }
}
