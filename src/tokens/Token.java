package tokens;

public abstract class Token
{
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

    public abstract boolean isThisTypeOfToken (String parseableString);

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
