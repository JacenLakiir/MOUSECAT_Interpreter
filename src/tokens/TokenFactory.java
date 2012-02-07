package tokens;

import java.util.ArrayList;
import java.util.List;


public class TokenFactory
{
    public Token myToken;

    public TokenFactory ()
    {}

    public TokenFactory (Token token)
    {
        myToken = token;
    }

    public boolean isThisTypeOfToken (String parseableString)
    {
        return myToken.isThisTypeOfToken(parseableString);
    }

    public Token createThisTypeOfToken (String parseableString)
    {
        return myToken.createThisTypeOfToken(parseableString);
    }

    public static List<TokenFactory> initialize ()
    {
        TokenFactory[] tokenTypes =
            new TokenFactory[] {
                    KeywordToken.getFactory(),
                    VariableToken.getFactory(),
                    IntegerToken.getFactory(),
                    PunctuationToken.getFactory() };
        List<TokenFactory> typesOfTokens = new ArrayList<TokenFactory>();
        for (TokenFactory typeFactory : tokenTypes)
            typesOfTokens.add(typeFactory);
        return typesOfTokens;
    }

}
