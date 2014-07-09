package players;

import java.io.*;
import java.net.*;

import message.ABSMessage;
import client.*;

/**
 * 
 * Wraps 6.005 Spring 2011 reference player (as listening player) in our ABSPlayer interface
 * This is primarily intended for our first checkoff... afterwards, we should be using the code in the network package
 * 
 * @author JVen
 * @see <a href="http://6005-abs.xvm.mit.edu/">6.005 Reference Player</a>
 *
 */

public class ListeningRefPlayer implements ABSPlayer
{

	public enum Verbosity
	{
		DEBUG,
		OFF
	}
	
	private static final String refPath = "6005-abs.xvm.mit.edu";
	private static final int refPort = 1338;
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	/**
	 * 
	 * Constructors
	 * 
	 * @author JVen
	 * 
	 */
	
	public ListeningRefPlayer()
	{
		this(Verbosity.OFF);
	}
	
	public ListeningRefPlayer ( Verbosity v )
	{
		if ( v == Verbosity.DEBUG )
			System.out.println("Attempting to connect to the ref player at '" + refPath + "'...");
		
		try
		{
			socket = new Socket(refPath, refPort);
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			if ( v == Verbosity.DEBUG )
				System.out.println("Successfully connected to the ref player at " + socket.getInetAddress() + " on port " + socket.getPort() + ".");
		}
		catch ( IOException e )
		{
			if ( v == Verbosity.DEBUG )
				System.out.println("Unable to connect to the ref player.");
			System.exit(-1);
		}
	}
	
	@Override
	public String getName()
	{
		return "ListeningRefPlayer";
	}
	
	/**
	 * 
	 * Send a message to the ref player
	 * 
	 * @author JVen
	 * @param m the message to send to the ref player
	 * 
	 */
	
	@Override
	public void sendMsgToPlayer ( ABSMessage m )
	{
		writer.println(m.toString());
	}

	/**
	 * 
	 * Get a message from the ref player
	 * 
	 * @author JVen
	 * @return the message sent from the ref player
	 * 
	 */
	
	@Override
	public ABSMessage getMsgFromPlayer()
	{
		
		ABSMessage m = null;
		
		try
		{
			String s = reader.readLine();
			m = ABSMessage.CreateMessage(s);			
		}
		catch ( Exception e )
		{
			System.exit(-1);
		}
		
		return m;
		
	}
	
}
