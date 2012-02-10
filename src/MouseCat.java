import lexer.Lexer;


/**
 * Runs interpreter for the MOUSECAT programming language.
 * 
 * (Currently only the scanner has been implemented.)
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
        return;
    }
}
