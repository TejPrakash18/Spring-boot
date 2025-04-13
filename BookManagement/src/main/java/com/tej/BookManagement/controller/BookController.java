package com.tej.BookManagement.controller;

import com.tej.BookManagement.model.Book;
import com.tej.BookManagement.service.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "*")

public class BookController {

    @Autowired
    private BookServiceImp bookServiceImp;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = bookServiceImp.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookServiceImp.getBookById(id);
        return (book != null)
                ? ResponseEntity.ok(book) // 200 ok
                : ResponseEntity.notFound().build();  //404 not found
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book created = bookServiceImp.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updated = bookServiceImp.updateBook(id, book);
        return (updated != null)
                ? ResponseEntity.ok(updated)               // 200 OK
                : ResponseEntity.notFound().build();       // 404 Not Found
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Book book = bookServiceImp.getBookById(id);
        if (book != null) {
            bookServiceImp.deleteBook(id);
            return ResponseEntity.ok("Book with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found.");
        }
    }

}
