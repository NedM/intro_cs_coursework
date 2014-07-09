package guiListeners;

import players.*;
import gui.MainMenu;

import java.awt.event.*;
import javax.swing.*;

public class DeclineGame implements ActionListener
{

	private final JFrame acceptBoard;
	private final GUIPlayer guiPlayer;
	
	public DeclineGame ( JFrame acceptBoard, GUIPlayer guiPlayer )
	{
		this.acceptBoard = acceptBoard;
		this.guiPlayer = guiPlayer;
	}
	
	@Override
	public void actionPerformed ( ActionEvent e )
	{
		guiPlayer.sendDoWeAccept(GUIPlayer.InitGameResponse.REJECT);
		JOptionPane.showMessageDialog(this.acceptBoard, "You declined the game.");
		this.acceptBoard.dispose();
		new MainMenu();
	}
	
}
