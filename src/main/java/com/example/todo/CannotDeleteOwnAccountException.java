package com.example.todo;

public class CannotDeleteOwnAccountException extends RuntimeException {

    public CannotDeleteOwnAccountException(String message) {
        super(message);
    }
}