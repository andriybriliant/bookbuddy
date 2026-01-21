package com.bookbuddy.bookbuddy.service;

import com.bookbuddy.bookbuddy.dto.book.BookResponse;
import com.bookbuddy.bookbuddy.dto.book.TagCountResponse;
import com.bookbuddy.bookbuddy.dto.user.TopUserWithDetailsResponse;
import com.bookbuddy.bookbuddy.mapper.BookMapper;
import com.bookbuddy.bookbuddy.model.Book;
import com.bookbuddy.bookbuddy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final MongoTemplate mongoTemplate;

    // top 3 rated books
    public List<BookResponse> getTopRatedBooks() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "avgRating")),
                Aggregation.limit(3)
        );

        List<Book> books = mongoTemplate.aggregate(aggregation, "books", Book.class).getMappedResults();

        return books.stream().map(BookMapper::toBookResponse).toList();
    }

    // most active users
    public List<TopUserWithDetailsResponse> getMostActiveUsers() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("userId").count().as("ratingsCount"),
                Aggregation.project("ratingsCount").and("_id").as("userId"),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "ratingsCount")),
                Aggregation.limit(5)
        );

        return mongoTemplate.aggregate(aggregation, "ratings", TopUserWithDetailsResponse.class).getMappedResults();
    }

    // most popular tags
    public List<TagCountResponse> getMostPopularTags() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("tags"),
                Aggregation.group("tags").count().as("count"),
                Aggregation.project("count").and("_id").as("tag"),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "count"))
        );

        return mongoTemplate.aggregate(aggregation, "books", TagCountResponse.class)
                .getMappedResults();
    }
}