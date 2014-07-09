package friendly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class FriendlyClientDriver {

	private Socket friendlySocket;
	private PrintWriter out;
	private BufferedReader in;

	String hostName;
	int port;

	public FriendlyClientDriver() throws IOException, UnknownHostException {
		this("localhost", 4444);
	}

	public FriendlyClientDriver(String hostName, int port) throws IOException {
		this.hostName = hostName;
		this.port = port;

		friendlySocket = new Socket(hostName, port);
		out = new PrintWriter(friendlySocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(friendlySocket.getInputStream()));
	}

	public void writeLine(String input) {
		out.println(input);
	}

	public String readLine() throws IOException {
		return in.readLine();
	}

	public boolean canRead() throws IOException {
		return in.ready();
	}

	public void close() throws IOException {
		out.close();
		in.close();
		friendlySocket.close();
	}

}