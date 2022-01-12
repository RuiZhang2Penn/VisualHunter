package com.laioffer.jupiter.service;

public class TwitchException extends RuntimeException {
  public TwitchException(String errorMessage) {
    super(errorMessage);
  }
}