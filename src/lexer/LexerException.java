package lexer;

@SuppressWarnings("serial")
public class LexerException extends RuntimeException
{
    public static enum Type
    {
        INVALID_FILE_EXTENSION, BAD_SYNTAX, INVALID_TOKEN
    };

    private Type myType;

    /**
     * Create exception with given message
     * 
     * @param message explanation of problem
     */
    public LexerException (String message)
    {
        this(message, Type.BAD_SYNTAX);
    }

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
