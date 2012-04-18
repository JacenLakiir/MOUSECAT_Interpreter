package syntaxtree;

import tokens.KeywordToken;

public abstract interface SyntaxTreeNode
{

    /**
     * All possible types of syntax tree nodes.
     */
    public static enum Type
    {
        SIZE, CAT, MOUSE, HOLE, SEQ, MOVE, CLOCKWISE, REPEAT
    };
    
    public abstract Type getType();
    
    public abstract void traverse ();
    
    /**
     * Tests whether a given object meets the definition of this type of node.
     * 
     * @param keyword
     * @return true if object meets definition
     */
    public abstract boolean isThisTypeOfNode (KeywordToken keyword);

    /**
     * Creates a SyntaxTreeNode object of this type by parsing the given objects as
     * appropriate for assigning values to the instance variables of this type.
     * 
     * @param STstackObjects
     * @return new SyntaxTreeNode object of this type
     */
    public abstract SyntaxTreeNode createThisTypeOfNode (Object... STstackObjects);
    
}
