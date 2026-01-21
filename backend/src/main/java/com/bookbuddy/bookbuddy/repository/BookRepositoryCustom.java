package com.bookbuddy.bookbuddy.repository;

import com.bookbuddy.bookbuddy.model.Book;

import java.util.List;

public interface BookRepositoryCustom {
    void updateAvgRating(String bookId, double avgRating);
    void updateRatingsCount(String bookId, int count);
    List<Book> recommendBooks(List<String> tags, List<String> excludedBookIds);
}
