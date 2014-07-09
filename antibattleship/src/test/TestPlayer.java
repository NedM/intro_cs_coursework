package test;

import players.ABSPlayer;
import message.*;

public class TestPlayer implements ABSPlayer 
{
	private ABSMessage storedMessage;
	
	public TestPlayer()
	{
		this.storedMessage = null;
	}
	
	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return "TestPlayer";
	}

	@Override
	public void sendMsgToPlayer(ABSMessage m)
	{
		System.out.println("Sent message " + m.toString());
		this.storedMessage = m;
	}

	@Override
	public ABSMessage getMsgFromPlayer() throws InvalidMessageException 
	{
		ABSMessage rMsg = null;
		switch(this.storedMessage.getMessageType())
		{
			case INIT_GAME:
				rMsg = new ABSGameAcceptMessage();
				break;
			default:
				rMsg = new ABSSyntaxErrorMessage();
				break;
		}
		System.out.println("Got message: " + rMsg.toString());
		return rMsg;
	}

}
