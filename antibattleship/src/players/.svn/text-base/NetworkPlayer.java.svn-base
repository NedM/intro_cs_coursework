package players;

import message.ABSMessage;
import message.InvalidMessageException;
import network.ABSConnection;
import network.ABSConnection.Timeout;
import network.Chat;

public class NetworkPlayer implements ABSPlayer, Runnable
{
	
	public enum ChatOption
	{
		ENABLE,
		DISABLE
	}
	
	private final ChatOption chatOption;
	private final ABSConnection netConnection;
	private final Chat playerChat;
	private String hostName;
	private int portNumber;
	private boolean isHost;
		
	public NetworkPlayer ( ChatOption chatOption, Timeout timeout )
	{
		this.chatOption = chatOption;
		this.netConnection = new ABSConnection(timeout);
		if ( chatOption == ChatOption.ENABLE )
			this.playerChat = new Chat();
		else
			this.playerChat = null;
	}
	
	public NetworkPlayer()
	{
		this(ChatOption.ENABLE, Timeout.STANDARD);
	}
	
	public boolean HostGame(int portNumber)
	{
		this.isHost = true;
		this.portNumber = portNumber;		
		boolean connected = netConnection.StartABSConnectionServer(portNumber);
		if ( connected && chatOption == ChatOption.ENABLE )
		{
			Thread chatSetupThread = new Thread(this, "ChatHostSetupThread");
			chatSetupThread.start();
		}
		return connected;
	}
	
	public boolean ConnectAsClient(String hostName, int hostPortNum)
	{
		this.hostName = hostName;
		this.isHost = false;
		this.portNumber = hostPortNum;
		if ( chatOption == ChatOption.ENABLE )
		{
			Thread chatSetupThread = new Thread(this, "ChatClientSetupThread");
			chatSetupThread.start();
		}
		return netConnection.StartABSConnectionClient(hostName, hostPortNum); 				
	}

	@Override
	public String getName()
	{
		return "NetworkPlayer";
	}

	@Override
	public void sendMsgToPlayer(ABSMessage m) 
	{
		String ans = m.toString();
		netConnection.SendGameMessage(ans);
	}

	@Override
	public ABSMessage getMsgFromPlayer() throws InvalidMessageException
	{
		ABSMessage rMessage = null;
		rMessage = ABSMessage.CreateMessage(netConnection.WaitForResponse());
		return rMessage;
	}
	
	public void cleanUpNetworkPlayer()
	{
		this.netConnection.Dispose();
	}

	@Override
	public void run() 
	{
		if ( chatOption == ChatOption.ENABLE )
		{
			if(this.isHost)
				playerChat.StartChatServer(this.portNumber + 1);
			else
				playerChat.StartChatClient(this.hostName, this.portNumber + 1);
		}
	}

}
