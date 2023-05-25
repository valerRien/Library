package com.example.newtry.repository;

import com.example.newtry.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    void deleteById(int id);
}
