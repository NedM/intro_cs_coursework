package guiListeners;

import gui.*;
import java.awt.event.*;
import javax.swing.*;

public class SpawnMainMenu implements ActionListener
{
	
	private JFrame playScreen;
	private JFrame gameOver;
	
	public SpawnMainMenu ( JFrame playScreen, JFrame gameOver )
	{
		this.playScreen = playScreen;
		this.gameOver = gameOver;
	}
	
	@Override
	public void actionPerformed ( ActionEvent e )
	{
		this.playScreen.dispose();
		this.gameOver.dispose();
		new MainMenu();
	}
	
}
