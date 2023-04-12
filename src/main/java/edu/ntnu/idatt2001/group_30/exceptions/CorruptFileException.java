package edu.ntnu.idatt2001.group_30.exceptions;

/**
 * An exception that is thrown when Link information a paths file has been corrupted.
 */
public class CorruptFileException extends RuntimeException{

    /**
     * Constructs a new CorruptFileException with no detail message.
     */
    public CorruptFileException() {

    }

    /**
     * Constructs a new CorruptFileException with the specified detail message.
     *
     * @param message The detail message, given as a String.
     */
    public CorruptFileException(String message) {
        super(message);
    }

    /**
     * Constructs a new CorruptFileException with the specified detail message and cause.
     *
     * @param message The detail message, given as a String
     * @param cause   The cause, given as a Throwable Object.
     */
    public CorruptFileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new CorruptFileException with the specified cause and a detail message of
     * {@code cause == null ? null : cause.toString()} (which usually contains the class and detail message of cause).
     *
     * @param cause The cause, given as a Throwable Object.
     */
    public CorruptFileException(Throwable cause) {
        super(cause);
    }
}
