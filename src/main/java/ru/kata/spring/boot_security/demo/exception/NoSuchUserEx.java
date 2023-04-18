package ru.kata.spring.boot_security.demo.exception;

public class NoSuchUserEx  extends RuntimeException {
    public NoSuchUserEx(String message) {
        super(message);
    }
}
