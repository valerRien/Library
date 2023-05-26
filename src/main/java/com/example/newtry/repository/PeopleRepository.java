package com.example.newtry.repository;

import com.example.newtry.models.Person;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    void deleteById(int id);
    Person findByFullName(String fullName);
}
