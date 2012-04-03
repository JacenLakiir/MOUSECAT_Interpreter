package lexer;

import tokens.Token;

/**
 * Various utility functions for printing lexer output.
 * 
 * @author Eric Mercer (ewm10)
 */
public class LexerOutput
{
    
    /**
     * Prints the output header as a table with three columns: Type,
     * CharacterValue, and IntegerValue.
     */
    public static void printHeader ()
    {
        StringBuffer header = new StringBuffer();
        header.append(String.format("%-20s %-20s %-20s\n", "TYPE", "CH VALUE", "INT VALUE"));
        header.append(String.format("%-20s %-20s %-20s", "====", "========", "========="));
        System.out.println(header.toString());
    }

    /**
     * Prints a line of output with the given token's type and, if the symbol
     * table was updated for this token, its character and integer values
     * formatted into three columns.
     * 
     * @param parsedToken
     * @param enteredIntoTable true if the symbol table was accessed for the given token
     */
    public static void printTokenOutput (Token parsedToken, boolean enteredIntoTable)
    {
        StringBuffer line = new StringBuffer();
        line.append(String.format("%-20s ", parsedToken.getType()));
        if (enteredIntoTable)
        {
            line.append(String.format("%-20s ", parsedToken.getCharacterValue()));
            line.append(String.format("%-20s", parsedToken.getIntegerValue()));
        }
        System.out.println(line.toString());
    }
    
    /**
     * Prints output reporting that an invalid token has been found and where in
     * the file it is located.
     * 
     * @param parseableString word that triggered the error
     * @param lineNumber line in file in which the error occurred
     */
    public static void reportError (String parseableString, int lineNumber)
    {
        StringBuffer error = new StringBuffer();
        error.append("---------------------------------------------------\n");
        error.append("Line " + lineNumber + ": ");
        error.append("invalid token \"" + parseableString + "\"\n");
        error.append("---------------------------------------------------");
        System.err.println(error.toString());
    }
}
