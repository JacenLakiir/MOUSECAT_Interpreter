package syntaxtree;

import tokens.IntegerToken;
import tokens.KeywordToken;

public class RepeatTreeNode implements SyntaxTreeNode
{
    
    private static final Type myNodeType = SyntaxTreeNode.Type.REPEAT;
    private static final String myKeyword = "repeat";
    
    private IntegerToken myI_Val;
    private SyntaxTreeNode myStatements;
        
    public RepeatTreeNode (IntegerToken i, SyntaxTreeNode statements)
    {
        myI_Val = i;
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
        return new RepeatTreeNode((IntegerToken) STstackObjects[0], (SyntaxTreeNode) STstackObjects[1]);
    }
    
    /**
     * Private constructor used for creating a SyntaxTreeNodeFactory for this
     * type of node.
     */
    private RepeatTreeNode ()
    {}

    /**
     * Creates a factory for use in identifying and constructing RepeatTreeNodes.
     * 
     * @return new factory for RepeatTreeNodes
     */
    public static SyntaxTreeNodeFactory getFactory ()
    {
        return new SyntaxTreeNodeFactory(new RepeatTreeNode());
    }

}
