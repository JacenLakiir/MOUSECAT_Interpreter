package syntaxtree;

import tokens.IntegerToken;
import tokens.KeywordToken;
import tokens.VariableToken;

public class MoveTreeNode implements SyntaxTreeNode
{
    
    private static final Type myNodeType = SyntaxTreeNode.Type.MOVE;
    private static final String myKeyword = "move";
    
    private VariableToken myVariable;
    private IntegerToken myI_Val;
    
    public MoveTreeNode (VariableToken variable, IntegerToken i)
    {
        myVariable = variable;
        myI_Val = i;
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
        if (STstackObjects.length == 1)
            return new MoveTreeNode((VariableToken) STstackObjects[0], null);
        return new MoveTreeNode((VariableToken) STstackObjects[0], (IntegerToken) STstackObjects[1]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private MoveTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing MoveTreeNodes.
     * 
     * @return new factory for MoveTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new MoveTreeNode());
    }

}
