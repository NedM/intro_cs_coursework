package friendly;

/**
 * The friendly ear program chats with the user until the user types "bye", then
 * terminates.
 */
public class Main {

	public static void main(String[] args) {
		FriendlyServer server = new FriendlyServer(); 
		server.start();
	}
}
