package it.epicode.libreria.security;


public class FileSizeExceededException extends RuntimeException {
    public FileSizeExceededException(String message) {
        super(message);
    }
}

