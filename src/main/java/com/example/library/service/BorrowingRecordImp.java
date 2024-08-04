package com.example.library.service;

import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BorrowingRecord;
import com.example.library.model.Patron;
import com.example.library.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowingRecordImp implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookServiceImp bookService;

    @Autowired
    private PatronServiceImp patronService;
    @Override
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookService.getBookById(bookId);
        Patron patron = patronService.getPatronById(patronId);

        if (book != null && patron != null) {
            BorrowingRecord record = new BorrowingRecord();
            record.setBook(book);
            record.setPatron(patron);
            record.setBorrowDate(new Date());
            return borrowingRecordRepository.save(record);
        }
        throw new ResourceNotFoundException(String.format(
                "Failed to borrow book: Book with ID %d or Patron with ID %d not found.", bookId, patronId));
    }

    @Override
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);

        if (record != null) {
            record.setReturnDate(new Date());
            return borrowingRecordRepository.save(record);
        }
        throw new ResourceNotFoundException(String.format(
                "Failed to return book: Book with ID %d or Patron with ID %d not found.", bookId, patronId));
    }
}
