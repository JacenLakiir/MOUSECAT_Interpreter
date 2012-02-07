package lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import tokens.Token;
import tokens.TokenFactory;

public class Lexer
{
    // single instance of lexer
    private static Lexer myLexer;

    // state of the lexer
    private static File myFile;
    private static Scanner myInput;
    private static Map<String, Token> mySymbolTable;
    private static final List<TokenFactory> typesOfTokens = TokenFactory.initialize();
    
    private Lexer () { }
    
    public static Lexer getInstance ()
    {
        if (myLexer == null)
        {
            myLexer = new Lexer();
            mySymbolTable = new TreeMap<String, Token>();
        }
        return myLexer;
    }
    
    public void initializeScanner ()
    {
        myFile = chooseFile();
        myInput = openFile();
    }
    
    public void runScanner ()
    {
        if (myInput == null)
            return;
        printSymbolTableHeader();
        int lineNumber = 0;
        String[] line;
        while (myInput.hasNextLine())
        {
            line = myInput.nextLine().split("\\s+");
            lineNumber++;
            for (String parseableString : line)
            {
                if (parseableString.length() == 0) continue;
                if (parseableString.startsWith("//")) break;
                try
                {
                    Token parsedToken = createToken(parseableString);
                    updateSymbolTable(parsedToken);
                    boolean enteredIntoTable = isSymbolTableUpdateNecessary(parsedToken);
                    printSymbolTableLine(parsedToken, enteredIntoTable);
                }
                catch (LexerException e)
                {
                    if (e.getType() == LexerException.Type.INVALID_TOKEN)
                        reportError(parseableString, lineNumber);
                }
            }
        }
        return;
    }

    public File chooseFile ()
    {
        JFileChooser fileChooser =  new JFileChooser(System.getProperties().getProperty("user.dir"));
        int option = fileChooser.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return fileChooser.getSelectedFile();
    }
    
    public Scanner openFile ()
    {
        if (myFile == null)
            return null;
        if (!isValidExtension())
            throw new LexerException("File extension is not valid (must end in \".mc\")",
                                       LexerException.Type.INVALID_FILE_EXTENSION);
        FileInputStream fstream;
        try
        {
            fstream = new FileInputStream(myFile);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        Scanner in = new Scanner(fstream);
        return in;
    }

    public Token createToken (String parseableString)
    {
        for (TokenFactory tokenType : typesOfTokens)
        {
            if (tokenType.isThisTypeOfToken(parseableString))
                return tokenType.createThisTypeOfToken(parseableString);
        }
        throw new LexerException("Invalid token: " + parseableString, LexerException.Type.INVALID_TOKEN);
    }
    
    private Token updateSymbolTable (Token parsedToken)
    {
        boolean needToUpdate = isSymbolTableUpdateNecessary(parsedToken);            
        if (needToUpdate)
        {
            if (!mySymbolTable.containsKey(parsedToken.getCharacterValue()))
                mySymbolTable.put(parsedToken.getCharacterValue(), parsedToken);
            return mySymbolTable.get(parsedToken.getCharacterValue());
        }
        return null;
    }
    
    private void printSymbolTableHeader ()
    {
        StringBuffer header = new StringBuffer();
        header.append(String.format("%-20s %-20s %-20s\n", "TYPE", "CH VALUE", "INT VALUE"));
        header.append(String.format("%-20s %-20s %-20s", "====", "========", "========="));
        System.out.println(header.toString());
    }
    
    private void printSymbolTableLine (Token parsedToken, boolean enteredIntoTable)
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
    
    private boolean isValidExtension()
    {
        return (myFile.getAbsolutePath().toLowerCase().endsWith(".mc"));
    }
    
    private boolean isSymbolTableUpdateNecessary (Token parsedToken)
    {
        Token.Type parsedTokenType = parsedToken.getTokenType();
        return (parsedTokenType == Token.Type.VARIABLE || parsedTokenType == Token.Type.INTEGER);
    }
    
    private void reportError (String parseableString, int lineNumber)
    {
        StringBuffer error = new StringBuffer();
        error.append("---------------------------------------------------\n");
        error.append("Line " + lineNumber + ": ");
        error.append("invalid token \"" + parseableString + "\"\n");
        error.append("---------------------------------------------------");
        System.err.println(error.toString());
    }
    
    public File getFile ()
    {
        return myFile;
    }
    
    
    public void setFile (String filePath)
    {
        myFile = new File(filePath);
    }
}