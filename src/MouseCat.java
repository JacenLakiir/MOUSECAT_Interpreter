import lexer.Lexer;
import parser.Parser;

/**
 * Runs interpreter for the MOUSECAT programming language.
 * 
 * (Currently only the scanner and parser have been implemented.)
 * 
 * @author Eric Mercer (ewm10)
 */
public class MouseCat
{
    public static void main (String[] args)
    {
        Lexer lex = Lexer.getInstance();
        lex.initializeScanner();
        lex.runScanner();
        
        Parser pars = Parser.getInstance();
        pars.initializeParser();
        pars.runParser();
    }
}
