package lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import tokens.Token;
import tokens.TokenFactory;

public class Lexer
{
    // single instance of lexer
    private static Lexer myLexer;
    
    // state of the lexer
    private static File myFile;
    private static InputScanner myInputScanner;
    private static final List<TokenFactory> typesOfTokens = TokenFactory.initialize();
    
    private Lexer () { }
    
    public static Lexer getInstance ()
    {
        if (myLexer == null)
            myLexer = new Lexer();
        return myLexer;
    }
    
    public void initializeScanner ()
    {
        myFile = chooseFile();
        myInputScanner = InputScanner.getInstance(openFile());
    }
    
    public void runScanner ()
    {
        /*
         * pseudo-code for scanner
         * 
         * find next token
         * if can be inserted
         *      search if already in table
         *      if not, insert it
         * return reference to token's location in table and token type
         * print this reference
         */
        return;
    }
    
    public Token createToken (String parseableString)
    {
        for (TokenFactory tokenType : typesOfTokens)
        {
            if (tokenType.isThisTypeOfToken(parseableString))
                return tokenType.createThisTypeOfToken(parseableString);
        }
        throw new LexerException("Invalid token", LexerException.Type.INVALID_TOKEN);
    }
    
    public File chooseFile ()
    {
        JFileChooser fileChooser =  new JFileChooser(System.getProperties().getProperty("user.dir"));
        int retval = fileChooser.showOpenDialog(null);
        if (retval != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return fileChooser.getSelectedFile();
    }
    
    
    public java.util.Scanner openFile ()
    {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        Scanner in = new Scanner(fstream);
        return in;
    }
    
    
    private boolean isValidExtension()
    {
        return (myFile.getAbsolutePath().toLowerCase().endsWith(".mc"));
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