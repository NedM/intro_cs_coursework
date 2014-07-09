package friendly.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens in port 4444 for a connection. If a connection occurs, reads the
 * first line, echoes it back, and exits.
 */
public class Example {

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

		// The server waits until someone connects, and when someone
		// does, it creates a socket.
		Socket socket = null;
		try {
			System.out.println("Accepting connections...");
			socket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		// Create a buffered reader to read the input.
		BufferedReader reader = null;
		PrintWriter writer = null;

		try {

			// Create reader and writer, connect them to socket.
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);

			// Acknowledge connection.
			writer.append("Connected to server. Type a line of input followed by the ENTER key.");
			writer.append(System.getProperty("line.separator"));
			writer.flush();

			// Read the first line.
			String line = reader.readLine();

			// Echo the first line.
			writer.println("You said: " + line);
			
			// Close streams, socket and socket server.
			writer.println("Closing connection... ");
			writer.flush();
			reader.close();
			writer.close();
			socket.close();
			serverSocket.close();
			

		} catch (IOException e) {
			System.out.println("I/O error (will exit): " + e.getMessage());
			System.exit(1);
		}
		
		System.out.println("Terminating server.");
	}
}
