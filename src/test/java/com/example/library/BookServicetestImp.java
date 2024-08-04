package com.example.library;

import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class BookServicetestImp implements BookServicetest {

    @Autowired
    BookRepositorytest bookRepository;

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
                .orElse(null);
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
