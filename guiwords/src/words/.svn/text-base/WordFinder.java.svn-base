package words;

import info.clearthought.layout.TableLayout;

//import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.*;

import words.WordFinder;
import words.WordList;

/**
 * WordFinder is an interface for searching a word list.
 * When the user types any part of a word, the interface
 * displays all the words that match.
 */
public class WordFinder extends JFrame 
{
    private static final long serialVersionUID = 1L;
    
    //Private members    
    private final WordList words = new WordList();
    private final JLabel findLabel;
    private final JTextField find;
    private final JScrollPane outScroll;
    private final JList outList;
    private final DefaultListModel dlm;
    private final JLabel matchCount;
    private final JButton searchButton;
    private final JButton clearButton;
    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenuItem openItem;
    private final JMenuItem exitItem;
    private final JLabel caseLabel;
    private final JCheckBox caseCheck;
    
    /**
     * Make a WordFinder window.
     */
    public WordFinder()
    {
        super("Word Finder");
        
        // call System.exit() when user closes the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container cp = this.getContentPane();
        this.findLabel = new JLabel("Find: ");
        this.find = new JTextField(20);               
        this.dlm = new DefaultListModel();
        this.outList = new JList(this.dlm);
        this.outScroll = new JScrollPane(this.outList); 
        this.matchCount = new JLabel("");
        this.searchButton = new JButton("Search");
        this.clearButton = new JButton("Clear");
        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.openItem = new JMenuItem("Open");
        this.exitItem = new JMenuItem("Exit");
        this.caseLabel = new JLabel("Case Sensitive Comparison:");
        this.caseCheck = new JCheckBox();
        
        /*
         * Task 2: set the layout manager of the content pane to a TableLayout.
         */
        double tableSize[][] = {{5, TableLayout.PREFERRED, 5, TableLayout.FILL, 5, TableLayout.PREFERRED, 5}, //columns
        					   {5, TableLayout.PREFERRED, 5, TableLayout.PREFERRED, TableLayout.FILL, TableLayout.PREFERRED}}; //rows
        cp.setLayout(new TableLayout(tableSize));
        
        
        /*
         * Task 1: add an action listener to `find' that outputs matching words
         *         to the console          
         */
        cp.add(findLabel, "1, 1");        
        cp.add(this.find, "3, 1");
        this.find.addKeyListener(new EnterKeyListener(this));       
        
        /*
         * Task 3: add a JList inside a JScrollPane that shows matching words
         */
        cp.add(this.outScroll, "3, 4");
        
        /*
         * Task 4: add a JLabel that shows the number of matching words
         */
        cp.add(this.matchCount, "3, 3");
        
        /*
         * Task 5: add a Search button
         */
        cp.add(this.searchButton, "5, 1");
        this.searchButton.addActionListener(new ButtonListener(this));
        
        /*
         * Add a clear button
         */
        cp.add(this.clearButton, "5, 3");
        this.clearButton.addActionListener(new ButtonListener(this));
        /*
         * Task 6: add a File menu with Open... and Exit options
         */
        this.fileMenu.add(this.openItem);
        this.openItem.addActionListener(new MenuListener(this));
        this.fileMenu.add(this.exitItem);
        this.exitItem.addActionListener(new MenuListener(this));
        this.menuBar.add(this.fileMenu);
        super.setJMenuBar(this.menuBar);
        
        //Add case sensitive check box
        cp.add(this.caseLabel, "3, 5");
        cp.add(this.caseCheck, "5, 5");
        
        this.pack();
        
        try 
        {
            InputStream in = new FileInputStream("words");
            loadWords(in);
        } 
        catch (IOException ioe) 
        {
        	System.out.println("Failed to load words!");
        }
    }
    
    public void doExit()
    {
    	super.dispose();
    }
    
    public void doOpen()
    {
    	JFileChooser fChooser = new JFileChooser();
    	InputStream inStream;
    	int returnVal = fChooser.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
    		try
    		{
    			inStream = new FileInputStream(fChooser.getSelectedFile());
    			this.loadWords(inStream);
//    			this.words.load(inStream);
    		}
    		catch(IOException ex)
    		{
    			System.out.println("File open error! Exception: " + ex.getMessage());
    		}
    	}
    }
    
    public void doClear()
    {
    	this.find.setText("");
    	this.matchCount.setText("");
    	this.dlm.clear();
    }
    
    public void doSearch()
    {
    	boolean caseSensitive = this.caseCheck.isSelected();
    	this.dlm.clear();
		String textToMatch = this.find.getText();
		List<String> matches = this.words.find(textToMatch, caseSensitive);
		for(String match : matches)
		{
			this.dlm.addElement(match);
//			System.out.println(match);
		}
		this.matchCount.setText(matches.size() + " matches found for \"" + textToMatch + "\"");
    }
    
    private void loadWords(InputStream in) throws IOException {
        words.load(in);
        // ...
    }
    
    /**
     * Main method.  Makes and displays a WordFinder window.
     * @param args Command-line arguments.  Ignored.
     */
    public static void main(String[] args) {
        // In general, Swing objects should only be accessed from
        // the event-handling thread -- not from the main thread
        // or other threads you create yourself.  SwingUtilities.invokeLater()
        // is a standard idiom for switching to the event-handling thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                // Make and display the WordFinder window.
                new WordFinder().setVisible(true);
            }
        });
    }    
}
