package myg;

/**
 * Custom exception class for MyG to handle user input errors and file corruption.
 */
public class MyGException extends Exception {
    /**
     * Constructs a MyGException with the specified detail message.
     *
     * @param message The detail message.
     */
    public MyGException(String message) {
        super(message);
    }
}