package test;

import players.jasper.*;
import players.jasper.JasperBot.*;
import players.*;
import players.NetworkPlayer.ChatOption;
import client.*;
import client.ABSGame.LogOption;
import threads.*;
import threads.TournamentThread.NetworkPlayerType;
import tournamentGUI.*;

import java.util.*;
import java.util.regex.*;

import network.ABSConnection.Timeout;

/**
 * 
 * This main method is run when playing our bot in the tournament
 * 
 * @author 6005StaffSP11, JVen
 *
 */

public class TournamentMain
{

	public static void main ( String[] args )
	{
		
		// parse arguments
		
		Pattern p = Pattern.compile("--([a-z_]+)=(.*)");
		
		Integer port = null;
		String hostname = null;
		String board_size = null;
		String ship_size = null;
		Integer delay = null;
		
		for ( int i = 0 ; i < args.length ; ++i )
		{
			Matcher m = p.matcher(args[i]);
			if ( !m.matches() )
			{
				System.err.println("ERROR: Unrecognized input argument: " + args[i]);
				System.exit(-1);
			}
			if ( m.group(1).equals("port") )
			{
				port = Integer.parseInt(m.group(2));
			}
			else if (m.group(1).equals("hostname"))
			{
				hostname = m.group(2);
			}
			else if (m.group(1).equals("board_size"))
			{
				board_size = m.group(2);
			}
			else if (m.group(1).equals("ship_size"))
			{
				ship_size = m.group(2);
			}
			else if (m.group(1).equals("delay"))
			{
				delay = Integer.parseInt(m.group(2));
			}
			else
			{
				System.err.println("ERROR: Unrecognized flag: " + m.group(1));
				System.exit(-1);
			}
		}
		
		// optional flag eh?
		
		if ( delay == null || delay < 50 )
			delay = 50;
		
		// parse game info
		
		
		int numRows = -1;
		int numCols = -1;
		List<Integer> shipSizeList = new ArrayList<Integer>();
		
		try
		{
		
			if ( board_size != null )
			{
				String[] toks = board_size.split("x");
				numRows = Integer.parseInt(toks[0]);
				numCols = Integer.parseInt(toks[1]);
			}
			
			if ( ship_size != null )
			{
				String[] toks = ship_size.substring(1, ship_size.length() - 1).split(",");
				for ( String s : toks )
					shipSizeList.add(Integer.parseInt(s));
			}
			
		}
		catch ( Exception e )
		{
			System.err.println("Typo in your arguments, try again please!");
		}
		
		// create players and game
		
		boolean isInitiatingPlayer = ( hostname != null );
		JasperBot jasper;
		NetworkPlayer evil;
		ABSGame justice;
		Thread winning;
		TournamentGUI glory;
		
		if ( isInitiatingPlayer )
		{
			jasper = new JasperBot(MoveOrder.INITIATING, Verbosity.TOURNAMENT, numRows, numCols, shipSizeList, delay);
			evil = new NetworkPlayer(ChatOption.DISABLE, Timeout.NONE);
			evil.ConnectAsClient(hostname, port);
			justice = new ABSGame(jasper, evil, LogOption.DISABLE);
			//winning = new Thread(new TournamentThread(justice, evil, NetworkPlayerType.RECEIVING, hostname, port));
		}
		else
		{
			jasper = new JasperBot(MoveOrder.RECEIVING, Verbosity.TOURNAMENT, numRows, numCols, shipSizeList, delay);
			evil = new NetworkPlayer(ChatOption.DISABLE, Timeout.NONE);
			evil.HostGame(port);
			justice = new ABSGame(evil, jasper, LogOption.DISABLE);
			//winning = new Thread(new TournamentThread(justice, evil, NetworkPlayerType.INITIATING, hostname, port));
		}
		
		// run game in new thread
		
		winning = new Thread(new GameThread(justice, evil));
		winning.start();
		
		// wait for game to start
		
		while ( !justice.isGameStarted() )
		{
			
			// wait a little bit
			
			try
			{
				Thread.sleep(50);
			}
			catch ( Exception e )
			{
			}
			
		}
		
		// show GUI
		
		glory = new TournamentGUI(jasper);
		glory.setVisible(true);
		glory.toFront();
		
		// refresh that junk
		
		while ( winning.isAlive() )
		{
			
			// wait a little bit
			
			try
			{
				Thread.sleep(50);
			}
			catch ( Exception e )
			{
			}
			
			// refresh
			
			glory.refresh();
			
		}
		
		// refresh one more for good measure
		
		glory.refresh();
		
	}
}
