package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "book_id")
    Long id ;
    String title;
    String author;
    int publication_year;
    String  ISBN ;
}
