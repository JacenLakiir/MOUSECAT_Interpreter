package syntaxtree;

import tokens.KeywordToken;
import tokens.VariableToken;

public class ClockwiseTreeNode implements SyntaxTreeNode
{
    
    private static final Type myNodeType = SyntaxTreeNode.Type.CLOCKWISE;
    private static final String myKeyword = "clockwise";
    
    private VariableToken myVariable;
        
    public ClockwiseTreeNode (VariableToken variable)
    {
        myVariable = variable;
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
        return new ClockwiseTreeNode((VariableToken) STstackObjects[0]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private ClockwiseTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing ClockwiseTreeNodes.
     * 
     * @return new factory for ClockwiseTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new ClockwiseTreeNode());
    }

}
