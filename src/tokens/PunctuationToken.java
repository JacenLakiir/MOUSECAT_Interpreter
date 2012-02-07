package tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PunctuationToken extends Token
{
    private static final Type myTokenType = Token.Type.PUNCTUATION_SYMBOL;
    private static final Pattern myRegex = Pattern.compile(";");
    private static final String myType = ";";

    public PunctuationToken (String integerString)
    {
        super();
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

    private PunctuationToken ()
    {}

    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new PunctuationToken());
    }

}
