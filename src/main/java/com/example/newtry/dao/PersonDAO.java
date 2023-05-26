package com.example.newtry.dao;

import com.example.newtry.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Book> getBooks(int id) {
        return new ArrayList<>(jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)));
    }
}
