package tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Defines a Variable Token. Valid variable names may contain any number of
 * letters and digits (as long as it is a keyword) and are case-insensitive. If
 * a variable name contains a digit and is of length 3 or less, then it must
 * have at least one letter. The character value is the lowercase variable name
 * and the integer value is 0.
 * 
 * @author Eric Mercer (ewm10)
 */
public class VariableToken extends Token
{
    private static final Type myTokenType = Token.Type.VARIABLE;
    private static final Pattern myRegex = Pattern.compile("[A-z|0-9]+");
    private static String myType = "variable";

    /**
     * Constructs a new VariableToken whose character value is the given string
     * cast to lowercase. Its integer value is 0.
     * 
     * @param keyword
     */
    public VariableToken (String variableName)
    {
        super();
        myCharacterValue = variableName.toLowerCase();
        myIntegerValue = 0;
        mySymbol = "v";
    }

    @Override
    public boolean isThisTypeOfToken (String parseableString)
    {
        Matcher variableMatcher = myRegex.matcher(parseableString);
        return (variableMatcher.matches() && isValidVariable(parseableString));
    }

    @Override
    public Token createThisTypeOfToken (String parseableString)
    {
        return new VariableToken(parseableString);
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
    
    @Override
    public String getSymbol ()
    {
        return mySymbol;
    }

    /**
     * Checks whether a given string matches the additional constraints imposed
     * on being classified as a variable token beyond matching the basic regular
     * expression description. If a variable name contains a digit and is of
     * length 3 or less, then it must have at least one letter to be valid.
     * 
     * @param parseableString
     * @return true if given string meets constraints
     */
    private static boolean isValidVariable (String parseableString)
    {
        boolean isValid = !KeywordToken.isKeyword(parseableString);
        if (parseableString.length() <= 3 && containsDigit(parseableString))
            isValid = isValid && containsLetter(parseableString);
        return isValid;

    }

    /**
     * Checks whether a given string contains at least one digit.
     * 
     * @param parseableString
     * @return true if given string includes a digit
     */
    private static boolean containsDigit (String parseableString)
    {
        Pattern digitRegex = Pattern.compile(".*[0-9].*");
        Matcher digitMatcher = digitRegex.matcher(parseableString);
        return digitMatcher.lookingAt();
    }

    /**
     * Checks whether a given string contains at least one letter.
     * 
     * @param parseableString
     * @return true if given string includes a letter
     */
    private static boolean containsLetter (String parseableString)
    {
        Pattern letterRegex = Pattern.compile(".*[A-z].*");
        Matcher letterMatcher = letterRegex.matcher(parseableString);
        return letterMatcher.lookingAt();
    }

    /**
     * Private constructor used for creating a TokenFactory for this type of
     * token.
     */
    private VariableToken ()
    {}

    /**
     * Creates a factory for use in identifying and constructing VariableTokens.
     * 
     * @return new factory for VariableTokens
     */
    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new VariableToken());
    }

}
