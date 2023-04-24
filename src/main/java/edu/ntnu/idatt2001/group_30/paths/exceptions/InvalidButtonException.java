package edu.ntnu.idatt2001.group_30.paths.exceptions;

/**
 * An exception that is thrown when an invalid ButtonType is used.
 */
public class InvalidButtonException extends RuntimeException{

    /**
     * Constructs a new InvalidButtonException with no detail message.
     */
    public InvalidButtonException() {
    }

    /**
     * Constructs a new InvalidButtonException with the specified detail message.
     *
     * @param message The detail message, given as a String.
     */
    public InvalidButtonException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidButtonException with the specified detail message and cause.
     *
     * @param message The detail message, given as a String
     * @param cause   The cause, given as a Throwable Object.
     */
    public InvalidButtonException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidButtonException with the specified cause and a detail message of
     * {@code cause == null ? null : cause.toString()} (which usually contains the class and detail message of cause).
     *
     * @param cause The cause, given as a Throwable Object.
     */
    public InvalidButtonException(Throwable cause) {
        super(cause);
    }

}
