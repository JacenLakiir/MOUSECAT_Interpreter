package interpreter;

import lexer.Lexer;
import parser.Parser;
import princeton.stdlib.StdDraw;
import syntaxtree.SyntaxTreeNode;

public class Interpreter
{
    // Animate 2 times a second if possible
    private static final int DEFAULT_DELAY = 1000 / 2; // in milliseconds
    
    // single instance of interpreter
    private static Interpreter myInterpreter;
    
    // state of interpreter
    private SyntaxTreeNode myCurrentInstruction;
    private boolean wasFileChosen;

    
    /**
     * Private constructor used for restricting instantiation of Interpreter objects.
     * (Singleton pattern)
     */
    private Interpreter ()
    {
        wasFileChosen = Lexer.getInstance().wasFileChosen();
    }

    /**
     * Limits access to this class's constructor so that only one Interpreter is ever
     * instantiated during the lifetime of the program. This ensures one point
     * of access to the information stored in Interpreter instance variables across
     * the interpreter without conflicts arising from the instantiation of other
     * Interpreters. (Singleton pattern)
     * 
     * @return new Interpreter if never instantiated or reference to existing Interpreter
     */
    public static Interpreter getInstance ()
    {
        if (myInterpreter == null)
        {
            myInterpreter = new Interpreter();
        }
        return myInterpreter;
    }
    
    public void initializeInterpreter ()
    {
        if (!wasFileChosen)     return;
        myCurrentInstruction = Parser.getInstance().getSyntaxTreeRoot();
    }
    
    public void runInterpreter ()
    {
        if (!wasFileChosen)     return;   
        // start traversing through syntax tree
        // first node is size node, get i and j

        // set x- and y-scale
//        StdDraw.setXscale(0, i);
//        StdDraw.setYscale(0, j);
        
        // draws a black canvas background
//        StdDraw.setPenColor(Color.BLACK); 
        //StdDraw.filledSquare(N/2.0, N/2.0, N/2.0); --> changed to filled rectangle that uses i and j

        // traverse through seq or statement node of size node
        // left-to-right traversal (preorder?)

        // animate according to rules
        // display error messages as they occur
        
        // draw mouse, cat, or hole at coordinates instructed
        // clockwise rotates mouse, cat, or hole
        // move mouse, cat, or hole in current direction by specific amount
        // how do you do for loops for repeat???
        
        // halt at end
    }
    
}
