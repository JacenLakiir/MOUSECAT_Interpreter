package tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IntegerToken extends Token
{
    private static final Type myTokenType = Token.Type.INTEGER;
    private static final Pattern myRegex = Pattern.compile("0|[1-9][0-9]{0,2}");
    private static final String myType = "integer";

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
        return (integerMatcher.matches() && !KeywordToken.isKeyword(parseableString));
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

    private IntegerToken ()
    {}

    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new IntegerToken());
    }

}
