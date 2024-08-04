package com.example.library;

import com.example.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibraryManagementSystemApplicationTests {

    @Autowired
    private BookRepositorytest bookRepository;

    @Autowired
    private BookServicetestImp bookService;

    @BeforeEach
    void setUp() {
        // Clean up the repository before each test
        bookRepository.deleteAll();
    }

    @Test
    void checkSaveOfBook() {
        // Given
        Book book = new Book();
        book.setId(40L);
        book.setTitle("C++");
        book.setAuthor("MOHAMED");
        book.setPublication_year(2011);
        book.setISBN("11-2-2011");

        // When
        bookRepository.save(book);
        boolean exists = bookRepository.existsById(40L);

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void checkFindBookById() {
        // Given
        Book book = new Book();
        book.setId(50L);
        book.setTitle("C++");
        book.setAuthor("MOHAMED");
        book.setPublication_year(2011);
        book.setISBN("11-2-2011");
        bookRepository.save(book);

        // When
        Book foundBook = bookService.getBookById(50L);

        // Then
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("C++");
    }

    @Test
    void checkFindBookByIdNotFound() {
        // When
        Book foundBook = bookService.getBookById(999L);

        // Then
        assertThat(foundBook).isNull();
    }

    @Test
    void checkUpdateBook() {
        // Given
        Book book = new Book();
        book.setId(40L);
        book.setTitle("C++");
        book.setAuthor("MOHAMED");
        book.setPublication_year(2011);
        book.setISBN("11-2-2011");
        bookRepository.save(book);

        Book updatedBook = new Book();
        updatedBook.setTitle("Java");
        updatedBook.setAuthor("MOHAMED");
        updatedBook.setPublication_year(2020);
        updatedBook.setISBN("22-2-2020");

        // When
        Book result = bookService.updateBook(40L, updatedBook);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Java");
        assertThat(result.getPublication_year()).isEqualTo(2020);
    }

    @Test
    void checkDeleteBook() {
        // Given
        Book book = new Book();
        book.setId(40L);
        book.setTitle("C++");
        book.setAuthor("MOHAMED");
        book.setPublication_year(2011);
        book.setISBN("11-2-2011");
        bookRepository.save(book);

        // When
        bookService.deleteBook(40L);
        boolean exists = bookRepository.existsById(40L);

        // Then
        assertThat(exists).isFalse();
    }
}
