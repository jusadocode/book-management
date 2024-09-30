package com.example.book_management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path="/all")
    public @ResponseBody List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path="/filter") 
        public @ResponseBody List<Book> getFilteredBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Integer releaseYear,  // format: yyyy
        @RequestParam(required = false) String author,
        @RequestParam(required = false) Integer rating) {
            
    List<Book> filteredBooks = bookRepository.findByFilters(title, releaseYear, author, rating);

    return filteredBooks;
}

    
    @PutMapping("/rate/{id}")
    @Transactional 
    public @ResponseBody String rateBook(@PathVariable Integer id, @RequestParam Integer rating) {
        
        if (rating < 1 || rating > 5) {
        return "Rating must be between 1 and 5.";
    }
        Optional<Book> bookToUpdate = bookRepository.findById(id);

        if (bookToUpdate.isPresent()) {
            bookRepository.rateBookById(id, rating);
            return "Book rating updated successfully!";
        }
        else      
            return "Book with ID " + id + " not found.";
        
    }
}       





