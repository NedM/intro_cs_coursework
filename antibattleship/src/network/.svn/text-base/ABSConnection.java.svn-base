package network;

import threads.*;

import java.io.*;
import java.net.*;

/**
 * Class to represent the connection on which game messages will be exchanged
 * @author nedmurp
 *
 */
public class ABSConnection
{
	
	public enum Timeout
	{
		STANDARD,
		NONE
	}
	
	boolean timeout = false;
	
	private volatile String response;
	private ServerSocket gameServerSocket = null;
	private Socket gameSocket = null;
	//private ObjectInputStream gameReader = null;
	//private ObjectOutputStream gameWriter = null;
	private BufferedReader gameReader = null;
	private PrintStream gameWriter = null;
	private Thread responseThread = null;
		
	public ABSConnection ( Timeout timeout )
	{
		this.timeout = (timeout == Timeout.STANDARD);
	}
	
	public ABSConnection()
	{
		this(Timeout.STANDARD);
	}
	
	/**
	 * Method to start the game connection as the server
	 * @author nedmurp
	 * @param connectionPort int : the port to connect on. If an invalid port is specified, the program will terminate
	 * @modifies the game server socket local to this class
	 */
	public boolean StartABSConnectionServer(int connectionPort)
	{
		
		// check if we're in tournament mode
		
		if ( !timeout )
		{
			try
			{
				// if tournament, we don't care if we block
				
				this.gameServerSocket = new ServerSocket(connectionPort);
				this.gameSocket = gameServerSocket.accept();
				this.Initialize();
				
				return true;
			}
			catch ( Exception e )
			{
				System.err.println("Failed to host game on port " + connectionPort + ".");
				System.exit(-1);
			}
		}
		
		try
		{

			// host game in different thread so we can check on its progress
			
			this.gameServerSocket = new ServerSocket(connectionPort);
			ServerThread serverThread = new ServerThread(gameServerSocket);
			Thread thread = new Thread(serverThread);
			thread.start();
			
			for ( int i = 0 ; i < 20 ; i++ )
			{
				if ( !serverThread.isConnected() )
				{
					// wait
					try
					{
						Thread.sleep(1000);
					}
					catch ( Exception ex )
					{
					}
					continue;
				}
				else
				{
					this.gameSocket = serverThread.getSocket();
					this.Initialize();
					return true;
				}
			}
			
		}
		catch(IOException ioEx)
		{
			System.err.println("Caught IOException while trying StartABSConnectionServer.\n" +
					"Error: Failed to connect on port " + connectionPort + ".\nException: " + ioEx.getMessage());
			System.exit(1);
		}
		
		// timeout
		
		return false;
	}
	
	public boolean StartABSConnectionClient(String server, int connectionPort)
	{
		
		// check if we're in tournament mode
		
		if ( !timeout )
		{
			try
			{
				this.gameSocket = new Socket(server, connectionPort);
			}
			catch ( Exception ex )
			{
				System.err.println("Failed to connect to host " + server + " on port " + connectionPort + ".");
				System.exit(-1);
			}
			this.Initialize();
			return true;
		}
		
		Socket sock = null;
		
		// try to connect for some time
		
		for ( int i = 0 ; i < 5 ; i++ )
		{
			try
			{
				sock = new Socket(server, connectionPort);
				return this.StartABSConnectionClient(sock);
			}
			catch(IOException ioEx)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch ( Exception ex )
				{
				}
				/*
				System.err.println("Caught IOException while trying to StartABSConnectionClient.\n" + 
						"Error: Failed to connect to server: " + server + " port: " + connectionPort + ".\nException: " + ioEx.getMessage());
						*/
			}
		}
	
		return false;
		
	}
	
	public boolean StartABSConnectionClient(Socket sock)
	{
		boolean connectSuccess = false;
		if(null != sock)
		{
			//System.out.println("Connecting to server: " + sock.getRemoteSocketAddress().toString() + " on port; " + sock.getPort());
			this.gameSocket = sock;
			connectSuccess = this.gameSocket.isConnected();
		}
		this.Initialize();
		
		return connectSuccess;
	}
	
	/*
	
	 * Method to send a message to the connected opponent
	 * Uses the .toString() method and calls SendGameMessage(String);
	 * @author nedmurp
	 * @param objMessage Object : the Message object to send
	 *
	public void SendGameMessage(Object objMessage)
	{
		SendGameMessage(objMessage.toString());
	}
	*/
	
	/**
	 * Method to send a mssage to the connected opponent
	 * @author nedmurp
	 * @param strMessage String : the message string to send
	 */
	public void SendGameMessage(String strMessage)
	{	
		this.gameWriter.println(strMessage);
		this.gameWriter.flush();
	}
	
	/**
	 * Method to dispose of the resources used by this class. Call this method during clean up.
	 * @author nedmurp
	 * @modifies the following objects local to this class: the game writer, the game reader, the game socket, the game server socket
	 */
	public void Dispose()
	{				
		try
		{
			if(null != this.responseThread && this.responseThread.isAlive())
			{
				//System.out.println("Response thread is alive. Attempting to interrupt...");
				this.responseThread.interrupt();
				this.responseThread = null;
				//System.out.println("Response thread is null");
			}
			
			if(this.gameWriter != null)
			{
				this.gameWriter.flush();
				this.gameWriter.close();
				this.gameWriter = null;
			}
			
			if(this.gameReader != null)
			{
				this.gameReader.close();
				this.gameReader = null;
			}
			
			if(this.gameSocket != null)
			{
				//System.out.println("Closing game socket");
				this.gameSocket.close();
				this.gameSocket = null;
			}
			
			if(this.gameServerSocket != null)
			{
				//System.out.println("Closing game server socket");
				this.gameServerSocket.close();
				this.gameServerSocket = null;
			}
		}
		catch(IOException e)
		{
			System.err.println("Caught IOException while trying to Dispose.\nException: " + e.getMessage());
		}
	}
	
	/**
	 * Initializes the game connection. If the connection attempt fails, the program will exit.
	 * @author nedmurp
	 * @modifies The following objects local to this class are modified: the game reader, the game writer, the game socket
	 */
	private void Initialize()
	{
		try
		{
			this.gameWriter = new PrintStream(this.gameSocket.getOutputStream(), true);
			this.gameReader = new BufferedReader(new InputStreamReader(this.gameSocket.getInputStream()));
		}
		catch (IOException e) 
		{
			System.err.println("Game accept failed. Exiting...");
			System.exit(1);
		}		
	}
	
	/**
	 * Read strings from the input buffer until the next response is received
	 * @author nedmurp
	 * @modifies the response variable local to this class
	 */
	public String WaitForResponse()
	{			
		
		if ( !timeout )
		{
			String ans = null;
			
			try
			{
				ans = this.gameReader.readLine();
			}
			catch ( Exception e )
			{
				System.err.println("Opponent disconnected.");
				System.exit(0);
			}
			
			return ans;
		}
		
		this.response = null;
		do
		{
			//this.response = this.gameReader.readUTF();
			try 
			{
				this.response = this.gameReader.readLine();
			} 
			catch (IOException e)
			{
				System.err.println("Opponent disconnected.");
				System.exit(0);
			}
		}while((this.response == null) || (this.response == "")); 
		
		//System.out.println("entering waiting state...");

		return this.response;
	}

}
