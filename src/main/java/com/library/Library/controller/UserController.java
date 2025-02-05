package com.library.Library.controller;

import com.library.Library.model.BorrowRecord;
import com.library.Library.model.User;
import com.library.Library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.getUserById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long userId, @PathVariable Long bookId){
        userService.borrowBook(userId, bookId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/{userId}/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long userId, @PathVariable Long bookId){
        userService.returnBook(userId, bookId);
        return ResponseEntity.ok("Book return successfully");
    }

    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<List<BorrowRecord>> getUserBorrowedBooks(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserBorrowedBooks(userId));
    }
}
