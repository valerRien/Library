package com.example.newtry.controllers;

import com.example.newtry.models.Person;
import com.example.newtry.service.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/list")
    public String listPeople(Model model) {
        model.addAttribute("people", peopleService.listPeople());
        return "listPeople";
    }

    @GetMapping("/{id}")
    public String showPersonInfo(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("person", peopleService.showPersonInfo(id));
        return "showPersonInfo";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "newPerson";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult errors) {
        if (errors.hasErrors()) return "newPerson";
        peopleService.save(person);
        return "redirect:/people/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable(name = "id") int id) {
        peopleService.delete(id);
        return "redirect:/people/list";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("person", peopleService.showPersonInfo(id));
        return "editPerson";
    }

    @PostMapping("update/{id}")
    public String saveEditing(@ModelAttribute(name = "person") @Valid Person person, @PathVariable(name = "id") int id,
                              BindingResult errors) {
        if (errors.hasErrors()) return "editPerson";
        peopleService.update(id, person);
        return "redirect:/people/list";
    }
}
