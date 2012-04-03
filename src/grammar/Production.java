package grammar;

/**
 * Defines a Production object. This stores a rule from the MouseCat
 * grammar in three parts: rule number, left-hand side (lhs), and
 * right-hand side (rhs).
 * 
 * @author Eric Mercer (ewm10)
 */
public class Production
{

    private String myRuleNumber;
    private String myLHS;
    private String myRHS;
    
    /**
     * Constructs a new Production
     * 
     * @param ruleNumber assigned number for parsing purposes
     * @param lhs rule's left-hand side
     * @param rhs rule's right-hand side
     */
    public Production (String ruleNumber, String lhs, String rhs)
    {
        myRuleNumber = ruleNumber;
        myLHS = lhs;
        myRHS = rhs;
    }
    
    public String getRuleNumber ()
    {
        return myRuleNumber;
    }
    
    public String getLHS ()
    {
        return myLHS;
    }
    
    public String getRHS ()
    {
        return myRHS;
    }
    
    @Override
    public String toString ()
    {
        StringBuilder production = new StringBuilder();
        production.append(myLHS);
        production.append(" ");
        production.append("-->");
        production.append(" ");
        production.append(myRHS);
        return production.toString();
    }
    
}
