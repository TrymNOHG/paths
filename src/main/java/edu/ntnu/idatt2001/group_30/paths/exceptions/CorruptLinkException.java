package edu.ntnu.idatt2001.group_30.paths.exceptions;

/**
 * An exception that is thrown when Link information a paths file has been corrupted.
 */
public class CorruptLinkException extends RuntimeException {

    /**
     * Constructs a new CorruptLinkException with no detail message.
     */
    public CorruptLinkException() {}

    /**
     * Constructs a new CorruptLinkException with the specified detail message.
     *
     * @param message The detail message, given as a String.
     */
    public CorruptLinkException(String message) {
        super(message);
    }

    /**
     * Constructs a new CorruptLinkException with the specified detail message and cause.
     *
     * @param message The detail message, given as a String
     * @param cause   The cause, given as a Throwable Object.
     */
    public CorruptLinkException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new CorruptLinkException with the specified cause and a detail message of
     * {@code cause == null ? null : cause.toString()} (which usually contains the class and detail message of cause).
     *
     * @param cause The cause, given as a Throwable Object.
     */
    public CorruptLinkException(Throwable cause) {
        super(cause);
    }
}
