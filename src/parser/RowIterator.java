package parser;

import java.util.Iterator;

/**
 * Defines a RowIterator used for iterating over a row of entries in a
 * parse table file written in LaTeX.
 * 
 * @author Eric Mercer (ewm10)
 */
public class RowIterator implements Iterator<String>
{
    
    private String myLine;
    private String myUnparsedLine;
    private int myPosition;
    
    /**
     * Constructs a new RowIterator. The given string is reformatted for easier iteration.
     * 
     * @param rawLine LaTeX-formatted row of entries 
     */
    public RowIterator (String rawLine)
    {
        String cleanLine = reformatLine(rawLine);
        
        myLine = cleanLine;
        myUnparsedLine = cleanLine;
        myPosition = 0;
    }

    @Override
    public boolean hasNext ()
    {
        return (myPosition < myLine.length());
    }

    @Override
    public String next ()
    {
        int index = myUnparsedLine.substring(1).indexOf("&");
        if (index == -1)
            index = myUnparsedLine.length()-1;
        
        String nextCell = myUnparsedLine.substring(1, index+1);
        myPosition += index + 1;
        myUnparsedLine = myUnparsedLine.substring(index+1);
        
        return nextCell;
    }

    @Override
    public void remove ()
    {
        // not implemented
    }
    
    /**
     * Reformats the given string to simplify iteration.
     * 
     * @param rawLine LaTeX-formatted row of entries 
     * @return reformatted line for iteration
     */
    private String reformatLine (String rawLine)
    {
        StringBuilder cleanLine = new StringBuilder();
        
        for (int i = 0; i < rawLine.length()-1; i++)
        {
            if (rawLine.substring(i, i+2).equals("&&"))
               cleanLine.append("&B");
            else
                cleanLine.append(rawLine.charAt(i));
        }
        
        cleanLine.append(rawLine.charAt(rawLine.length()-1));
        if (rawLine.endsWith("&"))
            cleanLine.append("B");
        
        return cleanLine.toString();
    }

}
