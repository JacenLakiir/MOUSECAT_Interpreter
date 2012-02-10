package lexer;

/**
 * Handles lexer-specific runtime exceptions.
 * 
 * @author Eric Mercer (ewm10)
 */
@SuppressWarnings("serial")
public class LexerException extends RuntimeException
{
    /**
     * All possible types of LexerExceptions.
     */
    public static enum Type
    {
        INVALID_FILE_EXTENSION, CANNOT_OPEN_FILE, BAD_SYNTAX, INVALID_TOKEN
    };

    private Type myType;

    /**
     * Creates exception with given message for a lexer issue of the given type.
     * 
     * @param message explanation of the problem
     * @param type type of exception to be thrown
     */
    public LexerException (String message, Type type)
    {
        super(message);
        myType = type;
    }

    public Type getType ()
    {
        return myType;
    }
}
