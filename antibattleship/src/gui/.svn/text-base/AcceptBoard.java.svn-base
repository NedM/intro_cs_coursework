package gui;

import players.*;
import guiListeners.*;

import java.util.List;
import java.awt.*;
import javax.swing.*;

import info.clearthought.layout.TableLayout;


public class AcceptBoard extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public AcceptBoard ( GUIPlayer guiPlayer, int numRows, int numCols, List<Integer> shipSizeList, boolean isSalvo )
	{
		
		super("Antibattleship - Accept Board");
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setUndecorated(true);
        
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	150,
        	spacerSize,
        	150,
        	spacerSize}
        ,
        {spacerSize,
        	TableLayout.PREFERRED,
        	TableLayout.PREFERRED,
        	spacerSize / 2,
        	TableLayout.PREFERRED,
        	spacerSize / 2,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // instructions
        
        String instructions1 = "";
        if ( !isSalvo )
        	instructions1 = "Your opponent wants to play a normal game on";
        else
        	instructions1 = "Your opponent wants to play a salvo game on";
        String instructions2 = "a " + numRows + "x" + numCols + " board with the following ship sizes:";
        
        JLabel instructionsLabel1 = new JLabel(instructions1, SwingConstants.LEFT);
        JLabel instructionsLabel2 = new JLabel(instructions2, SwingConstants.LEFT);
        instructionsLabel1.setForeground(Color.WHITE);
        instructionsLabel2.setForeground(Color.WHITE);
        
        cp.add(instructionsLabel1, "1,1,3,1");
        cp.add(instructionsLabel2, "1,2,3,2");
        
        // ship sizes
        
        String shipSizes = "";
        
        for ( int index = 0 ; index < shipSizeList.size(); index++ )
        {
        	Integer size = shipSizeList.get(index);
        	if ( index == 0 )
        		shipSizes += size;
        	else
        		shipSizes += " " + size;
        }
        
        JLabel shipSizeLabel = new JLabel(shipSizes, SwingConstants.CENTER);
        shipSizeLabel.setForeground(Color.WHITE);
        
        cp.add(shipSizeLabel, "1,4,3,4");
        
        // accept?
        
        String instructions3 = "Do you accept?";
        
        JLabel instructionsLabel3 = new JLabel(instructions3, SwingConstants.LEFT);
        instructionsLabel3.setForeground(Color.WHITE);
        
        cp.add(instructionsLabel3, "1,6,3,6");
        
        // decline button
        
        JButton declineButton = new JButton("Decline");
        cp.add(declineButton, "1,8");
        
        // accept button
        
        JButton acceptButton = new JButton("Accept");
        cp.add(acceptButton, "3,8");
        
        // add listeners
        
        declineButton.addActionListener(new DeclineGame(this, guiPlayer));
        acceptButton.addActionListener(new AcceptGame(this, guiPlayer, numRows, numCols, shipSizeList));
        
        // spawn window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

