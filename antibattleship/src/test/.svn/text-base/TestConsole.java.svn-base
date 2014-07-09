package test;

import client.*;
import players.*;
import players.jasper.*;

/**
 * 
 * Test class, for use in first project checkoff (4/29/11)
 * 
 * @author JVen
 * 
 */

public class TestConsole
{
	/**
	 * 
	 * Sets up a game between a console Player and the RefPlayer
	 * 
	 * @author JVen
	 * @param args Command line arguments, ignored.
	 * 
	 */
	
	public static void main ( String[] args )
	{
		ABSGame game = null;
		try
		{
			//game = new ABSGame(new HumanConsolePlayer(), new ListeningRefPlayer());
			//game = new ABSGame(new AsciiConsolePlayer(), new ListeningRefPlayer());
			game = new ABSGame(new HumanConsolePlayer(), new JasperBot(false));
		}
		catch ( Exception e )
		{
			System.out.println("Ruh roh.");
			System.exit(-1);
		}
		game.play();
	}
}
