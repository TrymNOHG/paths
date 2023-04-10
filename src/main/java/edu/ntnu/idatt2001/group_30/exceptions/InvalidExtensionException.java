package edu.ntnu.idatt2001.group_30.exceptions;

public class InvalidExtensionException extends IllegalArgumentException{

    public InvalidExtensionException() {
    }

    public InvalidExtensionException(String s) {
        super(s);
    }

    public InvalidExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExtensionException(Throwable cause) {
        super(cause);
    }
}

