package tokens;

import java.util.ArrayList;
import java.util.List;


/**
 * Defines a Keyword Token. There are 14 case-insensitive words recognized as
 * keywords. Keywords have no value.
 * 
 * @author Eric Mercer (ewm10)
 */
public class KeywordToken extends Token
{
    private static final Type myTokenType = Token.Type.KEYWORD;
    private static final List<String> recognizedKeywords = initializeKeywordList();
    private String myKeyword;

    /**
     * Constructs a new KeywordToken whose keyword is the given string. No
     * values are assigned. Its type is the given string cast to lowercase.
     * 
     * @param keyword
     */
    public KeywordToken (String keyword)
    {
        super();
        myKeyword = keyword;
        myType = keyword.toLowerCase();
    }

    @Override
    public boolean isThisTypeOfToken (String parseableString)
    {
        return (recognizedKeywords.contains(parseableString.toLowerCase()));
    }

    @Override
    public Token createThisTypeOfToken (String parseableString)
    {
        return new KeywordToken(parseableString);
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

    public String getKeyword ()
    {
        return myKeyword;
    }

    /**
     * Checks a given string against the list of Strings recognized as keywords.
     * 
     * @param parseableString
     * @return true if given string is a keyword
     */
    public static boolean isKeyword (String parseableString)
    {
        return (recognizedKeywords.contains(parseableString));
    }

    /**
     * Initializes a list of 14 Strings recognized as valid keywords.
     * 
     * @return list of keywords
     */
    private static List<String> initializeKeywordList ()
    {
        String[] recognizedKeywords =
            new String[] {
                    "begin",
                    "halt",
                    "cat",
                    "mouse",
                    "clockwise",
                    "move",
                    "north",
                    "south",
                    "east",
                    "west",
                    "hole",
                    "repeat",
                    "size",
                    "end" };
        List<String> keywordList = new ArrayList<String>();
        for (String keyword : recognizedKeywords)
            keywordList.add(keyword);
        return keywordList;
    }

    /**
     * Private constructor used for creating a TokenFactory for this type of
     * token.
     */
    private KeywordToken ()
    {}

    /**
     * Creates a factory for use in identifying and constructing KeywordTokens.
     * 
     * @return new factory for KeywordTokens
     */
    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new KeywordToken());
    }

}
