package lexer;

import java.util.Scanner;

public class InputScanner
{
    // single instance of input
    private static InputScanner myInputReader;
    
    // state of the input
    private static Scanner myInput;
    private static int myLineNumber;
    private static String myLine;
    private static int myCurrentPosition;
    
    private InputScanner (Scanner reader)
    {
        myInput = reader;
        myLineNumber = 1;
        myLine = null;
        myCurrentPosition = 0;
    }
    
    public static InputScanner getInstance (Scanner reader)
    {
        if (myInput == null)
            myInputReader = new InputScanner(reader);
        return myInputReader;
    }
    
    private void skipWhiteSpace ()
    {
        while (notAtEndOfLine() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }

    private char currentCharacter ()
    {
        if (notAtEndOfLine())
            return myLine.charAt(myCurrentPosition);
        else throw new LexerException("At end of line. Cannot get current character.");
    }

    public String getLine ()
    {
        return myLine;
    }

    public int getCurrentPosition ()
    {
        return myCurrentPosition;
    }

    private void advanceReader (int newPosition)
    {
        if (newPosition >= 0)
            myCurrentPosition = newPosition;
    }

    private void incrementCurrentPosition ()
    {
        myCurrentPosition++;
    }

    private boolean notAtEndOfLine ()
    {
        return (myCurrentPosition < myLine.length());
    }
    
    private boolean notAtEndOfFile ()
    {
        return (myInput.hasNextLine());
    }
}
