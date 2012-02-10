package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import lexer.Lexer;
import lexer.LexerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tokens.IntegerToken;
import tokens.KeywordToken;
import tokens.PunctuationToken;
import tokens.VariableToken;


/**
 * JUnit tests for parsing tokens and token/lexer exceptions.
 * 
 * @author Eric Mercer (ewm10)
 */
public class LexerTest
{

    private Lexer myLexer;

    @Before
    /**
     * Instantiate necessary variables before beginning tests.
     */
    public void setUp ()
    {
        myLexer = Lexer.getInstance();
    }
    
    @Test
    /**
     * Runs a suite of tests to check whether exceptions are thrown for
     * various file access operations, such as choosing and opening a file.
     */
    public void testLexerExceptions ()
    {
        runExceptionalLexerTest(LexerException.Type.INVALID_FILE_EXTENSION, "test.py");
        runExceptionalLexerTest(LexerException.Type.CANNOT_OPEN_FILE, "test.mc");
    }

    @Test
    /**
     * Runs a suite of tests to check whether tokens are being created
     * correctly.
     */
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
    /**
     * Runs a suite of tests to check whether exceptions are thrown for
     * various invalid tokens and checks some overlapping cases whether
     * a string could be accidentally parsed as a wrong type of token.
     */
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

    @After
    public void tearDown ()
    {}

    /**
     * Tests whether a non-valid file will throw the correct LexerException.
     * 
     * @param type expected LexerException type
     * @param fileName path of file to be opened
     */
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
    
    /**
     * Tests whether the token created from the given string is of the
     * correct type (i.e. whether parseableString is an instance of
     * the class given as expectedTokenType).
     * 
     * @param parseableString
     * @param expectedTokenType
     */
    private void runTokenTest (String parseableString, Class<?> expectedTokenType)
    {
        Object parsedToken = myLexer.createToken(parseableString);
        assertTrue(expectedTokenType.isInstance(parsedToken));
    }

    /**
     * Test whether the given string is successfully identified as an invalid
     * token.
     * 
     * @param parseableString
     */
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

    /**
     * Tests whether the token created from the given string is of the incorrect
     * type (i.e. whether the assertion that parseableString is an instance of
     * the class given as expectedTokenType fails).
     * 
     * @param parseableString
     * @param expectedTokenType
     */
    private void runExceptionalTokenAssertion (String parseableString, Class<?> expectedTokenType)
    {
        try
        {
            runTokenTest(parseableString, expectedTokenType);
        }
        catch (AssertionError e)
        {
            // nothing else to check (just checking for AssertionError)
            return;
        }
    }

}
