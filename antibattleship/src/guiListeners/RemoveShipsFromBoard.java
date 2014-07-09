package guiListeners;

import client.*;
import img.*;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * Action Listener removes ships from board
 * 
 * @author jboortz
 *
 */

public class RemoveShipsFromBoard implements ActionListener
{
	
	private List<ABSShip> myShips;
	private List<Integer> myShipLen;
	private JComboBox shipsCombo;
	private List<JLabel> myLabels;
	private int totCols;
	
	public RemoveShipsFromBoard ( JComboBox shipsCombo, List<Integer> myShipLen, List<ABSShip> myShips, List<JLabel> myLabels, int totCols )
	{
		this.shipsCombo = shipsCombo;
		this.myShipLen = myShipLen;
		this.myShips = myShips;
		this.myLabels = myLabels;
		this.totCols = totCols;
	}
	
	@Override
	public void actionPerformed ( ActionEvent e )
	{
		
		for ( int i = 0 ; i < myLabels.size() ; i++ )
		{
			
			// uncomment below for text labels
			
			//Integer colInt = i-(i/this.totCols)*this.totCols;
			//Integer rowInt = i/this.totCols;
			//String myText = rowInt.toString() + "," + colInt.toString();
			//myLabels.get(i).setText(myText);
			
			// put water icon
			
			myLabels.get(i).setIcon(ImageLibrary.getUnhitWaterIcon());
			
		}
		
		// put ships in myShips back into myShipLen
		
		for ( ABSShip ship : myShips )
			myShipLen.add(ship.getLength());
		
		myShips.clear();
		
		// update combo box
		
		shipsCombo.removeAllItems();
		for ( int i = 0 ; i < myShipLen.size() ; i++ )
		{
			String thisShip = "Ship of length " + myShipLen.get(i);
			shipsCombo.addItem(thisShip);
		}
		
	}
}
