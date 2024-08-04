package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public Book addBook(Book book);

    public Book updateBook(Long id, Book bookDetails);
    public void deleteBook(Long id);

}

