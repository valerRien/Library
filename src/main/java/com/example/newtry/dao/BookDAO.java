package com.example.newtry.dao;

import com.example.newtry.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getOwner(int id) {
        return new ArrayList<>(jdbcTemplate.query("SELECT Person.id, Person.full_name, Person.year_of_birth FROM Person JOIN Book ON Person.id=Book.person_id WHERE Book.id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)));
    }
}
