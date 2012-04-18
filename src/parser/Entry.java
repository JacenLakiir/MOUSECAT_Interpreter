package parser;

import grammar.Grammar;
import grammar.Production;

/**
 * Defines an Entry in a ParseTable. Each Entry includes three pieces of
 * information: state, action, and grammar rule. Some fields may be empty or
 * null depending on the type of action specified.
 * 
 * @author Eric Mercer (ewm10)
 */
public class Entry
{
    private String myState;
    private String myAction;
    private Production myRule;
    
    /**
     * Constructs a new Entry. No state value is stored if action is 'Error'. No
     * rule is stored unless the action is 'Reduce'.
     * 
     * @param state where to transition
     * @param action parsing instruction
     */
    public Entry (String state, String action)
    {
        
        myState = determineState(action);
        myAction = action;
        myRule = determineRule(action);
    }
    
    /**
     * Calculates the size of the right-hand side (rhs) of this
     * Entry's rule.
     * 
     * @return number of symbols on rhs of production 
     */
    public int sizeOfRHS ()
    {
        if (myRule == null)
            return 0;
        return myRule.getRHS().split("\\s+").length;
    }
    
    /**
     * Determines whether the right-hand size (rhs) of this
     * Entry's rule contains two nonterminals.
     * 
     * @return true if rhs contains two nonterminals
     */
    public boolean containsTwoNonterminalsOnRHS ()
    {
        if (myRule == null)
            return false;
        int count = 0;
        String[] symbols = myRule.getRHS().split("\\s+");
        for (String s : symbols)
        {
            if (s.matches("[LS]"))
                count++;
        }
        return (count == 2);
    }
    
    /**
     * Determines whether the right-hand size (rhs) of this
     * Entry's rule contains all terminals.
     * 
     * @return true if rhs contains all terminals
     */
    public boolean containsAllTerminalsOnRHS ()
    {
        if (myRule == null)
            return false;
        if (myRule.equals(Grammar.getProductionList().get(1)))
            return true;
        String[] symbols = myRule.getRHS().split("\\s+");
        for (String s : symbols)
        {
            if (s.matches("[LS]"))
                return false;
        }
        return true;
    }
    
    /**
     * Determines whether this Entry's rule contains punctuation
     * on the right-hand side (rhs).
     * 
     * @return true if rhs contains punctuation
     */
    public boolean hasPunctuationInRHS ()
    {
        if (myRule == null)
            return false;
        Production rule2 = Grammar.getProductionList().get(2);
        Production rule3 = Grammar.getProductionList().get(3);
        return (myRule.equals(rule2) || myRule.equals(rule3));
    }
    

    /**
     * Determines whether this Entry's rule is for repeat statements.
     * 
     * @return true if rule is S --> r i L d
     */
    public boolean hasRepeatRule ()
    {
        if (myRule == null)
            return false;
        return (myRule.equals(Grammar.getProductionList().get(10)));
    }
    
    /**
     * Convenience method for getting the left-hand side (lhs) of this
     * Entry's rule.
     * 
     * @return left-hand side (lhs) of rule
     */
    public String ruleLHS ()
    {
        return myRule.getLHS();
    }
    
    /**
     * Convenience method for getting the right-hand side (rhs) of this
     * Entry's rule.
     * 
     * @return right-hand side (lhs) of rule
     */
    public String ruleRHS ()
    {
        return myRule.getRHS();
    }
    
    /**
     * Checks whether the given action is an 'Error' action ("B")
     * 
     * @param action
     * @return true if 'Error' action
     */
    public static boolean isError (String action)
    {
        return (action.equals("B"));
    }
    
    /**
     * Checks whether the given action is a 'Shift' action ("s..")
     * 
     * @param action
     * @return true if 'Shift' action
     */
    public static boolean isShift (String action)
    {
        return (action.startsWith("s"));
    }
    
    /**
     * Checks whether the given string is a 'Reduce' action ("r..")
     * 
     * @param action
     * @return true if 'Reduce' action
     */
    public static boolean isReduce (String action)
    {
        return (action.startsWith("r"));
    }
    
    /**
     * Checks whether the given action is an 'Accept' action ("acc")
     * 
     * @param action
     * @return true if 'Accept' action
     */
    public static boolean isAccept (String action)
    {
        return (action.equals("acc"));
    }
    
    public String getState ()
    {
        return myState;
    }
    
    public String getAction ()
    {
        return myAction;
    }
    
    public Production getRule ()
    {
        return myRule;
    }

    /**
     * Determines what value to assign the Entry's state field. No value is stored
     * if the action is 'Error'.
     * 
     * @param action parsing instruction
     * @return state for transition
     */
    private String determineState (String action)
    {
        if (isError(action))
            return null;
        else if (action.substring(0, 1).matches("[sr]"))
            return action.substring(1);
        else
            return action;
    }
    
    /**
     * Determines what value to assign the Entry's rule field. No value is stored
     * unless the action is 'Reduce'.
     * 
     * @param action parsing instruction
     * @return state for transition
     */
    private Production determineRule (String action)
    {
        if (isReduce(action))
        {
            int ruleNumber = Integer.parseInt(action.substring(1));
            return Grammar.getProductionList().get(ruleNumber);
        }
        return null;
    }

}