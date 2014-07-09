package guiListeners;

import gui.*;
import threads.*;
import client.*;
import players.*;
import players.jasper.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * Action Listener initiate game with AI Bot
 * 
 * @author jboortz
 *
 */ 
public class PlayComputer implements ActionListener
{

	private final JFrame mainMenu;
	private final JTextField usernameField;
	
	public PlayComputer ( JFrame mainMenu, JTextField usernameField )
	{
		this.mainMenu = mainMenu;
		this.usernameField = usernameField;
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		
		// check if there's a username
		
		if ( usernameField.getText().isEmpty() )
		{
			JOptionPane.showMessageDialog(this.mainMenu, "Please input a username.");
			usernameField.setText("");
			return;
		}
		
		String username = usernameField.getText();
		
		JasperBot myJasperBot = new JasperBot(false, JasperBot.Verbosity.DEBUG);
		GUIPlayer me = new GUIPlayer(true, username, GUIPlayer.Verbosity.DEBUG);
		ABSGame myGame = new ABSGame(me, myJasperBot, false);
		
		// play the game!
		
		Thread gameThread = new Thread(new GameThread(myGame, myJasperBot));
		gameThread.start();
		
		new BoardSelect(me);
		mainMenu.dispose();
		
	}
	
}
