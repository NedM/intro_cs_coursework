package guiListeners;

import client.*;
import players.*;
import gui.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * Action Listener submits ship locations
 * 
 * @author jboortz
 *
 */
public class SubmitShips implements ActionListener
{

	private final JFrame myFrame;
	private final GUIPlayer myPlayer;
	private final List<Integer> shipLens;
	private final List<ABSShip> myShips;
	private int row;
	private int col;
	private List<JLabel> myLabels;
	
	public SubmitShips ( JFrame frame, GUIPlayer player, List<Integer> shipLens, List<ABSShip> ships, List<JLabel> myLabels, int row, int col )
	{
		this.myFrame = frame;
		this.myPlayer = player;
		this.shipLens = shipLens;
		this.myShips = ships;
		this.row = row;
		this.col = col;
		this.myLabels = myLabels;
	}
	
	@Override
	public void actionPerformed ( ActionEvent e)
	{
		
		if ( !shipLens.isEmpty() )
		{
			JOptionPane.showMessageDialog(this.myFrame, "You must place all of your ships!");
			return;
		}
		
		myPlayer.sendShipLocs(myShips);
		
		WaitMessage msg = WaitMessage.generate("Waiting for opponent to send board hash...");
		while ( myPlayer.isBoardHashReceived() == false )
		{
			// TODO waiting screen
			try
			{
				Thread.sleep(100);
			}
			catch ( InterruptedException e1 )
			{
			}
		}
		msg.dispose();
		
		myFrame.dispose();
		new Play(this.myPlayer, this.myLabels, this.myShips, this.col, this.row);
	}
	
}
