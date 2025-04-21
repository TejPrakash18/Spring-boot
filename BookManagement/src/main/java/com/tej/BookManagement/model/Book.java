package com.tej.BookManagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private Double price;
}
