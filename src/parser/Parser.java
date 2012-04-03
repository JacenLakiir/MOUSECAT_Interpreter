package parser;

import grammar.Production;
import java.util.Stack;
import lexer.Lexer;

/**
 * Parses a MOUSECAT program in order to determine syntactical correctness.
 * 
 * @author Eric Mercer (ewm10)
 */
public class Parser
{
    // single instance of parser
    private static Parser myParser;

    // state of the parser
    private String myState;
    private String mySymbol;
    private boolean wasFileChosen;
    
    private ParseTable myTable;
    private Stack<String> myParseStack;
    private Stack<Production> myRuleStack;
   
    /**
     * Private constructor used for restricting instantiation of Parser objects.
     * (Singleton pattern)
     */
    private Parser ()
    {
        myState = "0";
        mySymbol = "";
        wasFileChosen = Lexer.getInstance().wasFileChosen();
        
        myParseStack = new Stack<String>();
        myRuleStack = new Stack<Production>();
    }

    /**
     * Limits access to this class's constructor so that only one Parser is ever
     * instantiated during the lifetime of the program. This ensures one point
     * of access to the information stored in Parser's instance variables across
     * the interpreter without conflicts arising from the instantiation of other
     * Parsers. (Singleton pattern)
     * 
     * @return new Parser if never instantiated or reference to existing Parser
     */
    public static Parser getInstance ()
    {
        if (myParser == null)
        {
            myParser = new Parser();
        }
        return myParser;
    }
    
    /**
     * Builds a parsetable for the MouseCat programming language.
     */
    public void initializeParser ()
    {
        if (!wasFileChosen)     return;
        myTable = new ParseTable();
    }
    
    /**
     * Parses a MouseCat program symbol-by-symbol to produce rightmost derivations.
     */
    public void runParser ()
    {
        if (!wasFileChosen)     return;
        
        myParseStack.push(myState);
        mySymbol = Lexer.getInstance().getNextSymbol();
        
        Entry entry = myTable.lookupEntry(myState, mySymbol);
        String action = entry.getAction();
        
        while (!Entry.isAccept(action))
        {
            if (Entry.isShift(action))
            {
                performShift(entry);
            }
            else if (Entry.isReduce(action))
            {
                performReduce(entry);    
            }
            else if (Entry.isError(action))
            {
                ParserOutput.reportError();
                return;
            }
            entry = myTable.lookupEntry(myState, mySymbol);
            action = entry.getAction();
        }
        
        if (!mySymbol.equals("$"))
        {
            ParserOutput.reportError();
        }
        
        ParserOutput.printDerivations(myRuleStack);
    }

    /**
     * Shifts the current symbol (lookahead) and state number onto the parsing
     * stack. Transitions the state to the pushed state number and retrieves
     * the next lookahead.
     * 
     * @param entry
     */
    private void performShift (Entry entry)
    {
        myParseStack.push(mySymbol);
        myState = entry.getState();
        myParseStack.push(myState);
        mySymbol = Lexer.getInstance().getNextSymbol();
    }

    /**
     * Replaces the right-hand side (rhs) of the rewrite rule on top of the
     * parsing stack with the left-hand side (lhs). Transitions the state to
     * the one stored in the parse table entry associated with the current
     * state and lhs symbol.
     * 
     * @param entry
     */
    private void performReduce (Entry entry)
    {
        int sizeOfRHS = entry.sizeOfRHS();
        for (int i = 0; i < 2*sizeOfRHS; i++)
            myParseStack.pop();
        myState = myParseStack.peek();
        
        myParseStack.push(entry.ruleLHS());
        myRuleStack.push(entry.getRule());
        
        myState = myTable.lookupEntry(myState, entry.ruleLHS()).getState();
        myParseStack.push(myState);
    }
    
}
