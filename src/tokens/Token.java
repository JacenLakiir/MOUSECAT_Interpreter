package tokens;

/**
 * Defines an abstract Object Token which has a type, a character value, and an
 * integer value.
 * 
 * @author Eric Mercer (ewm10)
 */
public abstract class Token
{
    /**
     * All possible types of tokens.
     */
    public static enum Type
    {
        KEYWORD, VARIABLE, INTEGER, PUNCTUATION_SYMBOL, UNKNOWN
    };

    protected static Type myTokenType;
    protected static String myType;
    protected String myCharacterValue;
    protected Integer myIntegerValue;

    public Token ()
    {}

    /**
     * Tests whether a given string meets the definition of this type of token.
     * This involves matching, in whole or in part, a regular expression
     * denoting this type of token and any additional restrictions on syntax,
     * such as length of the string.
     * 
     * @param parseableString
     * @return true if string meets definition
     */
    public abstract boolean isThisTypeOfToken (String parseableString);

    /**
     * Creates a Token object of this type by parsing the given string as
     * appropriate for assigning values to the instance variables of this type.
     * 
     * @param parseableString
     * @return new Token object of this type
     */
    public abstract Token createThisTypeOfToken (String parseableString);

    public Type getTokenType ()
    {
        return null;
    }

    public String getType ()
    {
        return null;
    }

    public String getCharacterValue ()
    {
        return null;
    }

    public Integer getIntegerValue ()
    {
        return null;
    }

    @Override
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        result.append(getType() + " : ");
        result.append("[charVal = " + getCharacterValue() + ", ");
        result.append("intVal = " + getIntegerValue() + "]");
        return result.toString();
    }
}
