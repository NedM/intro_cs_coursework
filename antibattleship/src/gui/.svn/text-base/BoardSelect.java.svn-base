package gui;

import guiListeners.*;
import players.*;


import java.util.List;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import info.clearthought.layout.TableLayout;

/**
 * 
 * GUI to specify board specs
 * 
 * @author jboortz
 *
 */

public class BoardSelect extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	private final List<Integer> shipSizes = new ArrayList<Integer>();
	
	public BoardSelect ( GUIPlayer guiPlayer )
	{
		
		super("Antibattleship - Initiate Game");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double layoutSize[][] =
        {{spacerSize,
        	125,
        	spacerSize,
        	50,
        	2 * spacerSize,
        	100,
        	spacerSize,
        	100,
        	spacerSize}
        ,
        {spacerSize,
        	20,
        	20,
        	spacerSize,
        	20,
        	spacerSize,
        	20,
        	spacerSize,
        	20,
        	spacerSize,
        	20,
        	spacerSize,
        	20,
        	spacerSize,
        	20,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // instructions
        
        String instructions1 = "Enter board dimensions and ship sizes for the game you would like to play.";
        String instructions2 = "Click the Help button below to see a list of restrictions on these parameters.";
        
        JLabel instructionsLabel1 = new JLabel(instructions1, SwingConstants.LEFT);
        JLabel instructionsLabel2 = new JLabel(instructions2, SwingConstants.LEFT);
        instructionsLabel1.setForeground(Color.WHITE);
        instructionsLabel2.setForeground(Color.WHITE);
        cp.add(instructionsLabel1, "1,1,7,1");
        cp.add(instructionsLabel2, "1,2,7,2");
        
        // ship length label
        
        JLabel shipSizeLabel = new JLabel("Ship Length:", SwingConstants.RIGHT);
        shipSizeLabel.setForeground(Color.WHITE);
        cp.add(shipSizeLabel, "5,4");
        
        // ship length field
        
        JTextField shipSizeField = new JTextField();
        cp.add(shipSizeField, "7,4");
        
        // add ship button
        
        JButton addShipButton = new JButton("Add Ship");
        cp.add(addShipButton, "5,6");
        
        // clear ships button
        
        JButton clearShipsButton = new JButton("Clear Ships");
        cp.add(clearShipsButton, "7,6");
        
        // ship size list
        
        DefaultListModel shipSizeListModel = new DefaultListModel();
        JList shipSizeList = new JList(shipSizeListModel);
		JScrollPane shipSizeScrollPane = new JScrollPane(shipSizeList);
		cp.add(shipSizeScrollPane, "5,8,7,10");
        
        // numRows label
        
        JLabel numRowsLabel = new JLabel("Number of rows:", SwingConstants.RIGHT);
        numRowsLabel.setForeground(Color.WHITE);
        cp.add(numRowsLabel, "1,6");
        
        // numRows field
        
        JTextField numRowsField = new JTextField();
        cp.add(numRowsField, "3,6");
        
        // numCols label
        
        JLabel numColsLabel = new JLabel("Number of columns:", SwingConstants.RIGHT);
        numColsLabel.setForeground(Color.WHITE);
        cp.add(numColsLabel, "1,8");
        
        // numCols field
        
        JTextField numColsField = new JTextField();
        cp.add(numColsField, "3,8");
        
        // salvo label
        
        JLabel salvoLabel = new JLabel("Salvo Variant?", SwingConstants.RIGHT);
        salvoLabel.setForeground(Color.WHITE);
        cp.add(salvoLabel, "1,10");
        
        // salvo check box
        
        JCheckBox salvoCheckBox = new JCheckBox();
        salvoCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        salvoCheckBox.setBackground(Color.BLACK);
        cp.add(salvoCheckBox, "3,10");
        
        // help button
        
        JButton helpButton = new JButton("Help");
        cp.add(helpButton, "5,14");
        
        // submit button
        
        JButton submitButton = new JButton("Submit");
        cp.add(submitButton, "7,14");
        
        // add listeners
        
        addShipButton.addActionListener(new AddShip(this, shipSizeField, shipSizeListModel, shipSizes));
        clearShipsButton.addActionListener(new ClearShips(shipSizeListModel, shipSizes));
        // TODO helpButton.addActionListener(new SpawnHelp());
        submitButton.addActionListener(new SubmitBoard(this, guiPlayer, shipSizes, numRowsField, numColsField, salvoCheckBox));
        
        // spawn window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

