package tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Defines a Punctuation Token. The only valid punctuation symbol is semicolon
 * (";"). Punctuation Tokens do not have values.
 * 
 * @author Eric Mercer (ewm10)
 */
public class PunctuationToken extends Token
{
    private static final Type myTokenType = Token.Type.PUNCTUATION_SYMBOL;
    private static final Pattern myRegex = Pattern.compile(";");
    private static final String myType = ";";

    /**
     * Constructs a new PunctuationToken. No values are assigned.
     * 
     * @param keyword
     */
    public PunctuationToken (String integerString)
    {
        super();
        mySymbol = ";";
    }

    @Override
    public boolean isThisTypeOfToken (String parseableString)
    {
        Matcher punctationMatcher = myRegex.matcher(parseableString);
        return (punctationMatcher.matches());
    }

    @Override
    public Token createThisTypeOfToken (String parseableString)
    {
        return new PunctuationToken(parseableString);
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
    public String getSymbol ()
    {
        return mySymbol;
    }

    /**
     * Private constructor used for creating a TokenFactory for this type of
     * token.
     */
    private PunctuationToken ()
    {}

    /**
     * Creates a factory for use in identifying and constructing PunctuationTokens.
     * 
     * @return new factory for PunctuationTokens
     */
    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new PunctuationToken());
    }

}
