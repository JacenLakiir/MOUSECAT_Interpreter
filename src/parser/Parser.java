package parser;

import grammar.Production;
import interpreter.InterpreterException;
import java.util.List;
import java.util.Stack;
import syntaxtree.SequenceTreeNode;
import syntaxtree.SyntaxTreeNode;
import syntaxtree.SyntaxTreeNodeFactory;
import tokens.KeywordToken;
import tokens.Token;
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
    private final List<SyntaxTreeNodeFactory> typesOfNodes =
            SyntaxTreeNodeFactory.initialize();

    private String myState;
    private Token myToken;
    private String mySymbol;
    private boolean wasFileChosen;
    
    private ParseTable myTable;
    private Stack<String> myParseStack;
    private Stack<Production> myRuleStack;
    private Stack<Object> mySTstack;
   
    /**
     * Private constructor used for restricting instantiation of Parser objects.
     * (Singleton pattern)
     */
    private Parser ()
    {
        myState = "0";
        myToken = Lexer.getInstance().getNextToken();
        mySymbol = Lexer.getInstance().getNextSymbol();
        wasFileChosen = Lexer.getInstance().wasFileChosen();
        
        myParseStack = new Stack<String>();
        myRuleStack = new Stack<Production>();
        mySTstack = new Stack<Object>();
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
        
        Entry entry = myTable.lookupEntry(myState, mySymbol);
        String action = entry.getAction();
        
        while (!Entry.isAccept(action))
        {
            System.out.println(mySTstack);
            System.out.println();
            
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
        System.out.println(mySTstack);
        if (!mySymbol.equals("$"))
        {
            ParserOutput.reportError();
        }
        
        ParserOutput.printDerivations(myRuleStack);
    }
    
    public SyntaxTreeNode getSyntaxTreeRoot ()
    {
        if (mySTstack.size() != 1)
            throw new InterpreterException("Syntax tree does not have one root: " + mySTstack.size()
                                           + "elements left", InterpreterException.Type.NO_SHARED_ROOT);
        return (SyntaxTreeNode) mySTstack.peek();
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
        System.out.println("SHIFTING:\t" + myToken.getSymbol());
        mySTstack.push(myToken);
        myParseStack.push(mySymbol);
        myState = entry.getState();
        myParseStack.push(myState);
        
        myToken = Lexer.getInstance().getNextToken();
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
        
        updateSyntaxTree(entry, sizeOfRHS);   
        
        for (int i = 0; i < 2*sizeOfRHS; i++)
            myParseStack.pop();
        myState = myParseStack.peek();
        
        myParseStack.push(entry.ruleLHS());
        myRuleStack.push(entry.getRule());
        
        myState = myTable.lookupEntry(myState, entry.ruleLHS()).getState();
        myParseStack.push(myState);
    }

    private void updateSyntaxTree (Entry entry, int sizeOfRHS)
    {
        System.out.println("REDUCING:\t" + entry.getRule());
        
        if (entry.hasPunctuationInRHS())
            mySTstack.pop();    // discard punctuation symbol
        
        if (entry.containsTwoNonterminalsOnRHS())
        {
            System.out.println("Nonterminals");
            SyntaxTreeNode left = (SyntaxTreeNode) mySTstack.pop();
            SyntaxTreeNode right = (SyntaxTreeNode) mySTstack.pop();
            SyntaxTreeNode sharedRoot = new SequenceTreeNode(left, right);
            mySTstack.push(sharedRoot);
        }
        
        else if ((entry.containsAllTerminalsOnRHS() && sizeOfRHS > 1) || entry.hasRepeatRule())
        {
            System.out.println("Terminals\t" + sizeOfRHS);
            
            Object[] stStackObjects = new Object[sizeOfRHS-1];
            for (int i = 0; i < sizeOfRHS-1; i++)
                stStackObjects[sizeOfRHS-2-i] = mySTstack.pop();
            
            KeywordToken keyword = (KeywordToken) mySTstack.pop();
            System.out.println(keyword);

            for (SyntaxTreeNodeFactory nodeType : typesOfNodes)
            {
                if (nodeType.isThisTypeOfNode(keyword))
                {
                    SyntaxTreeNode reducedNode =
                            nodeType.createThisTypeOfNode(stStackObjects);
                    System.out.println(reducedNode.getClass().getName());
                    mySTstack.push(reducedNode);
                    return;
                }
            }
            throw new InterpreterException("No node created: " + stStackObjects,
                                           InterpreterException.Type.NO_NODE_CREATED);
        }
    }
    
}
