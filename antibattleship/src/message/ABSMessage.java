package message;


/**
 * 
 * 	
 * @author JVen, nedmurp
 *
 */

public abstract class ABSMessage
{	
	/**
	 * Message Factory
	 * @author nedmurp
	 * @param rawMessage String : the raw input that will be converted to a message object
	 * @return a subclass of the abstract ABSMessage
	 * @throws InvalidMessageException if the input was not valid
	 */
	public static ABSMessage CreateMessage(String rawMessage) throws InvalidMessageException
	{
		if(null == rawMessage)
		{
			throw new InvalidMessageException("The message was null.");
		}
		
		rawMessage = rawMessage.toLowerCase();
		String[] splitRawMessage = rawMessage.split(" ");
		ABSMessage returnMessage = null;
		
		String messageID = splitRawMessage[0];
		try
		{
			if ( messageID.equals("syntax-error") )
			{
				returnMessage = new ABSSyntaxErrorMessage(rawMessage);
			}
			else if ( messageID.equals("game-error") )
			{
				returnMessage = new ABSGameErrorMessage(rawMessage);
			}
			else if ( messageID.equals("init-game") )
			{
				returnMessage = new ABSInitGameMessage(rawMessage);
			}
			else if( messageID.equals("accept-game") ) 
			{
				returnMessage = new ABSGameAcceptMessage();
			}
			else if( messageID.equals("deny-game") )
			{
				returnMessage = new ABSGameDenyMessage();
			}
			else if( messageID.equals("board-hash") )
			{
				returnMessage = new ABSBoardHashMessage(rawMessage);
			}
			else if( messageID.equals("target") )
			{
				returnMessage = new ABSTargetMessage(rawMessage);
			}
			else if( messageID.equals("results") )
			{
				returnMessage = new ABSResultsMessage(rawMessage);
			}
			else if( messageID.equals("victory") )
			{
				returnMessage = new ABSVictoryMessage(rawMessage);
			}
			else if( messageID.equals("accept-victory") )
			{
				returnMessage = new ABSAcceptVictoryMessage();
			}
			else if( messageID.equals("reject-victory") )
			{
				returnMessage = new ABSRejectVictoryMessage();
			}
			else
			{
				//System.out.println("The message format was not recognized. Prefix chunk was \"" + splitRawMessage[0] + "\"");
				throw new InvalidMessageException("The message format was not recognized. Prefix chunk was \"" + splitRawMessage[0] + "\"");
			}
		}
		catch(IllegalArgumentException e)
		{
			// TODO players need to reply back with syntax error
			//Syntax error: return null
			returnMessage = null;
		}
		
		return returnMessage;
	}
	
	public abstract MsgType getMessageType();
	
}