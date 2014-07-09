package guiListeners;

import players.*;
import gui.*;

import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class AcceptGame implements ActionListener
{

	private final JFrame acceptBoard;
	private final GUIPlayer guiPlayer;
	private final int numRows;
	private final int numCols;
	private final List<Integer> shipSizeList;
	
	public AcceptGame ( JFrame acceptBoard, GUIPlayer guiPlayer, int numRows, int numCols, List<Integer> shipSizeList )
	{
		this.acceptBoard = acceptBoard;
		this.guiPlayer = guiPlayer;
		this.numRows = numRows;
		this.numCols = numCols;
		this.shipSizeList = new ArrayList<Integer>(shipSizeList);
	}
	
	@Override
	public void actionPerformed ( ActionEvent e )
	{
		guiPlayer.sendDoWeAccept(GUIPlayer.InitGameResponse.ACCEPT);
		JOptionPane.showMessageDialog(this.acceptBoard, "You accepted the game.");
		this.acceptBoard.dispose();
		new PlaceShips(acceptBoard, guiPlayer, numRows, numCols, shipSizeList );
	}
	
}
