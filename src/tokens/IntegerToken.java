package tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Defines an Integer Token. Valid integers may contain up to three digits
 * (0-9), the first of which must be nonzero, or be the integer 0. The character
 * and integer values are the same.
 * 
 * @author Eric Mercer (ewm10)
 */
public class IntegerToken extends Token
{
    private static final Type myTokenType = Token.Type.INTEGER;
    private static final Pattern myRegex = Pattern.compile("0|[1-9][0-9]{0,2}");
    private static final String myType = "integer";

    /**
     * Constructs a new IntegerToken. The character value is the given string
     * and the integer value is the given string parsed as an integer.
     * 
     * @param integerString
     */
    public IntegerToken (String integerString)
    {
        super();
        myCharacterValue = integerString;
        myIntegerValue = Integer.parseInt(integerString);
    }

    @Override
    public boolean isThisTypeOfToken (String parseableString)
    {
        Matcher integerMatcher = myRegex.matcher(parseableString);
        return (integerMatcher.matches());
    }

    @Override
    public Token createThisTypeOfToken (String parseableString)
    {
        return new IntegerToken(parseableString);
    }

    @Override
    public Type getTokenType ()
    {
        return myTokenType;
    }

    @Override
    public String getType ()
    {
        return myType;
    }

    @Override
    public String getCharacterValue ()
    {
        return myCharacterValue;
    }

    @Override
    public Integer getIntegerValue ()
    {
        return myIntegerValue;
    }

    /**
     * Private constructor used for creating a TokenFactory for this type of
     * token.
     */
    private IntegerToken ()
    {}

    /**
     * Creates a factory for use in identifying and constructing IntegerTokens.
     * 
     * @return new factory for IntegerTokens
     */
    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new IntegerToken());
    }

}
