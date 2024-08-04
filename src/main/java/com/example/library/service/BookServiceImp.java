package com.example.library.service;

import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    @Cacheable("books")
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        if (book !=null){
            BeanUtils.copyProperties(bookDetails , book , "id");
            return bookRepository.save(book);
        }
        throw  new ResourceNotFoundException("There is no book with this id :" + id);
    }

    @Override
    public void deleteBook(Long id) {
      bookRepository.deleteById(id);
    }
}
