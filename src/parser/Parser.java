package parser;

import java.util.Stack;

public class Parser
{
    // single instance of parser
    private static Parser myParser;

    // state of the parser
    private Stack myStack;
    private String myState;
   
    /**
     * Private constructor used for restricting instantiation of Parser objects.
     * (Singleton pattern)
     */
    private Parser ()
    {
        myStack = new Stack<String>();
        myState = "0";
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
    
    public void runParser ()
    {
        myStack.push(myState);
//        read (symbol)
//        entry = T[state, symbol]
//        while entry.action != accept
//        {
//            if entry.action == shift
//            {
//                push(symbol)
//                state = entry.state
//                push(state)
//                read(symbol)
//            }
//            else if entry.action == reduce
//            {
//                for (int i = 0; i < 2*size_rhs; i++)
//                    myStack.pop();
//                myState = myStack.peek()
//                myStack.push(entry.rule.lhs)
//                myState = T[state, entry.rule.lhs]
//                myStack.push(myState)        
//            }
//            else if entry.action == blank
//            {
//                error()
//            }
//            entry = T[state, symbol]
//        }
//        if symbol != $
//            error()
    }
}
