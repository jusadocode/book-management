package com.example.book_management;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = new Book("Clean Code", LocalDate.of(2001, 2, 20), "Robert C. Martin", 5);
        Book book2 = new Book("Effective Java", LocalDate.of(2001, 5, 28), "Joshua Bloch", 5);
        bookRepository.save(book); 
        bookRepository.save(book2); 
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testRateBookSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/rate/1")
                .param("rating", "3"))  
                .andExpect(MockMvcResultMatchers.status().isOk())   
                .andExpect(MockMvcResultMatchers.content().string("Book rating updated successfully!"));  
    }

    @Test
    @Order(2)
    void testRateBookWithInvalidRating() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/rate/1")
                .param("rating", "6"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Rating must be between 1 and 5."));
    }

    @Test
    @Order(3)
void testRateBookWithUnavailableID() throws Exception {
    Integer id = 55;
    mvc.perform(MockMvcRequestBuilders.put("/rate/{id}", id) 
            .param("rating", "4")) 
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Book with ID " + id + " not found.")); 
}
    @Test
    @Order(4)
    void testGetAllBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Clean Code"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Effective Java"));
    }

    @Test
    @Order(5)
    void testGetFilteredBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/filter")
                .param("author", "Robert C. Martin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    @Order(6)
    void testGetFilteredBooksNoResults() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/filter")
                .param("author", "Raaaa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    @Order(7)
    void testGetFilteredBooksByYear() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/filter")
                .param("releaseYear", "2001"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @Order(8)
    void testGetFilteredBooksByYearAndRating() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/filter")
                .param("releaseYear", "2001")
                .param("rating", "5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
    @Test
    @Order(9)
    void testBookIdGenerationOrder() {
        bookRepository.deleteAll();

        Book book1 = new Book("Clean Code", LocalDate.of(2001, 2, 20), "Robert C. Martin", 5);
        Book book2 = new Book("Effective Java", LocalDate.of(2001, 5, 28), "Joshua Bloch", 5);
        Book book3 = new Book("Java Concurrency in Practice", LocalDate.of(2006, 5, 28), "Brian Goetz", 4);
        
        Book savedBook1 = bookRepository.save(book1);
        Book savedBook2 = bookRepository.save(book2);
        Book savedBook3 = bookRepository.save(book3);

        assertEquals((long)savedBook2.getId(),(long)savedBook1.getId() + 1);
        assertEquals((long)savedBook3.getId(),(long)savedBook2.getId() + 1);
    }

    
}
