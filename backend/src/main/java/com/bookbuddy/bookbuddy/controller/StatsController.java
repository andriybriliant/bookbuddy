package com.bookbuddy.bookbuddy.controller;

import com.bookbuddy.bookbuddy.dto.book.BookResponse;
import com.bookbuddy.bookbuddy.dto.book.TagCountResponse;
import com.bookbuddy.bookbuddy.dto.user.TopUserWithDetailsResponse;
import com.bookbuddy.bookbuddy.model.User;
import com.bookbuddy.bookbuddy.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/top-rated-books")
    public List<BookResponse> getTopRatedBooks() {
        return statsService.getTopRatedBooks();
    }

    @GetMapping("/most-active-users")
    //@PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public List<TopUserWithDetailsResponse> getMostActiveUsers() {
        return statsService.getMostActiveUsers();
    }

    @GetMapping("/most-popular-tags")
    public List<TagCountResponse> getMostPopularTags() {
        return statsService.getMostPopularTags();
    }
}