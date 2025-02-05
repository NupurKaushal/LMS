package com.library.Library.controller;

import com.library.Library.model.Book;
import com.library.Library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        log.info("Fetching book with id: {}", id);
        return bookService.findById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        log.info("Creating new book with title: {}", book.getTitle());
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        log.info("Updating book with id: {}", id);
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.info("Soft deleting book with id: {}", id);
        bookService.softDeleteById(id);
    }

    @PutMapping("/restore/{id}")
    public void restoreBook(@PathVariable Long id) {
        log.info("Restoring book with id: {}", id);
        bookService.restoreBook(id);
    }
}
