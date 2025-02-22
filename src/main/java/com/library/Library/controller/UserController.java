package com.library.Library.controller;

import com.library.Library.model.BorrowRecord;
import com.library.Library.model.User;
import com.library.Library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        log.info("User is registered successfully");
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        log.info("User is available of {}", id);
        return userService.getUserById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long userId, @PathVariable Long bookId){
        log.info("user borrowed book");
        userService.borrowBook(userId, bookId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/{userId}/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long userId, @PathVariable Long bookId){
        log.info("user returned the book");
        userService.returnBook(userId, bookId);
        return ResponseEntity.ok("Book return successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("User is deleted: {}", id);
        userService.deleteUser(id);
    }

    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<List<BorrowRecord>> getUserBorrowedBooks(@PathVariable Long userId){
        log.info("book borrowed by this user: userId -> {}", userId);
        return ResponseEntity.ok(userService.getUserBorrowedBooks(userId));
    }
}
