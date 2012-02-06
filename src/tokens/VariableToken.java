package tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableToken extends Token
{
    private static final Type myTokenType = Token.Type.VARIABLE;
    private static final Pattern myRegex = Pattern.compile("[A-z|0-9]+");
    private static String myType = "variable";
    private String myVariable;
   
    public VariableToken (String variableName)
    {
        super();
        myVariable = variableName.toLowerCase();
        myCharacterValue = variableName.toLowerCase();
        myIntegerValue = 0;
    }
    
    @Override
    public boolean isThisTypeOfToken (String parseableString)
    {
        Matcher variableMatcher = myRegex.matcher(parseableString);
        return (variableMatcher.lookingAt() && isValidVariable(parseableString));
    }

    @Override
    public Token createThisTypeOfToken (String parseableString)
    {
        return new VariableToken(parseableString);
    }
    
    public Type getTokenType ()
    {
        return myTokenType;
    }
    
    public String getType ()
    {
        return myType;
    }
    
    public String getCharacterValue ()
    {
        return myCharacterValue;
    }
    
    public Integer getIntegerValue ()
    {
        return myIntegerValue;
    }
    
    public String getVariable()
    {
        return myVariable;
    }
    
    private static boolean isValidVariable (String parseableString)
    {
        boolean isValid = !KeywordToken.isKeyword(parseableString);
        if (parseableString.length() <= 3 && containsDigit(parseableString))
            isValid = isValid && containsLetter(parseableString);
        return isValid;
                
    }

    private static boolean containsDigit (String parseableString)
    {
        Pattern digitRegex = Pattern.compile(".*[0-9].*");
        Matcher digitMatcher = digitRegex.matcher(parseableString);
        return digitMatcher.lookingAt();
    }
    
    private static boolean containsLetter (String parseableString)
    {
        Pattern letterRegex = Pattern.compile(".*[A-z].*");
        Matcher letterMatcher = letterRegex.matcher(parseableString);
        return letterMatcher.lookingAt();
    }
    
    private VariableToken ()
    {}

    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new VariableToken());
    }

}
