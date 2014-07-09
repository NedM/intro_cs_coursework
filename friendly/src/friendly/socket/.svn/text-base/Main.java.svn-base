package friendly.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import friendly.FriendlyServer;

/**
 * Listens in port 4444 for a connection. If a connection occurs, reads the
 * first line, echoes it back, and exits.
 */
public class Main {

	public static void main(String[] args) 
	{
		boolean runServer = true;
		// Create a socket server that listens on port 4444 in the local
		// machine.
		ServerSocket serverSocket = null;
		try 
		{
			serverSocket = new ServerSocket(4444);
		} 
		catch (IOException e) 
		{
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}

		do
		{
			// The server waits until someone connects, and when someone
			// does, it creates a socket.
			Socket socket = null;
			try 
			{
				System.out.println("Accepting connections...");
				socket = serverSocket.accept();
			} 
			catch (IOException e) 
			{
				System.err.println("Accept failed.");
				System.exit(1);
			}
	
			// Create a buffered reader to read the input.
			BufferedReader reader = null;
			PrintWriter writer = null;
	
			try 
			{
				// Create reader and writer, connect them to socket.
				reader = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
				FriendlyServer server = new FriendlyServer(reader, writer, socket); 
				server.start();
				//Interact with Friendly			
				writer.flush();
				writer.close();
				reader.close();			
				socket.close();	
			} 
			catch (IOException e) 
			{
				System.out.println("I/O error (will exit): " + e.getMessage());
				System.exit(1);
			}
		} while(runServer); //Do this forever!!
		
		try
		{		
			serverSocket.close();
		}
		catch(IOException ex)
		{
			System.out.println("I/O error (will terminate): " + ex.getMessage());
			System.exit(1);			
		}
		finally
		{
			System.out.println("Terminating server.");			
		}
	}
}
