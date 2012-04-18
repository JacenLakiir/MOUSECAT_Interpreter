package syntaxtree;

import tokens.KeywordToken;

public class SequenceTreeNode implements SyntaxTreeNode
{

    private static final Type myNodeType = SyntaxTreeNode.Type.SEQ;
    
    private SyntaxTreeNode myLeftSyntaxTree;
    private SyntaxTreeNode myRightSyntaxTree;
        
    public SequenceTreeNode (SyntaxTreeNode leftSyntaxTree, SyntaxTreeNode rightSyntaxTree)
    {
        myLeftSyntaxTree = leftSyntaxTree;
        myRightSyntaxTree = rightSyntaxTree;
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
        return false;
    }

    @Override
    public SyntaxTreeNode createThisTypeOfNode (Object... STstackObjects)
    {
        return new SequenceTreeNode((SyntaxTreeNode) STstackObjects[0], (SyntaxTreeNode) STstackObjects[1]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private SequenceTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing SequenceTreeNodes.
     * 
     * @return new factory for SequenceTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new SequenceTreeNode());
    }
    
}
