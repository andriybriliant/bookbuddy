package com.bookbuddy.bookbuddy.mapper;

import com.bookbuddy.bookbuddy.dto.book.BookResponse;
import com.bookbuddy.bookbuddy.model.Book;

public abstract class BookMapper {
    public static BookResponse toBookResponse(Book book) {
        BookResponse dto = new BookResponse();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setTags(book.getTags());
        dto.setAvgRating(book.getAvgRating());
        dto.setRatingsCount(book.getRatingsCount());
        dto.setCoverUrl(book.getCoverUrl());
        return dto;
    }
}
