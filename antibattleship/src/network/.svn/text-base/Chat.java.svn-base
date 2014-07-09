package network;


import java.io.*;
import java.net.*;
import info.clearthought.layout.TableLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Class to initialize chat session
 * 
 * @author nedmurp
 *
 */
public class Chat extends JFrame implements Runnable
{
	//UI Members 
	private static final long serialVersionUID = 1L;
	private Container chatPane = null;
	private JTextPane chatOutputBox = null;
	private JScrollPane listScroller = null;
	private JTextField enteredText = null;
	private StyledDocument doc = null;
	
	private final String TIME_FORMAT = "H:mm:ss";
	private String playerName;
	private String opponentName;
	
	//Network Members
	private volatile boolean stopChatFlag;
	private ServerSocket chatServerSocket = null;
	private Socket chatSocket = null;
	private PrintWriter chatWriter = null;
	private BufferedReader chatReader = null;
	private Thread readerThread = null;
	
	public Chat()
	{
		super("Antibattleship - Chat");
		// call System.exit() when user closes the window
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		super.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
//				System.out.println("WindowClosing Event detected!");
				Dispose();  //Chat threads dispose
				dispose();  //Window dispose
			}
		});
	}
	
	/**
	 * Sets up the Gui and makes it visible
	 * @author nedmurp 
	 */
	private void showChatGUI()
	{
		this.chatPane = super.getContentPane();
		this.chatOutputBox = new JTextPane();
		this.chatOutputBox.setEditable(false);
		this.doc = this.chatOutputBox.getStyledDocument();
		this.listScroller = new JScrollPane(this.chatOutputBox);
		this.enteredText = new JTextField(30);
		
		DefaultCaret caret = (DefaultCaret)this.chatOutputBox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
    
		double size[][] = {{.05, .9, .05},{.025, .05, .75, .025, .1, .05}};
		this.chatPane.setLayout(new TableLayout(size));
		this.chatPane.setBackground(Color.black);
		
		JLabel oppName = new JLabel("AntiBattleShip Chat");
		this.chatPane.add(oppName, "1,1");		
		oppName.setForeground(Color.white);		
		
        this.listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.chatPane.add(listScroller, "1,2");
                
        this.chatPane.add(enteredText, "1,4");
        this.enteredText.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		SendChatMessage(enteredText.getText());
        		enteredText.setText("");
        	}
        });

        this.setPreferredSize(new Dimension(400, 600));
        this.pack();
        this.setVisible(true);	
	}
	
	/**
	 * Prints a chat message with the appropriate formatting to the chat UI
	 * @param source ChatSource - who sent the message -> determines formatting
	 * @param message String - the message itself
	 */
	private void printChatMessage(ChatSource source, String message)
	{
		if(null != this.chatOutputBox)
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(this.TIME_FORMAT);
			
			if(message.length() > 0)
			{
				SimpleAttributeSet keyWord = new SimpleAttributeSet();
				switch(source)
				{
					case Player:
						StyleConstants.setForeground(keyWord, Color.BLUE);
						StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_LEFT);
						message = "[" + sdf.format(cal.getTime()) + "] " + message + "\n";
//						message = this.player.getName() + " [" + sdf.format(cal.getTime()) + "] > " + message + "\n";
						break;
					case Opponent:
						StyleConstants.setForeground(keyWord, Color.RED);
						StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_RIGHT);						
						message = message + " [" + sdf.format(cal.getTime()) + "]\n";
//						message = this.opponent.getName() + " [" + sdf.format(cal.getTime()) + "] > " + message + "\n";
						break;
					default:						
						StyleConstants.setForeground(keyWord, Color.BLACK);
						StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_CENTER);
						StyleConstants.setBold(keyWord, true);
						StyleConstants.setFontSize(keyWord, 14);
						message = "<< " + message + " >>\n";
						break;
				}
				
				try 
				{
					this.doc.setParagraphAttributes(doc.getLength(), message.length(), keyWord, true);
					this.doc.insertString(this.doc.getLength(), message, keyWord);
				} catch (BadLocationException e) 
				{
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				this.chatOutputBox.setCaretPosition(this.chatOutputBox.getDocument().getLength());
				
			}
		}
	}
	
	public void setPlayerName(String playerName)
	{
		if(playerName != null && playerName.length() > 0)
		{
			this.playerName = playerName;
		}
	}
	
	public void setOpponentName(String opponentName)
	{
		if(null != opponentName && opponentName.length() > 0)
		{
			this.opponentName = opponentName;
		}
	}
	
	/**
	 * Initialize the chat connection object. If the connection attempt fails, the program will exit
	 * @author nedmurp
	 * @param connectionPort int : the port on which to connect the chat. if an invalid port is specified, the program will terminate.
	 * @modifies the chat server socket local to this class
	 */
	public void StartChatServer(int connectionPort)
	{		
		try
		{
			System.out.println("Starting chat server on port " + connectionPort);
//			this.printChatMessage(ChatSource.Game, "Starting chat server on port " + connectionPort);
			this.chatServerSocket = new ServerSocket(connectionPort);
			System.out.println("Accepting chat connections...");
//			this.printChatMessage(ChatSource.Game, "Accepting chat connections...");
			this.chatSocket = this.chatServerSocket.accept();
			this.showChatGUI();
			this.printChatMessage(ChatSource.Game, "Chat initiated!");
		}
		catch(IOException ioEx)
		{
//			System.err.println("Caught IOException while trying to StartChatServer.\n" +
//					"Error: Failed to connect on port " + connectionPort + ".\nException: " + ioEx.getMessage());
			System.exit(1);			
		}
		
		//Set up reader and writer
		this.Initialize();
	}
	
	/**
	 * Starts the chat connection as the client
	 * @author nedmurp
	 * @param server String - the server to connect to as an IP address
	 * @param connectionPort int - the port to connect on
	 * @return true for a successful connection.
	 */
	public boolean StartChatClient(String server, int connectionPort)
	{
		Socket sock = null;
		try
		{
			sock = new Socket(server, connectionPort);
		}
		catch(IOException ioEx)
		{
//			System.err.println("Caught IOException while trying to StartChatClient.\n" + 
//					"Error: Failed to connect to server: " + server + " port: " + connectionPort + ".\nException: " + ioEx.getMessage());
		}
		return this.StartChatClient(sock);
	}
	
	/**
	 * Starts the chat connection as the client
	 * @author nedmurp 
	 * @param sock Socket - the socket on which to connect
	 * @return true for successful connection
	 */
	public boolean StartChatClient(Socket sock)
	{
		boolean connectSuccess = false;
		
		if(null != sock)
		{
//			System.out.println("Connecting to server: " + sock.getRemoteSocketAddress().toString() + " on port; " + sock.getPort());
//			this.printChatMessage(ChatSource.Game, "Attempting to connect to host " + sock.getRemoteSocketAddress().toString() + " on port " + sock.getPort());
			this.chatSocket = sock;
			connectSuccess = this.chatSocket.isConnected();
//			this.printChatMessage(ChatSource.Game, "Connection attempt " + (connectSuccess ? "succeeded" : "failed"));
		}
		
		//Set up Reader and Writer
		this.Initialize();
		
		if(connectSuccess)
		{
			this.showChatGUI();
		}
		
		return connectSuccess;
	}
	
	/**
	 * Sets a flag to terminate the chat connection
	 */
	public void StopChat()
	{
		//Set flag and let thread exit
		this.stopChatFlag = true;
		if(this.readerThread != null && this.readerThread.isAlive())
		{
			this.readerThread.interrupt();
		}
	}
	
	/**
	 * Writes a message to the connected socket's PrintWriter 
	 * @author nedmurp
	 * @param message String : the message to write
	 */
	public void SendChatMessage(String message)
	{		
		//Send message over the wire
		this.chatWriter.println(message);
		//Print it in the chat window
		this.printChatMessage(ChatSource.Player, message);
	}
	
	/**
	 * Method to dispose of the resources used by this class. Call this method during clean up.
	 * @author nedmurp
	 * @modifies the following objects local to this class: the chat writer, the chat reader, the chat socket, the chat server socket
	 */
	public void Dispose()
	{
//		System.out.println("Disposing chat...");
		//Set stop chat flag so threads exit smoothly
		this.StopChat();
		
		if(this.chatWriter != null)
		{
			this.chatWriter.flush();
			this.chatWriter.close();
			this.chatWriter = null;
		}
		
		try
		{			
			if(this.readerThread != null && this.readerThread.isAlive())
			{
				this.readerThread.interrupt();
				this.readerThread = null;
			}
			
			if(this.chatReader != null)
			{
				this.chatReader.close();
				this.chatReader = null;
			}
			
			if(this.chatSocket != null)
			{
				this.chatSocket.close();
				this.chatSocket = null;
			}
			
			if(this.chatServerSocket != null)
			{
				this.chatServerSocket.close();
				this.chatServerSocket = null;
			}			
		}
		catch(IOException e)
		{
//			System.err.println("Caught IOException while trying to Dispose.\nException: " + e.getMessage());
		}
	}
	
	/**
	 * Initializes the chat connection. If the connection fails, the program will exit
	 * @author nedmurp
	 * @modifies the following resources local to this class are modified: the chat reader, the chat writer, the chat socket
	 */
	private void Initialize()
	{
		this.stopChatFlag = false;
		try
		{
			this.chatReader = new BufferedReader(new InputStreamReader(this.chatSocket.getInputStream()));
			this.chatWriter = new PrintWriter(this.chatSocket.getOutputStream(), true);
				
			readerThread = new Thread(this, "ChatReader");				
			readerThread.start();			
		}
		catch (IOException e) 
		{
			System.err.println("Chat Accept failed. Exiting...");
			System.exit(1);
		}		
	}
	
	/**
	 * Waits for the chat messages to be received then prints them to the output
	 * @author nedmurp
	 */
	private void WaitForChatMessage()
	{		
		String line = null;
		
		try
		{
			do
			{
				line = this.chatReader.readLine();

				//Print the line to the chat window
				this.printChatMessage(ChatSource.Opponent, line);
				
			}while((null != line) && (!this.stopChatFlag));
		}
		catch(IOException ex)
		{
//			System.err.println("Caught IOException while trying to WaitForChatMessage.\nException: " + ex.getMessage());
		}
		catch(NullPointerException npEx)
		{
			//Chat was closed. No op
		}
	}

	@Override
	public void run() 
	{
		this.WaitForChatMessage();
//		System.out.println("Cleaning up chat thread...");
		this.Dispose();
//		System.out.println("Terminating chat thread...");
	}
	
//	public static void main ( String[] args )
//	{
//		Chat myChat = new Chat();
//		myChat.StartChatServer(4444);	
//	}
}
