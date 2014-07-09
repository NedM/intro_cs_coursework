package friendly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class FriendlyServer implements Runnable {
	// Create a buffered reader. Buffered readers let you
	// read from a stream a line at a time.
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket socket;
	private Map<String, String> cannedResponses;

	public FriendlyServer(BufferedReader reader2, PrintWriter writer, Socket socket) 
	{
		this.reader = reader2;
		this.writer = writer;
		this.socket = socket;
	}

	public FriendlyServer() 
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		this.writer = new PrintWriter(System.out, true);
		this.socket = null;
	}
	
	private void buildResponseMap()
	{
		cannedResponses = new LinkedHashMap<String, String>();
		cannedResponses.put("i think it's going to rain today.", "Nonsense! Let's go to the beach.");
		cannedResponses.put("what's happening?", "We need to talk about your TPS reports.");
		cannedResponses.put("i think i'm gonna lose it.", "Uh-oh. Sounds like somebody's got a case of the Mondays.");
		cannedResponses.put("let's go to the seaside.", "I say! Capital idea!");		
	}
	
	/**
	 * This method lunches the server and the servers interaction with the client.
	 */
	public void start() 
	{
		buildResponseMap();

		// Start by printing out a greeting.
		this.writer.println("Hello, my friend! How are you today?");

		try
		{
			// Read the first line.
			String line = reader.readLine();

			while (line != null) 
			{ 
				// line==null means the end of the stream
				// was reached.

				// If user typed "bye", exit the loop.
				// Use `toLowerCase' and `startsWith' to allow variations like
				// `BYE',`Bye', `bye!', etc.
				if (line.toLowerCase().startsWith("bye")) 
				{
					this.writer.println("Well, bye then.");
					break;
				}
				
				if(cannedResponses.containsKey(line.toLowerCase()))
				{
					this.writer.println(cannedResponses.get(line.toLowerCase()));
				}
				else if (line.endsWith("?")) 
				{
					// User asked a question.
					this.writer.println("Interesting question.");
				}
				else
				{
					this.writer.println("I see. Why is that?");
				}

				// Read next line.
				line = reader.readLine();
			}
		} 
		catch (IOException e) 
		{
			System.out.println("I/O error: " + e.getMessage());
			System.exit(1);
		}
	}

	@Override
	public void run() 
	{
		try
		{
			//Interact with Friendly
			this.start();
						
			//Close friendly down
			this.writer.flush();
			this.writer.close();
			this.reader.close();			
			this.socket.close();
		}
		catch(IOException e)
		{
			System.out.println("I/O error (will terminate): " + e.getMessage());
			System.exit(1);
		}
	}
}