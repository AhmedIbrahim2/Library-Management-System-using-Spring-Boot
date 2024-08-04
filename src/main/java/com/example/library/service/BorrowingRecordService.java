package com.example.library.service;

import com.example.library.model.BorrowingRecord;

public interface BorrowingRecordService {

    BorrowingRecord borrowBook(Long bookId, Long patronId);

    BorrowingRecord returnBook(Long bookId, Long patronId);
}
