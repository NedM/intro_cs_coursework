package guiListeners;

import gui.*;
import players.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * 
 * Action Listener submits board specs
 * 
 * @author jboortz
 *
 */
public class SubmitBoard implements ActionListener
{

	private JFrame myFrame;
	private GUIPlayer me;
	private List<Integer> shipLen;
	private JTextField col;
	private JTextField row;
	private JCheckBox salvo;
	private int rowInt;
	private int colInt;
	
	public SubmitBoard ( JFrame myFrame, GUIPlayer me, List<Integer> shipLen, JTextField row, JTextField col, JCheckBox salvo )
	{
		this.myFrame = myFrame;
		this.me = me;
		this.shipLen = shipLen;
		this.col = col;
		this.row = row;
		this.salvo = salvo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// check if inputs are valid
		
		try
		{
			rowInt = Integer.parseInt(row.getText());
		}
		catch ( NumberFormatException exp )
		{
			JOptionPane.showMessageDialog(this.myFrame, "The number of rows you entered is not valid, please enter an integer.");
			row.setText("");
			return;
		}
		
		try
		{
			colInt = Integer.parseInt(col.getText());
		}
		catch ( NumberFormatException exp )
		{
			JOptionPane.showMessageDialog(this.myFrame, "The number of columns you entered is not valid, please enter an integer.");
			col.setText("");
			return;
		}

		if ( !this.isValidGame(rowInt, colInt, shipLen) )
		{
			JOptionPane.showMessageDialog(this.myFrame, "The options you selected don't constitute a valid game, please try again.");
			return;
		}
		
		// inputs are valid, send info to GUIPlayer
		
		me.sendBoardInfo(this.rowInt, this.colInt, this.shipLen, this.salvo.isSelected());
		
		// wait for opponent response
		
		WaitMessage msg = WaitMessage.generate("Waiting for opponent to respond to game proposal...");
		while ( me.didOpponentAccept() == GUIPlayer.InitGameResponse.DONT_KNOW )
		{
			try
			{
				Thread.sleep(100);
			}
			catch ( Exception ex )
			{
			}
		}
		msg.dispose();
		
		if ( me.didOpponentAccept() == GUIPlayer.InitGameResponse.ACCEPT )
		{
			JOptionPane.showMessageDialog(this.myFrame, "Your opponent accepted your game.");
			new PlaceShips(myFrame, me, this.rowInt, this.colInt, this.shipLen);
		}
		else
		{
			JOptionPane.showMessageDialog(this.myFrame, "Your opponent declined your game.");
			new MainMenu();	
		}
		
		this.myFrame.dispose();
			
	}
	
	private boolean isValidGame ( int numRows, int numCols, List<Integer> shipSizeList )
	{
		if ( numRows < 1 || numCols < 1 || shipSizeList.isEmpty() )
			return false;
		//if both the height and width are less than two times the number of ships
		if (( numRows < 2 * shipSizeList.size()) && (numCols < 2 * shipSizeList.size()) )
			return false;
		//now check if ships fit
		for ( Integer size : shipSizeList )
		{
			//if ship length less than one, then not valid
			if ( size < 1 ){
				return false;
			}
			//if the ship is too long to fit on the board not valid
			if ((size > numRows) && (size > numCols )){
				return false;
			// if the ships will only fit the direction that is less than 2 * the num of ships not valid
			}else if ((size > numRows) && (numRows < 2 * shipSizeList.size())){
				return false;
			}else if ((size > numCols) && (numCols < 2 * shipSizeList.size())){
				return false;
			}
		}
		return true;
	}
	
}
