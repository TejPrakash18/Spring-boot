package com.tej.BookManagement.service;

import com.tej.BookManagement.model.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    Book deleteBook(Long id);
}
