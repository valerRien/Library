package com.example.newtry.controllers;

import com.example.newtry.dao.BookDAO;
import com.example.newtry.models.Book;
import com.example.newtry.service.BookService;
import com.example.newtry.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookValidator bookValidator;
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator, BookDAO bookDAO) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.bookDAO = bookDAO;
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.listBooks());
        return "listBooks";
    }

    @GetMapping("/{id}")
    public String showBookInfo(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("book", bookService.showBookInfo(id));
        model.addAttribute("person", bookDAO.getOwner(id));
        return "showBookInfo";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute (name = "book") Book book) {
        return "newBook";
    }

    @PostMapping()
    public String addBook(@ModelAttribute(name = "book") @Valid Book book, BindingResult errors) {
        bookValidator.validate(book, errors);
        if (errors.hasErrors()) return "newBook";
        bookService.save(book);
        return "redirect:/books/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") int id) {
        bookService.delete(id);
        return "redirect:/books/list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("book", bookService.showBookInfo(id));
        return "editBook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@ModelAttribute(name = "book") @Valid Book book, BindingResult errors, @PathVariable(name = "id") int id) {
        bookValidator.validate(book, errors);
        if (errors.hasErrors()) return "editBook";
        bookService.update(id, book);
        return "redirect:/books/list";
    }



}
