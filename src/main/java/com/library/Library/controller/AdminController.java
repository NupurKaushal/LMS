package com.library.Library.controller;

import com.library.Library.model.Book;
import com.library.Library.model.User;
import com.library.Library.model.BorrowRecord;
import com.library.Library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin management endpoints
    @GetMapping("/admins/{email}")
    public ResponseEntity<?> getAdminByEmail(@PathVariable String email) {
        log.info("Fetching admin with email: {}", email);
        try {
            return ResponseEntity.ok(adminService.getAdminByEmail(email));
        } catch (Exception e) {
            log.error("Error fetching admin with email {}: {}", email, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with email " + email + " not found");
        }
    }

    @PostMapping("/admins")
    public ResponseEntity<User> addAdmin(@RequestBody User user) {
        log.info("Adding new admin with email: {}", user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addAdmin(user));
    }

    // Book management endpoints
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Fetching all books");
        return ResponseEntity.ok(adminService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        log.info("Fetching book with id: {}", id);
        try {
            return ResponseEntity.ok(adminService.getBookById(id));
        } catch (Exception e) {
            log.error("Error fetching book with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + id + " not found");
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        log.info("Adding new book with title: {}", book.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addBook(book));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        log.info("Updating book with id: {}", id);
        return ResponseEntity.ok(adminService.updateBook(id, book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id: {}", id);
        try {
            adminService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting book with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + id + " not found");
        }
    }

    @PutMapping("/books/{id}/copies/{newTotalCopies}")
    public ResponseEntity<?> updateBookCopies(@PathVariable Long id, @PathVariable int newTotalCopies) {
        log.info("Updating book copies for book with id: {}", id);
        try {
            return ResponseEntity.ok(adminService.updateBookCopies(id, newTotalCopies));
        } catch (Exception e) {
            log.error("Error updating book copies for book with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update book copies: " + e.getMessage());
        }
    }

    // User management endpoints
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            log.info("Fetching user with id: {}", id);
            return ResponseEntity.ok(adminService.getUserById(id));
        } catch (Exception e) {
            log.error("Error fetching user with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        log.info("Adding new user with email: {}", user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Updating user with id: {}", id);
        return ResponseEntity.ok(adminService.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id: {}", id);
        try {
            adminService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting user with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
        }
    }

    // Borrow record management endpoints
    @GetMapping("/borrowRecords")
    public ResponseEntity<List<BorrowRecord>> getAllBorrowRecords() {
        log.info("Fetching all borrow records");
        return ResponseEntity.ok(adminService.getAllBorrowRecords());
    }

    @GetMapping("/borrowRecords/{id}")
    public ResponseEntity<?> getBorrowRecordById(@PathVariable Long id) {
        log.info("Fetching borrow record with id: {}", id);
        try {
            return ResponseEntity.ok(adminService.getBorrowRecordById(id));
        } catch (Exception e) {
            log.error("Error fetching borrow record with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrow record with id " + id + " not found");
        }
    }

    @PostMapping("/borrowRecords")
    public ResponseEntity<BorrowRecord> addBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        log.info("Adding new borrow record for user: {}", borrowRecord.getUser().getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addBorrowRecord(borrowRecord));
    }

    @PutMapping("/borrowRecords/{id}")
    public ResponseEntity<BorrowRecord> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord borrowRecord) {
        log.info("Updating borrow record with id: {}", id);
        return ResponseEntity.ok(adminService.updateBorrowRecord(id, borrowRecord));
    }

    @DeleteMapping("/borrowRecords/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable Long id) {
        log.info("Deleting borrow record with id: {}", id);
        try {
            adminService.deleteBorrowRecord(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting borrow record with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrow record with id " + id + " not found");
        }
    }
}