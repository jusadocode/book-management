package com.example.book_management;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private LocalDate releaseDate;
    private String author;
    private Integer rating;

    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }
    
      public String getTitle() {
        return title;
      }
    
      public void setTitle(String title) {
        this.title = title;
      }
    
      public LocalDate getReleaseDate() {
        return releaseDate;
      }
    
      public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
      }

      public String getAuthor() {
        return author;
      }
    
      public void setAuthor(String author) {
        this.author = author;
      }

      public Integer getRating() {
        return rating;
      }
    
      public void setRating(Integer rating) {
        this.rating = rating;
      }

    
}
