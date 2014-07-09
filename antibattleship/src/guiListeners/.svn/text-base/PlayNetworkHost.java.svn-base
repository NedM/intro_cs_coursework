package guiListeners;

import gui.*;
import players.*;
import threads.*;
import client.*;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public class PlayNetworkHost implements ActionListener, Runnable
{
	private int timeoutInMilliseconds = 20 * 1000;
	private volatile boolean connectSuccess; 
	private int portNumber;
	
	private JFrame mainMenu;
	private JFrame hostOptions;
	private JTextField portField;
	private String username;
	private NetworkPlayer opp;
	
	public PlayNetworkHost ( JFrame mainMenu, JFrame hostOptions, JTextField portField, String username )
	{
		this.mainMenu = mainMenu;
		this.hostOptions = hostOptions;
		this.portField = portField;
		this.username = username;
	}
	
	public void actionPerformed ( ActionEvent e )
	{		
		try
		{
			this.portNumber = Integer.parseInt(portField.getText());
			if ( this.portNumber < 0 )
			{
				JOptionPane.showMessageDialog(this.hostOptions, "Invalid port.");
				portField.setText("");
				return;
			}
		}
		catch ( Exception ex )
		{
			JOptionPane.showMessageDialog(this.hostOptions, "Invalid port.");
			portField.setText("");
			return;
		}
		
		GUIPlayer me = new GUIPlayer(false, username);
//		NetworkPlayer opp = new NetworkPlayer();
		this.opp = new NetworkPlayer();
		
		WaitMessage msg1 = WaitMessage.generate("Waiting for a client to connect...");
		
		Thread hostSetupThread = new Thread(this, "hostSetupThread");
		hostSetupThread.start();
		
		// warn user
		
		JOptionPane.showMessageDialog(hostOptions, "After pressing OK, clients will have 20 seconds to connect.");
		
		// bring wait screen to front
		
		msg1.toFront();
		msg1.setAlwaysOnTop(true);
		
		// exit if could not connect
		long start = System.currentTimeMillis();		
		while(!this.connectSuccess && (System.currentTimeMillis() < (start + timeoutInMilliseconds)))
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				
			}
		}
		
		msg1.dispose();
		
		if ( !this.connectSuccess )
		{
			JOptionPane.showMessageDialog(hostOptions, "Could not find an opponent.");
			return;
		}
		
		// wait for GUIPlayer to send us game information
//		opp.printGameMessageToChatWin(ChatSource.Game, "Waiting for opponent to propose a game...");
		WaitMessage msg2 = WaitMessage.generate("Waiting for opponent to propose a game...");
        
        // opponent found, make game
		
		JOptionPane.showMessageDialog(this.hostOptions, "Opponent found.");
		hostOptions.dispose();
		
		// bring wait to front
		
		msg2.toFront();
		msg2.setAlwaysOnTop(true);
		
		// start game
		
		ABSGame myGame = new ABSGame(opp, me, false);
		
		// play the game!
		
		Thread gameThread = new Thread(new GameThread(myGame, opp));
		gameThread.start();
        
		// wait a little bit
        
        try
        {
        	Thread.sleep(300);
        }
        catch ( Exception ex )
        {
        }
		
		while ( !me.isInitGameSent() )
		{
			try
			{
				Thread.sleep(100);
			}
			catch ( Exception ex )
			{
			}
		}
		msg2.dispose();
		
		int numRows = me.getNumRows();
		int numCols = me.getNumCols();
		List<Integer> shipSizeList = me.getShipSizeList();
		boolean isSalvo = me.getIsSalvo();
		
		// check if the game is valid, auto deny if it's not
		
		if ( !isValidGame(numRows, numCols, shipSizeList) )
		{
			me.sendDoWeAccept(GUIPlayer.InitGameResponse.REJECT);
			JOptionPane.showMessageDialog(this.hostOptions, "The opponent specified an illegal game.");
			return;
		}
		
		// prompt the user
		
		new AcceptBoard(me, numRows, numCols, shipSizeList, isSalvo);
		mainMenu.dispose();
		
	}
	
	private boolean isValidGame ( int numRows, int numCols, List<Integer> shipSizeList )
	{
		if ( numRows < 2 || numCols < 2 || shipSizeList.isEmpty() )
			return false;
		if ( numRows < 2 * shipSizeList.size() && numCols < 2 * shipSizeList.size() )
			return false;
		for ( Integer size : shipSizeList )
		{
			if ( size < 1 || size > numRows || size > numCols )
				return false;
		}
		return true;
	}

	@Override
	public void run() 
	{
		this.connectSuccess = this.opp.HostGame(this.portNumber);		
	}

}
