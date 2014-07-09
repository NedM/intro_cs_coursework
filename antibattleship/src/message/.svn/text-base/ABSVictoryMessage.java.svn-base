package message;


public class ABSVictoryMessage extends ABSMessage 
{
	private int salt;
	private String boardState;
	
	public ABSVictoryMessage()
	{
		
	}
	
	public ABSVictoryMessage(String rawMessage)
	{
		this();
		String[] splitRawMessage = rawMessage.split(" ");
		
		if ( splitRawMessage.length != 3 )
		{
			throw new IllegalArgumentException("Expected 3 space separated message blobs but instead found " + splitRawMessage.length);
		}
		
		try
		{
			this.salt = Integer.parseInt(splitRawMessage[1]);
		}
		catch(NumberFormatException nfEx)
		{
			throw new IllegalArgumentException(splitRawMessage[1] + " is not a valid integer!", nfEx);
		}
		
		this.boardState = splitRawMessage[2];
	}
	
	public ABSVictoryMessage(int salt, String boardState)
	{
		this();
		this.salt = salt;
		this.boardState = boardState;
	}
	
	@Override
	public MsgType getMessageType()
	{
		return MsgType.VICTORY;
	}
	
	@Override
	public String toString()
	{
		return "victory " + this.salt + " " + this.boardState;
	}
	
	public void setSalt(int salt)
	{
		this.salt = salt;
	}
	
	public void setBoardState(String boardState)
	{
		this.boardState = boardState;
	}
	
	public int getSalt()
	{
		return this.salt;
	}
	
	public String getBoardState()
	{
		return this.boardState;
	}

}
