package message;


public class ABSGameDenyMessage extends ABSMessage
{
	public ABSGameDenyMessage()
	{
		
	}
	
	@Override
	public MsgType getMessageType()
	{
		return MsgType.GAME_DENY;
	}
	
	@Override
	public String toString()
	{
		return "deny-game";
	}

}
