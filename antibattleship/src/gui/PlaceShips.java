package gui;

import img.*;
import guiListeners.*;
import players.*;
import client.*;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import info.clearthought.layout.TableLayout;

/**
 * 
 * GUI for placing ships on board
 * 
 * @author jboortz
 *
 */
public class PlaceShips extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private final List<ABSShip> shipList = new ArrayList<ABSShip>();
	private Container cp;
	
	public PlaceShips ( JFrame boardSelect, GUIPlayer guiPlayer, int numRows, int numCols, List<Integer> shipSizeList )
	{
		
		super("Antibattleship - Place Ships");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        // get top left of board
        
        int boardStartRow = 16;
        int boardStartCol = 1;
        
        // set column sizes
        
        double[] colSizes = new double [ numCols + boardStartCol + 1 ];
        
        colSizes[0] = spacerSize;
        
        for ( int col = boardStartCol ; col <= numCols + boardStartCol - 1; col++ )
        	colSizes[col] = ImageLibrary.BOARD_ICON_SIZE;
        
        colSizes[numCols + boardStartCol] = spacerSize;
        
        // set row sizes
        
        double[] rowSizes = new double [ numRows + boardStartRow + 5 ];
        
        rowSizes[0] = spacerSize;
        rowSizes[1] = 20;
        rowSizes[2] = 20;
        rowSizes[3] = spacerSize;
        rowSizes[4] = 50;
        rowSizes[5] = spacerSize;
        rowSizes[6] = 30;
        rowSizes[7] = 30;
        rowSizes[8] = spacerSize;
        rowSizes[9] = 20;
        rowSizes[10] = 30;
        rowSizes[11] = 30;
        rowSizes[12] = spacerSize;
        rowSizes[13] = 25;
        rowSizes[14] = 25;
        rowSizes[15] = spacerSize;
        
        for ( int row = boardStartRow ; row <= numRows + boardStartRow - 1; row++ )
        	rowSizes[row] = ImageLibrary.BOARD_ICON_SIZE;
        
        rowSizes[numRows + boardStartRow] = spacerSize;
        rowSizes[numRows + boardStartRow + 1] = 25;
        rowSizes[numRows + boardStartRow + 2] = spacerSize / 2;
        rowSizes[numRows + boardStartRow + 3] = 25;
        rowSizes[numRows + boardStartRow + 4] = spacerSize;
        
        // set layout size
        
        double[][] layoutSize = {colSizes, rowSizes};
        
//        cp.setLayout(new TableLayout(layoutSize));
        cp.setLayout(new GridLayout());
        
        //Create Scroll Pane
        
        JPanel pane = new JPanel(new TableLayout(layoutSize));
        pane.setBackground(Color.BLACK);
                
        // instructions
        
        String instructions1 = "Place your ships on the board.";
        String instructions2 = "Click the Help button below to see a list of restrictions on ship placement.";
        
        JLabel instructionsLabel1 = new JLabel(instructions1);
        JLabel instructionsLabel2 = new JLabel(instructions2);
        instructionsLabel1.setForeground(Color.WHITE);
        instructionsLabel2.setForeground(Color.WHITE);
        pane.add(instructionsLabel1, "1,1," + (numCols + boardStartCol) + ",1");
        pane.add(instructionsLabel2, "1,2," + (numCols + boardStartCol) + ",2");
        
        // combo box
        
		JComboBox shipSizeComboBox = new JComboBox();
		for ( int i = 0 ; i < shipSizeList.size() ; i++ )
		{
			String thisShip = "Ship of length " + shipSizeList.get(i);
			shipSizeComboBox.addItem(thisShip);
		}
		pane.add(shipSizeComboBox, "1,4," + (numCols + boardStartCol - 1) + ",4");
        
        // shipRow field
        
        JTextField shipRowField = new JTextField();
        pane.add(shipRowField, "1,6");
        
        // shipRow label
        
        JLabel shipRowLabel = new JLabel("   Row of the top-left square of the ship.");
        shipRowLabel.setForeground(Color.WHITE);
        pane.add(shipRowLabel, "2,6," + (numCols + boardStartCol - 1) + ",6");
		
		// shipCol field
		
		JTextField shipColField = new JTextField();
		pane.add(shipColField, "1,7");
		
		// shipCol label
		
		JLabel shipColLabel = new JLabel("   Column of the top-left square of the ship.");
		shipColLabel.setForeground(Color.WHITE);
		pane.add(shipColLabel, "2,7," + (numCols + boardStartCol - 1) + ",7");
		
		// orientation label
		
		JLabel orientationLabel = new JLabel("Select the orientation of the ship.");
		orientationLabel.setForeground(Color.WHITE);
		pane.add(orientationLabel, "1,9," + (numCols + boardStartCol - 1) + ",9");
		
		// horizontal radio
		
		JRadioButton horizontalButton = new JRadioButton("Horizontal");
		horizontalButton.setBackground(Color.BLACK);
		horizontalButton.setForeground(Color.WHITE);
		pane.add(horizontalButton, "1,10," + (numCols + boardStartCol - 1) + ",10");
		
		// vertical radio
		
		JRadioButton verticalButton = new JRadioButton("Vertical");
		verticalButton.setBackground(Color.BLACK);
		verticalButton.setForeground(Color.WHITE);
		pane.add(verticalButton, "1,11," + (numCols + boardStartCol - 1) + ",11");
		
		// group radio buttons
		
		ButtonGroup orientationGroup = new ButtonGroup();
		orientationGroup.add(horizontalButton);
		orientationGroup.add(verticalButton);
		
		// add ship button
		
		JButton addShipButton = new JButton("Add Ship");
		pane.add(addShipButton, "1,13," + (numCols + boardStartCol - 1) + ",13");
		
		// reset ships button
		
		JButton resetShipsButton = new JButton("Reset Ships");
		pane.add(resetShipsButton, "1,14," + (numCols + boardStartCol - 1) + ",14");
		
		// board labels, added top to bottom, left to right
		
		List<JLabel> shipLabels = new ArrayList<JLabel>();
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		for ( int row = 0 ; row < numRows ; row++ )
		{
			for ( int col = 0 ; col < numCols ; col++ )
			{
				String labelLoc = (boardStartCol + col) + "," + (boardStartRow + row);
				JLabel boardLabel = new JLabel(ImageLibrary.getUnhitWaterIcon());
				boardLabel.setForeground(Color.WHITE);
				boardLabel.setBorder(border);
				shipLabels.add(boardLabel);
				pane.add(boardLabel, labelLoc);
			}
		}
		
		// help button
		
		JButton helpButton = new JButton("Help");
		pane.add(helpButton, "1," + (numRows + boardStartRow + 1) + "," + (numCols + boardStartCol - 1) + "," + (numRows + boardStartRow + 1));
		
		// submit button
		
		JButton submitButton = new JButton("Submit");
		pane.add(submitButton, "1," + (numRows + boardStartRow + 3) + "," + (numCols + boardStartCol - 1) + "," + (numRows + boardStartRow + 3));
		
        // add listeners
        
		addShipButton.addActionListener(new PlaceShipsOnBoard(this, shipRowField, shipColField, shipSizeComboBox, shipSizeList, horizontalButton, verticalButton, shipList, shipLabels, numRows, numCols));
		resetShipsButton.addActionListener(new RemoveShipsFromBoard(shipSizeComboBox, shipSizeList, shipList, shipLabels, numCols));
		// TODO add help button listener
		submitButton.addActionListener(new SubmitShips(this, guiPlayer, shipSizeList, shipList, shipLabels, numRows, numCols));
        
        // spawn window
        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cp.add(scrollPane, "0,0");
        super.pack();
        super.setVisible(true);
//        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

