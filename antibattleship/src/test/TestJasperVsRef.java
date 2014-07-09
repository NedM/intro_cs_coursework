package test;

import client.*;
import players.*;
import players.jasper.*;
import players.jasper.JasperBot.Verbosity;

/**
 * 
 * Test class, JasperBot vs reference player
 * 
 * @author JVen
 * 
 */

public class TestJasperVsRef
{
	/**
	 * 
	 * Sets up a game between JasperBot and the RefPlayer
	 * Outputs log to file
	 * 
	 * @author JVen
	 * @param args Command line arguments, ignored.
	 * @see TestJasperResults1.txt file in this folder
	 * 
	 */
	
	public static void main ( String[] args )
	{
		
		int numGames = 100;
		int Awins = 0;
		int Bwins = 0;
		int Aerrors = 0;
		int Berrors = 0;
		
		System.out.println("Playing " + numGames + " games between Jasper and the reference player...");
		
		for ( int i = 0 ; i < numGames ; i++ )
		{
			if ( i % (numGames / 10) == 0 )
				System.out.println(((i * 100) / numGames) + "% complete...");

			ABSGame game = null;
			try
			{
				game = new ABSGame(new JasperBot(true, Verbosity.DEBUG), new ListeningRefPlayer(ListeningRefPlayer.Verbosity.OFF), true);
			}
			catch ( Exception e )
			{
				System.out.println("Ruh roh.");
				System.exit(-1);
			}
			switch ( game.play() )
			{
				case A_VICTORY:
					Awins++;
					break;
					
				case B_VICTORY:
					Bwins++;
					break;
					
				case A_THREW_ERROR:
					Aerrors++;
					break;
					
				case B_THREW_ERROR:
					Berrors++;
					break;
			}
		}
		
		System.out.println("Done.\n\nResults:");
		System.out.println("A won " + Awins + " times.");
		System.out.println("B won " + Bwins + " times.");
		System.out.println("A threw " + Aerrors + " errors.");
		System.out.println("B threw " + Berrors + " errors.");
	}
}
