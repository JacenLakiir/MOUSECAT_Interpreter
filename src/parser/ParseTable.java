package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Defines a ParseTable object that stores Entries accessible by row-column
 * indexing. A row holds all of the Entries for a particular parsing state.
 * A column holds all of the Entries for a particular grammar symbol.
 * 
 * @author Eric Mercer (ewm10)
 */
public class ParseTable
{
    private static final String PARSE_DATA = "resources/parsedata.txt";
    private static final int NUM_OF_STATES = 38;
    private static final int NUM_OF_TERMINALS = 18;
    private static final int NUM_OF_VARIABLES = 4;
    
    private static Scanner myParseData;
    private static List<Map<String, Entry>> myTable;
    private static List<String> myTerminals;
    private static List<String> myVariables;
    
    /**
     * Constructs a new ParseTable.
     */
    public ParseTable ()
    {
        myParseData = openParseData();
        myTable = buildParseTable();
    }
    
    /**
     * Convenience method for getting the Entry stored at [state, symbol].
     * 
     * @return Entry stored at [state, symbol]
     */
    public Entry lookupEntry (String state, String symbol)
    {
        return myTable.get(Integer.parseInt(state)).get(symbol);
    }
    
    /**
     * Opens the contents of the parsedata file as a scannable input
     * stream. An exception is thrown if the file cannot be opened.
     * 
     * @throws ParserException.Type.CannotOpenFile if the parsedata file cannot
     *              be opened
     * @return scannable input stream for parsedata file
     */
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
    
    /**
     * Reads the parse data line-by-line in order to generate parse table entries.
     * 
     * @return parse table
     */
    private List<Map<String, Entry>> buildParseTable ()
    {
        List<Map<String, Entry>> table = new ArrayList<Map<String, Entry>>();
        
        String header = myParseData.nextLine();
        myTerminals = parseHeader(header, NUM_OF_TERMINALS);
        for (int i = 0; i < NUM_OF_STATES; i++)
        {
            Map<String, Entry> symbolMap = parseLine(true);
            table.add(symbolMap);
        }
        
        header = myParseData.nextLine();
        myVariables = parseHeader(header, NUM_OF_VARIABLES);
        for (int i = 0; i < NUM_OF_STATES; i++)
        {
            Map<String, Entry> symbolMap = table.remove(i);
            Map<String, Entry> variableMap = parseLine(false);
            symbolMap.putAll(variableMap);
            table.add(i, symbolMap);
        }

        return table;
    }
    
    /**
     * Reads in the header row of one of the two subtables of the parsedata
     * file. The number of symbols in the header is dependent on the subtable
     * (terminal or variable).
     * 
     * @param header line of symbols in subtable
     * @param numberOfSymbols number of columns in subtable
     * @return list of symbols
     */
    private List<String> parseHeader (String header, int numberOfSymbols)
    {
        List<String> headerList = new ArrayList<String>();
        for (int i = 0; i < numberOfSymbols; i++)
        {
            String symbol = header.substring(1 + 2*i, 2 + 2*i);
            headerList.add(symbol);
        }
        return headerList;
    }
    
    /**
     * Reads in a non-header row of one of the two subtables of the parsedata
     * file. Each column's symbol is mapped to an Entry generated by the action
     * listed in that column.
     * 
     * @param isTerminalRow true if line from terminal subtable
     * @return mapping of symbols to Entries
     */
    private Map<String, Entry> parseLine (boolean isTerminalRow)
    {
        String rawLine = myParseData.nextLine();
        String state = rawLine.substring(0, rawLine.indexOf("&"));
        String entries = rawLine.substring(state.length());

        RowIterator parseIter = new RowIterator(entries);
        Map<String, Entry> symbolMap = new TreeMap<String, Entry>();
        int column = 0;
        while (parseIter.hasNext())
        {
            String action = parseIter.next();
            String currentSymbol = isTerminalRow ? myTerminals.get(column) : myVariables.get(column);
            symbolMap.put(currentSymbol, new Entry(state, action));
            column++;
        }
        return symbolMap;
    }
    
}
