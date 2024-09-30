package com.example.book_management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT bk from Book bk WHERE " 
        +"(:title IS NULL OR bk.title LIKE %:title%) AND " 
        +"(:year IS NULL OR YEAR(bk.releaseDate) = :year) AND " 
        +"(:author IS NULL OR bk.author LIKE %:author%) AND "
        +"(:rating IS NULL OR bk.rating = :rating)")
    List<Book> findByFilters(@Param("title") String title, 
                              @Param("year") Integer year,
                              @Param("author") String author, 
                              @Param("rating") Integer rating);

    @Modifying
    @Query("UPDATE Book bk SET rating = :rating WHERE bk.id = :id" )
    void rateBookById(@Param("id") Integer id, @Param("rating") Integer rating);


}