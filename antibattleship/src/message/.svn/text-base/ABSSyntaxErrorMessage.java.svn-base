package message;


public class ABSSyntaxErrorMessage extends ABSMessage
{
	private String errorMessage;
	
	public ABSSyntaxErrorMessage()
	{
		this.errorMessage = "";
	}
	
	public ABSSyntaxErrorMessage(String rawMessage)
	{
		this();
		String[] splitMsg = rawMessage.split(" ");
		
		if ( splitMsg.length > 1 )
			this.errorMessage = splitMsg[1];
	}

	@Override
	public MsgType getMessageType()
	{
		return MsgType.SYNTAX_ERROR;
	}
	
	@Override
	public String toString()
	{
		return "syntax-error " + this.errorMessage;
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
