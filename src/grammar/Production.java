package grammar;

public class Production
{

    private String myRuleNumber;
    private String myLHS;
    private String myRHS;
    
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
