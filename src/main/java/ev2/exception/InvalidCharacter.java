package ev2.exception;

/**
 * Custom exception that is thrown when an invalid character is next in the expression
 * @author Kevin
 */
public class InvalidCharacter extends Exception{
    
    /**
     * Default constructor
     */
    public InvalidCharacter() {
        super();
    }
    
    /**
     * Throws the exception with a custom message
     * @param message 
     */
    public InvalidCharacter(String message) {
        super(message);
    }
    
    /**
     * Throws the exception with a custom message and the cause
     * @param message
     * @param cause 
     */
    public InvalidCharacter (String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Throws the exception with the cause
     * @param cause 
     */
    public InvalidCharacter (Throwable cause) {
        super(cause);
    }
    
}
