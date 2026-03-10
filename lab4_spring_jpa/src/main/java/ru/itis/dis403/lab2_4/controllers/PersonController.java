package ru.itis.dis403.lab2_4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dis403.lab2_4.model.Admin;
import ru.itis.dis403.lab2_4.model.Client;
import ru.itis.dis403.lab2_4.model.Person;
import ru.itis.dis403.lab2_4.service.PersonService;

import java.util.List;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public String persons(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @GetMapping("/persons/new")
    public String newPersonForm() {
        return "person_form";
    }

    @PostMapping("/persons")
    public String createPerson(@RequestParam String type,
                               @RequestParam String name,
                               @RequestParam(required = false, defaultValue = "") String email,
                               @RequestParam(required = false, defaultValue = "") String address) {
        if ("admin".equalsIgnoreCase(type)) {
            personService.save(type, name, email);
        } else {
            personService.save(type, name, address);
        }
        return "redirect:/persons";
    }
}
