package com.example.library;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositorytest extends JpaRepository<Book ,Long> {
}
