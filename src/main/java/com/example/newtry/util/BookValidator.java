package com.example.newtry.util;

import com.example.newtry.models.Book;
import com.example.newtry.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class BookValidator implements Validator {

    private final BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book bookWhichIsValidatedNow = (Book) target;
        if (bookService.findByTitle(bookWhichIsValidatedNow.getTitle()) != null)
            errors.rejectValue("title", "", "Такая книга уже зарегистрирована в базе");
    }
}
