package com.example.newtry.util;

import com.example.newtry.models.Person;
import com.example.newtry.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person personWhoIsValidatedNow = (Person) target;

        if (personService.findByFullName(personWhoIsValidatedNow.getFullName()) != null)
            errors.rejectValue("fullName", "", "Человек с таким именем уже существует");
    }
}
