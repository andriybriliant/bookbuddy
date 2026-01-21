package com.bookbuddy.bookbuddy.dto.user;

import lombok.Data;

@Data
public class TopUserWithDetailsResponse {
    private String email;
    private long ratingsCount;
}
