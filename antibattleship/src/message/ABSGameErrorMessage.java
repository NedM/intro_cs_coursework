package message;


public class ABSGameErrorMessage extends ABSMessage
{
	private String errorMessage;
	
	public ABSGameErrorMessage()
	{
		this.errorMessage = "";
	}
	
	public ABSGameErrorMessage(String rawMessage)
	{
		this();
		String[] splitMessage = rawMessage.split(" ");
		
		if ( splitMessage.length > 1 )
			this.errorMessage = splitMessage[1];
	}

	@Override
	public MsgType getMessageType() 
	{
		return MsgType.GAME_ERROR;
	}
	
	@Override
	public String toString()
	{
		return "game-error " + this.errorMessage;
	}
	
	public void setErrorMessage(String errMessage)
	{
		this.errorMessage = errMessage;
	}
	
	public String getErrorMessage()
	{
		return this.errorMessage;
	}
}
