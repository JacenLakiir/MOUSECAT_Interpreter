package lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import tokens.Token;
import tokens.TokenFactory;

/**
 * Scans a MOUSECAT input file in order to identify all of its tokens.
 * 
 * @author Eric Mercer (ewm10)
 */
public class Lexer
{
    // single instance of lexer
    private static Lexer myLexer;

    // state of the lexer
    private final List<TokenFactory> typesOfTokens = TokenFactory.initialize();
    
    private File myFile;
    private Scanner myInput;
    private Queue<String> mySymbolQueue;
    private Map<String, Token> mySymbolTable;
        
    /**
     * Private constructor used for restricting instantiation of Lexer objects.
     * (Singleton pattern)
     */
    private Lexer ()
    {
        mySymbolTable = new TreeMap<String, Token>();
        mySymbolQueue = new LinkedList<String>();
    }

    /**
     * Limits access to this class's constructor so that only one Lexer is ever
     * instantiated during the lifetime of the program. This ensures one point
     * of access to the information stored in Lexer's instance variables across
     * the interpreter without conflicts arising from the instantiation of other
     * Lexers. (Singleton pattern)
     * 
     * @return new Lexer if never instantiated or reference to existing Lexer
     */
    public static Lexer getInstance ()
    {
        if (myLexer == null)
        {
            myLexer = new Lexer();
        }
        return myLexer;
    }

    /**
     * Prompts the user to choose a MOUSECAT file and opens it.
     */
    public void initializeScanner ()
    {
        myFile = chooseFile();
        myInput = openFile();
    }

    /**
     * Displays a file dialog box prompting the user to choose a file. All files
     * without a .mc (case-insensitive) extension are filtered out.
     * 
     * @return MOUSECAT (.mc) file
     */
    public File chooseFile ()
    {
        FileFilter mouseCatFilter =
                new FileNameExtensionFilter("MOUSECAT program", "mc", "MC", "mC", "Mc");
        JFileChooser fileChooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
        fileChooser.setFileFilter(mouseCatFilter);
        int option = fileChooser.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION)
            return null;
        return fileChooser.getSelectedFile();
    }

    /**
     * Opens the contents of the selected MOUSECAT file as a scannable input
     * stream. An exception is thrown if the file does not have a valid
     * case-insensitive extension (.mc) or cannot be opened.
     * 
     * @throws LexerException.Type.InvalidFileExtension if the selected file
     *             does not have a valid case-insensitive extension (.mc)
     * @throws LexerException.Type.CannotOpenFile if the selected file cannot be
     *             opened
     * @return scannable input stream for selected file
     */
    public Scanner openFile ()
    {
        if (!wasFileChosen()) return null;
        if (!hasValidExtension())
            throw new LexerException("File extension is not valid (must end in \".mc\")",
                                     LexerException.Type.INVALID_FILE_EXTENSION);
        FileInputStream fstream;
        try
        {
            fstream = new FileInputStream(myFile);
        }
        catch (FileNotFoundException e)
        {
            throw new LexerException("File could not be opened",
                                     LexerException.Type.CANNOT_OPEN_FILE);
        }
        return new Scanner(fstream);
    }
    
    /**
     * Scans the input stream line-by-line for tokens while printing output for
     * all tokens parsed.
     */
    public void runScanner ()
    {
        if (!wasFileChosen()) return;
//        LexerOutput.printHeader();
        int lineNumber = 0;
        while (myInput.hasNextLine())
        {
            lineNumber++;
            String[] line = myInput.nextLine().split("\\s+");
            parseLine(lineNumber, line);
        }
        mySymbolQueue.add("$");
        return;
    }
    
    /**
     * Checks the given string against all defined types of tokens. If a match
     * is encountered, a token is created for that string and returned. An
     * exception is thrown if the given string does not match any defined token
     * types.
     * 
     * @param parseableString
     * @throws LexerException.Type.INVALID_TOKEN if the word does not match any
     *             defined type of token
     * @return token created from parseableString
     */
    public Token createToken (String parseableString)
    {
        for (TokenFactory tokenType : typesOfTokens)
        {
            if (tokenType.isThisTypeOfToken(parseableString))
                return tokenType.createThisTypeOfToken(parseableString);
        }
        throw new LexerException("Invalid token: " + parseableString,
                                 LexerException.Type.INVALID_TOKEN);
    }    
    
    /**
     * Checks whether the user selected a file.
     * 
     * @return true if file selected
     */
    public boolean wasFileChosen ()
    {
        return (myFile != null);
    }
    
    /**
     * Convenience method for retrieving the next symbol in the queue.
     * 
     * @return next symbol
     */
    public String getNextSymbol ()
    {
        return mySymbolQueue.poll();
    }
    
    public File getFile ()
    {
        return myFile;
    }

    public void setFile (String filePath)
    {
        myFile = new File(filePath);
    }
    
    /**
     * Scans a line word-by-word for tokens. Empty strings are skipped. If a
     * comment is detected, scanning ends for that line. Any non-empty,
     * non-comment string is identified as a token of the appropriate type and
     * stored in the symbol table. If the string is an invalid token, an error
     * report is logged and scanning continues.
     * 
     * @param lineNumber current line in file
     * @param line array of words to be scanned
     */
    private void parseLine (int lineNumber, String[] line)
    {
        for (String parseableString : line)
        {
            if (isEmptyString(parseableString)) continue;
            if (isComment(parseableString)) break;
            try
            {
                identifyAndStoreToken(parseableString);
            }
            catch (LexerException e)
            {
                if (e.getType() == LexerException.Type.INVALID_TOKEN)
                    LexerOutput.reportError(parseableString, lineNumber);
            }
        }
    }

    /**
     * Creates a token of the appropriate type for the given string and updates
     * the symbol table. Adds the token's symbol to the queue of symbol scanned
     * in so far. Output is printed showing the results of the token
     * identification and storage.
     * 
     * @param parseableString
     */
    private void identifyAndStoreToken (String parseableString)
    {
        Token parsedToken = createToken(parseableString);
        updateSymbolTable(parsedToken);
        
        String tokenSymbol = parsedToken.getSymbol();
        mySymbolQueue.add(tokenSymbol);
//        boolean isEnteredIntoTable = isUpdateNecessary(parsedToken);
//        LexerOutput.printTokenOutput(parsedToken, isEnteredIntoTable);
    }

    /**
     * If an update is necessary for this type of token and the symbol table
     * does not contain a mapping for this token, it creates an entry mapping
     * the given token's character value to its representation as a Token
     * object. Returns a reference to the token.
     * 
     * @param parsedToken token to be stored/accessed
     * @return reference to the token stored/accessed
     */
    private Token updateSymbolTable (Token parsedToken)
    {
        if (!isUpdateNecessary(parsedToken)) return null;
        String charVal = parsedToken.getCharacterValue();
        if (!mySymbolTable.containsKey(charVal))
            mySymbolTable.put(charVal, parsedToken);
        return mySymbolTable.get(charVal);
    }

    /**
     * Checks whether myFile has a case-insensitive file extension ending in ".mc".
     * 
     * @return true if myFile has MOUSECAT file extension
     */
    private boolean hasValidExtension ()
    {
        return (myFile.getAbsolutePath().toLowerCase().endsWith(".mc"));
    }
    
    /**
     * Checks whether the given string denotes the beginning of a comment.
     * 
     * @param parseableString
     * @return true if beginning of a comment
     */
    private boolean isComment (String parseableString)
    {
        return (parseableString.startsWith("//"));
    }

    /**
     * Checks whether the given string is empty (has length 0).
     * 
     * @param parseableString
     * @return true if given string is empty
     */
    private boolean isEmptyString (String parseableString)
    {
        return (parseableString.length() == 0);
    }

    /**
     * Checks whether the given token is a VariableToken or an IntegerToken,
     * thereby determining if symbol table access is necessary.
     * 
     * @param parsedToken
     * @return true if symbol table needs to be updated
     */
    private boolean isUpdateNecessary (Token parsedToken)
    {
        Token.Type parsedTokenType = parsedToken.getTokenType();
        return (parsedTokenType == Token.Type.VARIABLE || parsedTokenType == Token.Type.INTEGER);
    }

}
