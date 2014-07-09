package guiListeners;

import gui.*;
import players.*;
import threads.*;
import client.*;

import java.util.*;

import java.awt.event.*;
import javax.swing.*;


public class PlayNetworkClient implements ActionListener
{
	
	private JFrame mainMenu;
	private JFrame clientOptions;
	private JTextField hostnameField;
	private JTextField portField;
	private String username;
	
	public PlayNetworkClient ( JFrame mainMenu, JFrame clientOptions, JTextField hostnameField, JTextField portField, String username )
	{
		this.mainMenu = mainMenu;
		this.clientOptions = clientOptions;
		this.hostnameField = hostnameField;
		this.portField = portField;
		this.username = username;
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		
		String hostname = hostnameField.getText();
		int port = -1;
		
		try
		{
			port = Integer.parseInt(portField.getText());
			if ( port <= 0 )
			{
				JOptionPane.showMessageDialog(clientOptions, "Invalid port.");
				portField.setText("");
				return;
			}
		}
		catch ( Exception ex )
		{
			JOptionPane.showMessageDialog(clientOptions, "Invalid port.");
			portField.setText("");
			return;
		}
		
		// close client options
		
		clientOptions.dispose();
		
		GUIPlayer me = new GUIPlayer(true, username);
		NetworkPlayer opp = new NetworkPlayer();
        
        // wait a little bit
        
        try
        {
        	Thread.sleep(300);
        }
        catch ( Exception ex )
        {
        }
		
        // wait screen
        
		WaitMessage msg = WaitMessage.generate("Looking for host...");
		
		// warn user
		
		JOptionPane.showMessageDialog(clientOptions, "After pressing OK, the game will search for the host for 10 seconds.");
		
		// bring wait screen to front
		
		msg.toFront();
		msg.setAlwaysOnTop(true);
		
		boolean connected = opp.ConnectAsClient(hostname, port);
		msg.dispose();
		
		if ( !connected )
		{
			JOptionPane.showMessageDialog(clientOptions, "Could not find the host.");
			return;
		}
		
		ABSGame myGame = new ABSGame(me, opp, true);
		
		// play the game!
		
		Thread gameThread = new Thread(new GameThread(myGame, opp));
		gameThread.start();
		
		// prompt user for game information
		
		new BoardSelect(me);
		mainMenu.dispose();
		
	}

}
