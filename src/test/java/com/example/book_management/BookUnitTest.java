package com.example.book_management;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookUnitTest {

    @Test
    void testBookCreation() {
        String title = "Clean Code";
        LocalDate releaseDate = LocalDate.of(2001, 2, 20);
        String author = "Robert C. Martin";
        int rating = 5;

        Book book = new Book(title, releaseDate, author, rating);

        assertEquals(title, book.getTitle());
        assertEquals(releaseDate, book.getReleaseDate());
        assertEquals(author, book.getAuthor());
        assertEquals(rating, book.getRating());
    }

    @Test
    void testAllSetters() {
        Book book = new Book("Clean Code", LocalDate.of(2001, 2, 20), "Robert C. Martin", 5);

        book.setId(3);
        assertEquals(3, book.getId());

        String newTitle = "Effective Java";
        book.setTitle(newTitle);
        assertEquals(newTitle, book.getTitle());

        LocalDate newReleaseDate = LocalDate.of(2005, 5, 28);
        book.setReleaseDate(newReleaseDate);
        assertEquals(newReleaseDate, book.getReleaseDate());

        String newAuthor = "Joshua Bloch";
        book.setAuthor(newAuthor);
        assertEquals(newAuthor, book.getAuthor());

        int newRating = 4;
        book.setRating(newRating);
        assertEquals(newRating, book.getRating());
    }


}
