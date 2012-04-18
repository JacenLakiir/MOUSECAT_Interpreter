package syntaxtree;

import tokens.IntegerToken;
import tokens.KeywordToken;
import tokens.VariableToken;

public class CatTreeNode implements SyntaxTreeNode
{

    private static final Type myNodeType = SyntaxTreeNode.Type.CAT;
    private static final String myKeyword = "cat";
    
    private VariableToken myVariable;
    private IntegerToken myI_Val, myJ_Val;
    private KeywordToken myDirection;
        
    public CatTreeNode (VariableToken variable, IntegerToken i, IntegerToken j, KeywordToken direction)
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
        return new CatTreeNode((VariableToken) STstackObjects[0], (IntegerToken) STstackObjects[1],
                               (IntegerToken) STstackObjects[2], (KeywordToken) STstackObjects[3]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private CatTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing CatTreeNodes.
     * 
     * @return new factory for CatTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new CatTreeNode());
    }

}
