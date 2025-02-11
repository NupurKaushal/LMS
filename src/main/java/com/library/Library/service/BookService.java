package com.library.Library.service;

import com.library.Library.model.Book;
import com.library.Library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        log.info("Fetching all active books");
        return bookRepository.findAllActive();
    }

    public Book findById(Long id) {
        log.debug("Finding book by id: {}", id);
        return bookRepository.findById(id).orElseThrow(() -> {
            log.error("Book not found with id: {}", id);
            return new RuntimeException("Book not found");
        });
    }

    public Book save(Book book) {
        log.info("Saving book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    public void softDeleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            log.error("Book not found with id: {}", id);
            return new RuntimeException("Book not found");
        });
        book.setDeleted(true);
        bookRepository.save(book);
        log.info("Soft deleted book with id: {}", id);
    }

    public void restoreBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            log.error("Book not found with id: {}", id);
            return new RuntimeException("Book not found");
        });
        book.setDeleted(false);
        bookRepository.save(book);
        log.info("Restored book with id: {}", id);
    }

}
