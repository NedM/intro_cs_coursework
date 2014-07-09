package guiListeners;

import client.*;
import img.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * 
 * Action Listener places ships on board
 * 
 * @author jboortz
 *
 */
public class PlaceShipsOnBoard implements ActionListener
{

	private JFrame myFrame;
	private JTextField row;
	private JTextField col;
	private JRadioButton horz;
	private JRadioButton ver;
	private List<ABSShip> myShips;
	private List<Integer> myShipLen;
	private JComboBox shipsCombo;
	private int rowInt;
	private int colInt;
	private List<JLabel> myLabels;
	private int totRows;
	private int totCols;
	
	public PlaceShipsOnBoard ( JFrame myFrame, JTextField row, JTextField col, JComboBox shipsCombo, List<Integer> myShipLen, JRadioButton horz, JRadioButton ver, List<ABSShip> myShips, List<JLabel> myLabels, int totRows, int totCols )
	{
		this.myFrame = myFrame;
		this.row = row;
		this.col = col;
		this.shipsCombo = shipsCombo;
		this.myShipLen = myShipLen;
		this.horz = horz;
		this.ver = ver;
		this.myShips = myShips;
		this.myLabels = myLabels;
		this.totRows = totRows;
		this.totCols = totCols;
	}
	
	@Override
	public void actionPerformed ( ActionEvent e )
	{
		
		// make sure there is a ship selected
		
		if ( shipsCombo.getSelectedIndex() < 0 )
		{
			JOptionPane.showMessageDialog(this.myFrame, "Please select a ship to place on the board.");
			return;
		}
		
		// check if row is valid
		
		try
		{
			this.rowInt = Integer.parseInt(this.row.getText());
			if ( rowInt < 0 || ( this.ver.isSelected() && rowInt + this.myShipLen.get(shipsCombo.getSelectedIndex()) > totRows ) )
			{
				JOptionPane.showMessageDialog(this.myFrame, "The ship must lie entirely in the board, please enter a valid location.");
				this.row.setText("");
				this.col.setText("");
				return;
			}
		}
		catch ( NumberFormatException exp )
		{
			JOptionPane.showMessageDialog(this.myFrame, "The number of rows you entered is not valid, please enter an integer.");
			this.row.setText("");
			return;
		}
		
		// check if col is valid
		
		try
		{
			this.colInt = Integer.parseInt(this.col.getText());
			if ( colInt < 0 || ( this.horz.isSelected() && colInt + this.myShipLen.get(shipsCombo.getSelectedIndex()) > totCols ) )
			{
				JOptionPane.showMessageDialog(this.myFrame, "The ship must lie entirely in the board, please enter a valid location.");
				this.row.setText("");
				this.col.setText("");
				return;
			}
		}
		catch ( NumberFormatException exp )
		{
			JOptionPane.showMessageDialog(this.myFrame, "The number of columns you entered is not valid, please enter an integer.");
			this.row.setText("");
			return;
		}
		
		// check if orientation is valid
		
		if ( ( !this.horz.isSelected() && !this.ver.isSelected() ) || ( this.horz.isSelected() && this.ver.isSelected() ) )
		{
			JOptionPane.showMessageDialog(this.myFrame, "You must select an orientation for your ship.");
			this.horz.setSelected(false);
			this.ver.setSelected(false);
			return;
		}
		
		ABSAttackBoard checkBoard = new ABSAttackBoard(this.totRows, this.totCols, this.myShips);
		ABSShip placedShip = null;
		
		if ( this.horz.isSelected() )
		{
			
			placedShip = new ABSShip(this.rowInt, this.colInt, this.myShipLen.get(shipsCombo.getSelectedIndex()), ABSShip.Orientation.HORIZONTAL);
			
			// check if the ship can be placed there
			
			if ( !isValidShip(checkBoard, placedShip.getRow(), placedShip.getCol(), placedShip.getLength(), placedShip.getOrientation()) )
			{
				JOptionPane.showMessageDialog(this.myFrame, "The ship cannot be placed there. Ships cannot overlap or be adjacent.");
				this.row.setText("");
				this.col.setText("");
				this.horz.setSelected(false);
				this.ver.setSelected(false);
				return;
			}
			
			for ( int shipLoc = 0 ; shipLoc < myShipLen.get(shipsCombo.getSelectedIndex()) ; shipLoc++ )
{
				
				// uncomment below to have text labels
				
				//getLabel(rowInt, colInt + shipLoc).setText("Ship");
				
				// put ship icon
				
				getLabel(rowInt, colInt + shipLoc).setIcon(ImageLibrary.getUnhitShipIcon());
				
			}
			
		}
		else if ( this.ver.isSelected() )
		{
			
			placedShip = new ABSShip(this.rowInt, this.colInt, this.myShipLen.get(shipsCombo.getSelectedIndex()), ABSShip.Orientation.VERTICAL);
			
			// check if the ship can be placed there
			
			if ( !isValidShip(checkBoard, placedShip.getRow(), placedShip.getCol(), placedShip.getLength(), placedShip.getOrientation()) )
			{
				JOptionPane.showMessageDialog(this.myFrame, "The ship cannot be placed there. Ships cannot overlap or be adjacent.");
				this.row.setText("");
				this.col.setText("");
				this.horz.setSelected(false);
				this.ver.setSelected(false);
				return;
			}
			
			for ( int shipLoc = 0 ; shipLoc < myShipLen.get(shipsCombo.getSelectedIndex()) ; shipLoc++ )
			{
				
				// uncomment below to have text labels
				
				//getLabel(rowInt + shipLoc, colInt).setText("Ship");
				
				// put ship icon
				
				getLabel(rowInt + shipLoc, colInt).setIcon(ImageLibrary.getUnhitShipIcon());
				
			}
		}
		
		// add ship to myShips
		
		this.myShips.add(placedShip);
		
		// remove ship from myShipLen
		
		myShipLen.remove(shipsCombo.getSelectedIndex());
		
		// update combo box
		
		shipsCombo.removeAllItems();
		for ( int i = 0 ; i < myShipLen.size() ; i++ )
		{
			String thisShip = "Ship of length " + myShipLen.get(i);
			shipsCombo.addItem(thisShip);
		}
		
	}
	
	private boolean isValidLoc ( int row, int col )
	{
		return ( row >= 0 && row < this.totRows && col >= 0 && col < this.totCols );
	}
	
	private boolean isValidShip ( ABSAttackBoard board, int row, int col, int size, ABSShip.Orientation ori )
	{
		if ( ori == ABSShip.Orientation.HORIZONTAL )
		{
			if ( col + size > this.totCols )
				return false;
			for ( int i = row - 1 ; i <= row + 1 ; i++ )
				for ( int j = col - 1; j <= col + size; j++ )
				{
					if ( isValidLoc(i, j) && board.isOccupied(i, j) )
						return false;
				}
		}
		else
		{
			if ( row + size > this.totRows )
				return false;
			for ( int i = row - 1 ; i <= row + size ; i++ )
				for ( int j = col - 1; j <= col + 1 ; j++ )
				{
					if ( isValidLoc(i, j) && board.isOccupied(i, j) )
						return false;
				}
		}
		return true;
	}
	
	private JLabel getLabel ( int row, int col )
	{
		return myLabels.get(row * totCols + col);
	}

}
