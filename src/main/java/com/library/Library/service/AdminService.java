package com.library.Library.service;

import com.library.Library.model.Admin;
import com.library.Library.model.Book;
import com.library.Library.model.User;
import com.library.Library.model.BorrowRecord;
import com.library.Library.repository.AdminRepository;
import com.library.Library.repository.BookRepository;
import com.library.Library.repository.UserRepository;
import com.library.Library.repository.BorrowRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    // Admin management methods
    public Admin getAdminByEmail(String email) {
        log.info("Fetching admin with email: {}", email);
        try {
            return adminRepository.findByEmail(email).orElseThrow(() -> new Exception("Admin with email " + email + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch admin: " + e.getMessage());
        }
    }

    public Admin addAdmin(Admin admin) {
        log.info("Adding new admin with email: {}", admin.getEmail());
        return adminRepository.save(admin);
    }

    // Book management methods
    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        log.info("Fetching book with id: {}", id);
        try {
            return bookRepository.findById(id).orElseThrow(() -> new Exception("Book with id " + id + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch book: " + e.getMessage());
        }
    }

    public Book addBook(Book book) {
        log.info("Adding new book with title: {}", book.getTitle());
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        book.setId(id);
        log.info("Updating book with id: {}", id);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        try {
            Book book = bookRepository.findById(id).orElseThrow(() -> new Exception("Book with id " + id + " not found"));
            bookRepository.delete(book);
            log.info("Deleting book with id: {}", id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to delete book: " + e.getMessage());
        }
    }

    public boolean updateBookCopies(Long id, int newTotalCopies) {
        try {
            Book book = bookRepository.findById(id).orElseThrow(() -> new Exception("Book with id " + id + " not found"));
            if (newTotalCopies >= book.getCopiesAvailable()) {
                book.setCopiesAvailable(newTotalCopies);
                bookRepository.save(book);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to update book copies: " + e.getMessage());
        }
    }

    // User management methods
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        try {
            return userRepository.findById(id).orElseThrow(() -> new Exception("User with id " + id + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch user: " + e.getMessage());
        }
    }

    public User addUser(User user) {
        log.info("Adding new user with email: {}", user.getEmail());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        log.info("Updating user with id: {}", id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }

    // Borrow record management methods
    public List<BorrowRecord> getAllBorrowRecords() {
        log.info("Fetching all borrow records");
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getBorrowRecordById(Long id) {
        log.info("Fetching borrow record with id: {}", id);
        try {
            return borrowRecordRepository.findById(id).orElseThrow(() -> new Exception("Borrow record with id " + id + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch borrow record: " + e.getMessage());
        }
    }

    public BorrowRecord addBorrowRecord(BorrowRecord borrowRecord) {
        log.info("Adding new borrow record for user: {}", borrowRecord.getUser().getEmail());
        return borrowRecordRepository.save(borrowRecord);
    }

    public BorrowRecord updateBorrowRecord(Long id, BorrowRecord borrowRecord) {
        borrowRecord.setId(id);
        log.info("Updating borrow record with id: {}", id);
        return borrowRecordRepository.save(borrowRecord);
    }

    public void deleteBorrowRecord(Long id) {
        try {
            BorrowRecord borrowRecord = borrowRecordRepository.findById(id).orElseThrow(() -> new Exception("Borrow record with id " + id + " not found"));
            borrowRecordRepository.delete(borrowRecord);
            log.info("Deleting borrow record with id: {}", id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to delete borrow record: " + e.getMessage());
        }
    }
}
