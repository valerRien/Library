package com.example.newtry.controllers;

import com.example.newtry.models.Person;
import com.example.newtry.service.PersonService;
import com.example.newtry.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping("/list")
    public String listPeople(Model model) {
        model.addAttribute("people", personService.listPeople());
        return "listPeople";
    }

    @GetMapping("/{id}")
    public String showPersonInfo(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("person", personService.showPersonInfo(id));
        return "showPersonInfo";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute(name = "person") Person person) {
        return "newPerson";
    }

    @PostMapping
    public String createPerson(@ModelAttribute(name = "person") @Valid Person person, BindingResult errors) {
        personValidator.validate(person, errors);
        if (errors.hasErrors()) return "newPerson";
        personService.save(person);
        return "redirect:/people/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable(name = "id") int id) {
        personService.delete(id);
        return "redirect:/people/list";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("person", personService.showPersonInfo(id));
        return "editPerson";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@ModelAttribute(name = "person") @Valid Person person, BindingResult errors, @PathVariable(name = "id") int id) {
        personValidator.validate(person, errors);
        if (errors.hasErrors()) return "editPerson";
        personService.update(id, person);
        return "redirect:/people/list";
    }
}
