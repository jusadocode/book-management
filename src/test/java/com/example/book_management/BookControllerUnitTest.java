package com.example.book_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

class BookControllerUnitTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book("Clean Code", LocalDate.of(2001, 2, 20), "Robert C. Martin", 5);
        Book book2 = new Book("Effective Java", LocalDate.of(2001, 5, 28), "Joshua Bloch", 5);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> result = bookController.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());
        assertEquals("Effective Java", result.get(1).getTitle());
    }

    @Test
    void testRateBookSuccess() {
        Book book = new Book("Clean Code", LocalDate.of(2001, 2, 20), "Robert C. Martin", 5);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book)); // Use Integer

        String response = bookController.rateBook(1, 4); // Ensure correct parameters

        assertEquals("Book rating updated successfully!", response);
        verify(bookRepository).rateBookById(1, 4);
    }

    @Test
    void testRateBookWithUnavailableID() {
        when(bookRepository.findById(66)).thenReturn(Optional.empty());

        String response = bookController.rateBook(55, 4);

        assertEquals("Book with ID 55 not found.", response);
    }

    @Test
    void testRateBookWithInvalidRating() {
        Book book = new Book("Clean Code", LocalDate.of(2001, 2, 20), "Robert C. Martin", 5);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        String response = bookController.rateBook(1, 6); // Test with invalid rating

        assertEquals("Rating must be between 1 and 5.", response);
    }
}
