package com.example.newtry.dao;

import com.example.newtry.models.Book;
import com.example.newtry.models.Person;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
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

    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE id=?", id);
    }

    public void assignBookTo(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, bookId);
    }

    public List<Book> findAll() {
       return jdbcTemplate.query("SELECT * FROM Book WHERE person_id IS NULL ", new BeanPropertyRowMapper<>(Book.class));
    }
}
