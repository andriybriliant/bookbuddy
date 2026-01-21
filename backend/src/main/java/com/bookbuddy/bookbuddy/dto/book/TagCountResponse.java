package com.bookbuddy.bookbuddy.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagCountResponse {
    private String tag;
    private int count;
}
