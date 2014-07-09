package threads;

import java.io.*;
import java.net.*;

/**
 * 
 * Hosts a network game... we make a new thread since ServerSocket.accept() blocks
 * 
 * @author JVen
 *
 */

public class ServerThread implements Runnable 
{

	private final ServerSocket serverSocket;
	private Socket socket;
	private boolean connected;
	
	public ServerThread ( ServerSocket serverSocket )
	{
		this.serverSocket = serverSocket;
		this.connected = false;
	}
	
	@Override
	public void run()
	{
		try
		{
			Socket tmp = serverSocket.accept();
			this.connected = true;
			this.socket = tmp;
		}
		catch ( IOException ex )
		{
			System.err.println("Caught IOException while trying StartABSConnectionServer.\n" +
					"Error: Failed to listen on the designated port." + ".\nException: " + ex.getMessage());
			System.exit(1);
		}
	}
	
	public boolean isConnected()
	{
		return this.connected;
	}
	
	public Socket getSocket()
	{
		return this.socket;
	}
	
}
