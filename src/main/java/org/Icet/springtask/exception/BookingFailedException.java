package org.Icet.springtask.exception;

public class BookingFailedException extends RuntimeException{
    public BookingFailedException(String message) {
        super(message);
    }
}
