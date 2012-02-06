package tokens;

import java.util.ArrayList;
import java.util.List;

public class KeywordToken extends Token
{
    private static final Type myTokenType = Token.Type.KEYWORD;
    private static final List<String> recognizedKeywords = initializeKeywordList();
    private String myKeyword;
    
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
    
    public Type getTokenType ()
    {
        return myTokenType;
    }
    
    public String getType ()
    {
        return myType;
    }
    
    public String getKeyword()
    {
        return myKeyword;
    }
        
    public static boolean isKeyword (String parseableString)
    {
        return (recognizedKeywords.contains(parseableString));
    }
    
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
    
    private KeywordToken ()
    {}

    public static TokenFactory getFactory ()
    {
        return new TokenFactory(new KeywordToken());
    }
    
}
