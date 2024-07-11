package com.example.crud.controllers;

import com.example.crud.models.Book;
import com.example.crud.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/book")
    public String bookMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book-main";
    }



    @GetMapping("/book/add")
    public String bookAdd(Model model) {

        return "book-add";
    }

    @PostMapping("/book/add")
    public String bookPostAdd(@RequestParam String title, @RequestParam String author, @RequestParam String description, @RequestParam int price, Model model) {
        Book book = new Book(title, author, description, price);

        bookRepository.save(book);


        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable(value = "id")  long id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty())
            return "404";
        model.addAttribute("book", book.get());

        return "book-details";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable(value = "id")  long id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty())
            return "404";
        model.addAttribute("book", book.get());

        return "book-edit";
    }

    @PostMapping("/book/{id}/edit")
    public String bookPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String author, @RequestParam String description, @RequestParam int price, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);

        bookRepository.save(book);


        return "redirect:/book";
    }


    @PostMapping("/book/{id}/remove")
    public String bookPostUpdate(@PathVariable(value = "id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();

        bookRepository.delete(book);


        return "redirect:/book";
    }

}
