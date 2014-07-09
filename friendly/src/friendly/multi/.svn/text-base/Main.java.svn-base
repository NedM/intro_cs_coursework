package friendly.multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import friendly.FriendlyServer;

/**
 * Multi-threaded version of Friendly. Listens for incoming connections on port
 * 4444. When a new connection is accepted, spawns a new thread, which handles
 * the interaction with the user, and goes back to accepting connections.
 */
public class Main {
	
	public static void main(String[] args) {

		// Create a socket server that listens on port 4444 in the local
		// machine.
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}
		

		try {
			
			while (true) 
			{
				System.out.println("Accepting connections...");
				Socket socket = serverSocket.accept();

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				
		        FriendlyServer server = new FriendlyServer(reader, writer, socket);
		        Thread thread = new Thread(server);
		        thread.start();
			}
			
		}
		catch (IOException e) 
		{
			System.err.println("Accept failed.");
			System.exit(1);
		}
	}
}
