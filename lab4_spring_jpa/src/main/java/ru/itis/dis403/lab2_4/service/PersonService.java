package ru.itis.dis403.lab2_4.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dis403.lab2_4.model.Admin;
import ru.itis.dis403.lab2_4.model.Client;
import ru.itis.dis403.lab2_4.model.Person;
import ru.itis.dis403.lab2_4.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person save(String type, String name, String extra) {
        Person person;
        if ("admin".equalsIgnoreCase(type)) {
            Admin admin = new Admin();
            admin.setName(name);
            admin.setEmail(extra);
            person = admin;
        } else {
            Client client = new Client();
            client.setName(name);
            client.setAddress(extra);
            person = client;
        }
        return personRepository.save(person);
    }
}
