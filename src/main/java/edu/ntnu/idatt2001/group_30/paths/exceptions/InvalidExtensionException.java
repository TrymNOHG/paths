package edu.ntnu.idatt2001.group_30.paths.exceptions;

/**
 * An exception that is thrown when an invalid file extension is used.
 */
public class InvalidExtensionException extends IllegalArgumentException{

    /**
     * Constructs a new InvalidExtensionException with no detail message.
     */
    public InvalidExtensionException() {
    }

    /**
     * Constructs a new InvalidExtensionException with the specified detail message.
     *
     * @param message The detail message, given as a String.
     */
    public InvalidExtensionException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidExtensionException with the specified detail message and cause.
     *
     * @param message The detail message, given as a String
     * @param cause   The cause, given as a Throwable Object.
     */
    public InvalidExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidExtensionException with the specified cause and a detail message of
     * {@code cause == null ? null : cause.toString()} (which usually contains the class and detail message of cause).
     *
     * @param cause The cause, given as a Throwable Object.
     */
    public InvalidExtensionException(Throwable cause) {
        super(cause);
    }
}

