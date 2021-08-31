package org.alishev.spring.dao;

import org.alishev.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        if (personToBeUpdated!=null) {
            personToBeUpdated.setName(person.getName());
        } else {
            throw new NoSuchElementException("No person with id: " + person.getId());
        }
    }

    public void delete(int id) {
        people.removeIf(x->x.getId()==id);
    }
}
