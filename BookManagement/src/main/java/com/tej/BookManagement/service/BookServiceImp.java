package com.tej.BookManagement.service;

import com.tej.BookManagement.model.Book;
import com.tej.BookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        existing.setTitle(bookDetails.getTitle());
        existing.setAuthor(bookDetails.getAuthor());
        existing.setIsbn(bookDetails.getIsbn());
        existing.setPrice(bookDetails.getPrice());
            return repository.save(existing);
    }

    @Override
    public Book deleteBook(Long id) {

        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            repository.deleteById(id);
            return book;
        } else {
            return null; // or throw custom exception
        }
    }

}
