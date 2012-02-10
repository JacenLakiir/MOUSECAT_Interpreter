package tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating tokens based on given input.
 * 
 * @author Eric Mercer (ewm10)
 */
public class TokenFactory
{
    private Token myToken;

    /**
     * Constructs a TokenFactory for the given token.
     * 
     * @param token
     */
    public TokenFactory (Token token)
    {
        myToken = token;
    }

    /**
     * Calls the isThisTypeOfToken() method defined by the class of this token type.
     * 
     * @param parseableString
     * @return true if string meets class-specific definition
     */
    public boolean isThisTypeOfToken (String parseableString)
    {
        return myToken.isThisTypeOfToken(parseableString);
    }

    /**
     * Calls the createThisTypeOfToken() method defined by the class of this token type.
     * 
     * @param parseableString
     * @return new Token object using class-specific constructor
     */
    public Token createThisTypeOfToken (String parseableString)
    {
        return myToken.createThisTypeOfToken(parseableString);
    }

    /**
     * Initializes a list of TokenFactories for all defined types of tokens.
     * 
     * @return list of TokenFactories
     */
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
