package com.library.Library.controller;

import com.library.Library.model.Admin;
import com.library.Library.model.Book;
import com.library.Library.model.User;
import com.library.Library.model.BorrowRecord;
import com.library.Library.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Admin management endpoints
    @GetMapping("/admins/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }

    @PostMapping("/admins")
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    // Book management endpoints
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return adminService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return adminService.getBookById(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return adminService.addBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return adminService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        adminService.deleteBook(id);
    }

    @PutMapping("/books/{id}/copies/{newTotalCopies}")
    public boolean updateBookCopies(@PathVariable Long id, @PathVariable int newTotalCopies) {
        return adminService.updateBookCopies(id, newTotalCopies);
    }

    // User management endpoints
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return adminService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return adminService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    // Borrow record management endpoints
    @GetMapping("/borrowRecords")
    public List<BorrowRecord> getAllBorrowRecords() {
        return adminService.getAllBorrowRecords();
    }

    @GetMapping("/borrowRecords/{id}")
    public BorrowRecord getBorrowRecordById(@PathVariable Long id) {
        return adminService.getBorrowRecordById(id);
    }

    @PostMapping("/borrowRecords")
    public BorrowRecord addBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        return adminService.addBorrowRecord(borrowRecord);
    }

    @PutMapping("/borrowRecords/{id}")
    public BorrowRecord updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord borrowRecord) {
        return adminService.updateBorrowRecord(id, borrowRecord);
    }

    @DeleteMapping("/borrowRecords/{id}")
    public void deleteBorrowRecord(@PathVariable Long id) {
        adminService.deleteBorrowRecord(id);
    }
}
