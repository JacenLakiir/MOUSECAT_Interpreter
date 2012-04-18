package syntaxtree;

import tokens.IntegerToken;
import tokens.KeywordToken;

public class SizeTreeNode implements SyntaxTreeNode
{
    
    private static final Type myNodeType = SyntaxTreeNode.Type.SIZE;
    private static final String myKeyword = "size";
    
    private IntegerToken myI_Val, myJ_Val;
    private SyntaxTreeNode myStatements;
        
    public SizeTreeNode (IntegerToken i, IntegerToken j, SyntaxTreeNode statements)
    {
        myI_Val = i;
        myJ_Val = j;
        myStatements = statements;
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
        return new SizeTreeNode((IntegerToken) STstackObjects[0], (IntegerToken) STstackObjects[1],
                                (SyntaxTreeNode) STstackObjects[3]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private SizeTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing SizeTreeNodes.
     * 
     * @return new factory for SizeTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new SizeTreeNode());
    }

}
