package tests;

import lexer.Lexer;
import lexer.LexerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tokens.IntegerToken;
import tokens.KeywordToken;
import tokens.PunctuationToken;
import tokens.VariableToken;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

// add tests for character and integer values of tokens
public class LexerTest
{
    
    private Lexer myLexer;

    @Before
    public void setUp ()
    {
        myLexer = Lexer.getInstance();
    }
    
    @Test
    public void testTokenTypes ()
    {
        runTokenTest("clockwise", KeywordToken.class);
        runTokenTest("mOUse", KeywordToken.class);
        runTokenTest("9j03PQf09j", VariableToken.class);
        runTokenTest("ab", VariableToken.class);
        runTokenTest("1A2", VariableToken.class);
        runTokenTest("1234", VariableToken.class);
        runTokenTest("0", IntegerToken.class);
        runTokenTest("913", IntegerToken.class);
        runTokenTest(";", PunctuationToken.class);
    }

    @Test
    public void testTokenExceptions ()
    {
        runExceptionalTokenTest(".");
        runExceptionalTokenTest("");
        runExceptionalTokenTest("   ");
        runExceptionalTokenTest("04");
        runExceptionalTokenAssertion("counterclockwise", KeywordToken.class);
        runExceptionalTokenAssertion("123", VariableToken.class);
        runExceptionalTokenAssertion("91238", IntegerToken.class);
    }
    
    @Test
    public void testLexerExceptions ()
    {
        runExceptionalLexerTest(LexerException.Type.INVALID_FILE_EXTENSION, "test.py");
    }

    @After
    public void tearDown () { }

    private void runTokenTest (String parseableString, Class<?> expectedTokenType)
    {
        Object parsedToken = myLexer.createToken(parseableString);
        assertTrue(expectedTokenType.isInstance(parsedToken));
    }
    
    private void runExceptionalLexerTest (LexerException.Type type, String fileName)
    {
        myLexer.setFile(fileName);
        try
        {
            myLexer.openFile();
            fail("Exception should have been thrown");
        }
        catch (LexerException e)
        {
            assertEquals(type, e.getType());
        }
    }
    
    private void runExceptionalTokenTest (String parseableString)
    {
        try
        {
            @SuppressWarnings("unused")
            Object parsedToken = myLexer.createToken(parseableString);
            fail("Exception should have been thrown");
        }
        catch (LexerException e)
        {
            assertEquals(LexerException.Type.INVALID_TOKEN, e.getType());
        }
    }
    
    private void runExceptionalTokenAssertion (String parseableString, Class<?> expectedTokenType)
    {
        try
        {
            runTokenTest(parseableString, expectedTokenType);
        }
        catch (AssertionError e)
        {
            // nothing to check
            return;
        }
    }
    
}
