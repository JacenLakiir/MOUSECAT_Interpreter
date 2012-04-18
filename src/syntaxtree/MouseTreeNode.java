package syntaxtree;

import tokens.IntegerToken;
import tokens.KeywordToken;
import tokens.VariableToken;

public class MouseTreeNode implements SyntaxTreeNode
{

    private static final Type myNodeType = SyntaxTreeNode.Type.MOUSE;
    private static final String myKeyword = "mouse";
    
    private VariableToken myVariable;
    private IntegerToken myI_Val, myJ_Val;
    private KeywordToken myDirection;
        
    public MouseTreeNode (VariableToken variable, IntegerToken i, IntegerToken j, KeywordToken direction)
    {
        myVariable = variable;
        myI_Val = i;
        myJ_Val = j;
        myDirection = direction;
    }
    
    @Override
    public void traverse ()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Type getType ()
    {
        return myNodeType;
    }

    @Override
    public boolean isThisTypeOfNode (KeywordToken keyword)
    {
        return (keyword.getType().equals(myKeyword));
    }

    @Override
    public SyntaxTreeNode createThisTypeOfNode (Object... STstackObjects)
    {
        return new MouseTreeNode((VariableToken) STstackObjects[0], (IntegerToken) STstackObjects[1],
                                 (IntegerToken) STstackObjects[2], (KeywordToken) STstackObjects[3]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private MouseTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing MouseTreeNodes.
     * 
     * @return new factory for MouseTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new MouseTreeNode());
    }
    
}
