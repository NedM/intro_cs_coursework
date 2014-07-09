package message;


public class ABSBoardHashMessage extends ABSMessage
{
	private String boardHash;
	
	public ABSBoardHashMessage()
	{
		this.boardHash = "";
	}
	
	public ABSBoardHashMessage(String rawMessage)
	{
		this();
		
		String[] splitRawMessage = rawMessage.split(" ");
		
		if(splitRawMessage.length < 2)
		{
			throw new IllegalArgumentException("Expected Board Hash message to have 2 space separated chunks but found " + splitRawMessage.length);
		}
		
		this.boardHash = splitRawMessage[1];				
	}
	
	@Override
	public MsgType getMessageType()
	{
		return MsgType.BOARD_HASH;
	}
	
	@Override
	public String toString()
	{
		return "board-hash " + this.boardHash;
	}
	
	public void setBoardHash(String boardHash)
	{
		this.boardHash = boardHash;
	}
	
	public String getBoardHash()
	{
		return this.boardHash;
	}
}
