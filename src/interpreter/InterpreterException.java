package interpreter;

/**
 * Handles interpreter-specific runtime exceptions.
 * 
 * @author Eric Mercer (ewm10)
 */
@SuppressWarnings("serial")
public class InterpreterException extends RuntimeException
{
    /**
     * All possible types of LexerExceptions.
     */
    public static enum Type
    {
        NO_NODE_CREATED, NO_SHARED_ROOT
    };

    private Type myType;

    /**
     * Creates exception with given message for a lexer issue of the given type.
     * 
     * @param message explanation of the problem
     * @param type type of exception to be thrown
     */
    public InterpreterException (String message, Type type)
    {
        super(message);
        myType = type;
    }

    public Type getType ()
    {
        return myType;
    }
}
