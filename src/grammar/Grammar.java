package grammar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import parser.ParserException;

/**
 * Stores the rules/productions of the MouseCat language.
 * 
 * @author Eric Mercer (ewm10)
 */
public class Grammar
{
    private static final String GRAMMAR_FILE = "resources/grammar.txt";
    private static final Scanner GRAMMAR = openGrammar();
    private static final List<Production> PRODUCTION_LIST = initializeProductionList();
    
    /**
     * Builds string representation of the grammar.
     * 
     * @return string representation
     */
    public static String asString ()
    {
        StringBuilder grammar = new StringBuilder();
        for (Production p : PRODUCTION_LIST)
            grammar.append(p + "\n");
        return grammar.toString().trim();
    }
    
    public static List<Production> getProductionList ()
    {
        return Collections.unmodifiableList(PRODUCTION_LIST);
    }
    
    /**
     * Opens the contents of the grammar file as a scannable input stream. An
     * exception is thrown if the file cannot be opened.
     * 
     * @throws ParserException.Type.CannotOpenFile if the grammar file cannot
     *              be opened
     * @return scannable input stream for grammar file
     */
    private static Scanner openGrammar ()
    {
        FileInputStream fstream;
        try
        {
            fstream = new FileInputStream(GRAMMAR_FILE);
        }
        catch (FileNotFoundException e)
        {
            throw new ParserException("Grammar could not be opened",
                                     ParserException.Type.CANNOT_OPEN_FILE);
        }
        return new Scanner(fstream);
    }
    
    /**
     * Initializes a list of Productions from the grammar.
     * 
     * @return list of Productions
     */
    private static List<Production> initializeProductionList ()
    {
        List<Production> productionList = new ArrayList<Production>();
        while (GRAMMAR.hasNextLine())
        {
            String[] production = GRAMMAR.nextLine().split("\\s+");
            
            String ruleNumber = production[0];
            String lhs = production[1];
            StringBuilder rhs = new StringBuilder();
            for (int i = 2; i < production.length; i++)
                rhs.append(production[i] + " ");
            
            productionList.add(new Production(ruleNumber, lhs, rhs.toString().trim()));
        }
        return productionList;
    }

}
