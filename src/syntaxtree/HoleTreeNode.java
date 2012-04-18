package syntaxtree;

import tokens.IntegerToken;
import tokens.KeywordToken;

public class HoleTreeNode implements SyntaxTreeNode
{

    private static final Type myNodeType = SyntaxTreeNode.Type.HOLE;
    private static final String myKeyword = "hole";
    
    private IntegerToken myI_Val, myJ_Val;
        
    public HoleTreeNode (IntegerToken i, IntegerToken j)
    {
        myI_Val = i;
        myJ_Val = j;
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
        return new HoleTreeNode((IntegerToken) STstackObjects[0], (IntegerToken) STstackObjects[1]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private HoleTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing HoleTreeNodes.
     * 
     * @return new factory for HoleTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new HoleTreeNode());
    }

}
