package syntaxtree;

import java.util.ArrayList;
import java.util.List;
import tokens.KeywordToken;

/**
 * Factory for creating SyntaxTreeNodes based on given input.
 * 
 * @author Eric Mercer (ewm10)
 */
public class SyntaxTreeNodeFactory
{
    private SyntaxTreeNode mySyntaxTreeNode;

    /**
     * Constructs a SyntaxTreeNodeFactory for the given syntax tree node.
     * 
     * @param node
     */
    public SyntaxTreeNodeFactory (SyntaxTreeNode node)
    {
        mySyntaxTreeNode = node;
    }

    /**
     * Calls the isThisTypeOfNode() method defined by the class of this syntax
     * tree node type.
     * 
     * @param keyword
     * @return true if string meets class-specific definition
     */
    public boolean isThisTypeOfNode (KeywordToken keyword)
    {
        return mySyntaxTreeNode.isThisTypeOfNode(keyword);
    }

    /**
     * Calls the createThisTypeOfNode() method defined by the class of this
     * token type.
     * 
     * @param STstackObjects
     * @return new SyntaxTreeNode object using class-specific constructor
     */
    public SyntaxTreeNode createThisTypeOfNode (Object... STstackObjects)
    {
        return mySyntaxTreeNode.createThisTypeOfNode(STstackObjects);
    }

    /**
     * Initializes a list of SyntaxTreeNodeFactories for all defined types
     * of nodes.
     * 
     * @return list of SyntaxTreeNodeFactories
     */
    public static List<SyntaxTreeNodeFactory> initialize ()
    {
        SyntaxTreeNodeFactory[] nodeTypes =
            new SyntaxTreeNodeFactory[] {
                    CatTreeNode.getFactory(),
                    ClockwiseTreeNode.getFactory(),
                    HoleTreeNode.getFactory(),
                    MouseTreeNode.getFactory(),
                    MoveTreeNode.getFactory(),
                    RepeatTreeNode.getFactory(),
                    SequenceTreeNode.getFactory(),
                    SizeTreeNode.getFactory() };
        List<SyntaxTreeNodeFactory> typesOfNodes = new ArrayList<SyntaxTreeNodeFactory>();
        for (SyntaxTreeNodeFactory typeFactory : nodeTypes)
            typesOfNodes.add(typeFactory);
        return typesOfNodes;
    }

}