package com.library.Library.service;

import com.library.Library.ENUM.BorrowStatus;
import com.library.Library.model.Book;
import com.library.Library.model.BorrowRecord;
import com.library.Library.model.User;
import com.library.Library.repository.BookRepository;
import com.library.Library.repository.BorrowRecordRepository;
import com.library.Library.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void borrowBook(Long userId, Long bookId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (book.getCopiesAvailable() > 0) {
            BorrowRecord record = new BorrowRecord();
            record.setUser(user);
            record.setBook(book);
            record.setBorrowDate(LocalDate.now());
            record.setStatus(BorrowStatus.BORROWED);
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);

            borrowRecordRepository.save(record);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("No copies available for book id: " + bookId);
        }
    }

    public void returnBook(Long userId, Long bookId){
        BorrowRecord record = borrowRecordRepository.findByUserId(userId).stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getStatus() == BorrowStatus.BORROWED)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Borrow record not found for user id: " + userId + " and book id: " + bookId));

        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowStatus.RETURNED);

        Book book = record.getBook();
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);

        borrowRecordRepository.save(record);
        bookRepository.save(book);
    }

    public List<BorrowRecord> getUserBorrowedBooks(Long userId){
        return borrowRecordRepository.findByUserId(userId);
    }
}
