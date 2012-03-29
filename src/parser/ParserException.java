package parser;

/**
 * Handles parser-specific runtime exceptions.
 * 
 * @author Eric Mercer (ewm10)
 */
@SuppressWarnings("serial")
public class ParserException extends RuntimeException
{
    /**
     * All possible types of ParserExceptions.
     */
    public static enum Type
    {
        CANNOT_OPEN_FILE
    };

    private Type myType;

    /**
     * Creates exception with given message for a parser issue of the given type.
     * 
     * @param message explanation of the problem
     * @param type type of exception to be thrown
     */
    public ParserException (String message, Type type)
    {
        super(message);
        myType = type;
    }

    public Type getType ()
    {
        return myType;
    }
}
