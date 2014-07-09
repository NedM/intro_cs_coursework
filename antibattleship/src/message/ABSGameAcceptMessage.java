package message;


public class ABSGameAcceptMessage extends ABSMessage
{
	public ABSGameAcceptMessage()
	{
		
	}
	
	@Override
	public MsgType getMessageType()
	{
		return MsgType.GAME_ACCEPT;
	}
	
	@Override
	public String toString()
	{
		return "accept-game";
	}
}
