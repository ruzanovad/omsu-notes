package org.example;

public class NoRootsException extends Exception {
    public NoRootsException() {
    }

    public NoRootsException(String message) {
        super(message);
    }

    public NoRootsException(String message, Throwable cause) {
        super(message, cause);
    }
}