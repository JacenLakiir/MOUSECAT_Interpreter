package parser;

import grammar.Production;
import java.util.Stack;

/**
 * Various utility functions for printing lexer output.
 * 
 * @author Eric Mercer (ewm10)
 */
public class ParserOutput
{

    /**
     * Prints the rules applied by the parser to parse a syntactically
     * correct program in the order the rules would be used in a right-most
     * derivation.
     * 
     * @param ruleStack rules applied by the parser in reverse order
     */
    public static void printDerivations (Stack<Production> ruleStack)
    {
        while (!ruleStack.empty())
            System.out.println(ruleStack.pop());
    }
    
    /**
     * Prints output reporting that a program is not syntactically correct.
     */
    public static void reportError ()
    {
        System.err.println("Not syntactically correct.");
    }
    
}
