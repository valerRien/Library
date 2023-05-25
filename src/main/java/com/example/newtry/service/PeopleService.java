package com.example.newtry.service;

import com.example.newtry.models.Person;
import com.example.newtry.repository.PeopleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> listPeople() {
        return peopleRepository.findAll();
    }

    public Person showPersonInfo(int id) {
        return (Person) peopleRepository.findById(id).orElse(null);
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }
}
