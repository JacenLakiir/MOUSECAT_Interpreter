import lexer.Lexer;

/**
 * @author Eric Mercer (ewm10)
 */
public class Mousecat
{
    public static void main (String[] args)
    {
        Lexer lex = Lexer.getInstance();
        lex.initializeScanner();
        lex.runScanner();
        return;
    }
}
