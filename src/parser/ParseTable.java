package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseTable
{

    private static final String PARSE_DATA = "resources/parsedata.txt";
    
    public enum Action
    {
        ACCEPT, SHIFT, REDUCE, BLANK
    }
    
    public ParseTable ()
    {
        
    }
    
    /**
     * Reads the parse data line-by-line in order to generate parse table entries.
     */
    public void buildParseTable ()
    {
        Scanner parseData = openParseData();
        if (parseData == null)  return;
        while (parseData.hasNextLine())
        {
            String[] line = parseData.nextLine().split("&");
        }
        return;
    }
    
    private Scanner openParseData ()
    {
        FileInputStream fstream;
        try
        {
            fstream = new FileInputStream(PARSE_DATA);
        }
        catch (FileNotFoundException e)
        {
            throw new ParserException("Parse data could not be opened",
                                     ParserException.Type.CANNOT_OPEN_FILE);
        }
        return new Scanner(fstream);
    }
}
