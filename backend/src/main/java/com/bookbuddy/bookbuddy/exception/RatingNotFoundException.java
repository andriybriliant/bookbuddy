package com.bookbuddy.bookbuddy.exception;

public class RatingNotFoundException extends RuntimeException {
  public RatingNotFoundException(String id) {
    super("Rating not found with id: " + id);
  }
}
