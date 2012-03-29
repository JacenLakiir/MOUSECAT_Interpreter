package tests;

import parser.Parser;
import org.junit.After;
import org.junit.Before;

/**
 * JUnit tests for parser exceptions.
 * 
 * @author Eric Mercer (ewm10)
 */
public class ParserTest
{
    
    private Parser myParser;

    @Before
    /**
     * Instantiate necessary variables before beginning tests.
     */
    public void setUp ()
    {
        myParser = Parser.getInstance();
    }
    
    @After
    public void tearDown ()
    {}
}
