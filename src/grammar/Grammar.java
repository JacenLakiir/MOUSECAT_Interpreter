package grammar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import parser.ParserException;

public class Grammar
{
    
    private static final String GRAMMAR = "src/resources/grammar.txt";
    
    private static List<Production> myProductionList;
    
    public Grammar ()
    {
        initializeProductionList ();
    }
    
    public List<Production> getProductionList ()
    {
        return Collections.unmodifiableList(myProductionList);
    }
    
    private void initializeProductionList ()
    {
        myProductionList = new ArrayList<Production>();
        Scanner grammar = openGrammar();
        while (grammar.hasNextLine())
        {
            String[] production = grammar.nextLine().split("\\s+");
            
            String ruleNumber = production[0];
            String lhs = production[1];
            StringBuilder rhs = new StringBuilder();
            for (int i = 2; i < production.length; i++)
                rhs.append(production[i] + " ");
            
            myProductionList.add(new Production(ruleNumber, lhs, rhs.toString().trim()));
        }
    }
    
    private Scanner openGrammar ()
    {
        FileInputStream fstream;
        try
        {
            fstream = new FileInputStream(GRAMMAR);
        }
        catch (FileNotFoundException e)
        {
            throw new ParserException("Grammar could not be opened",
                                     ParserException.Type.CANNOT_OPEN_FILE);
        }
        return new Scanner(fstream);
    }
}
