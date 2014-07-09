package players;

import java.io.*;

import message.ABSBoardHashMessage;
import message.ABSGameErrorMessage;
import message.ABSInitGameMessage;
import message.ABSMessage;
import message.ABSResultsMessage;
import message.ABSSyntaxErrorMessage;
import message.ABSTargetMessage;
import message.InvalidMessageException;
import client.*;

/**
 * 
 * Wraps console with human input into our ABSPlayer interface
 * Strings are casted raw to ABSMessages... exceptions galore!
 * 
 * @author JVen
 *
 */

public class HumanConsolePlayer implements ABSPlayer
{
	
	private final BufferedReader reader;
	
	public HumanConsolePlayer()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	@Override
	public String getName()
	{
		return "HumanConsolePlayer";
	}
	
	@Override
	public void sendMsgToPlayer ( ABSMessage m )
	{
		// TODO @requires != null
		switch ( m.getMessageType() )
		{
			case SYNTAX_ERROR:
				System.out.println("Opponent raised a syntax error: \"" + ((ABSSyntaxErrorMessage)m).getErrorMessage() + "\".");
				break;
				
			case GAME_ERROR:
				System.out.println("Opponent raised a game error: \"" + ((ABSGameErrorMessage)m).getErrorMessage() + "\".");
				break;
				
			case INIT_GAME:
				System.out.print("Opponent wants to play on a " + ((ABSInitGameMessage)m).getNumRows() + "x" + ((ABSInitGameMessage)m).getNumColumns() + " board with ship sizes [");
				for ( int i = 0 ; i < ((ABSInitGameMessage)m).getShipSizeList().size() ; i++ )
				{
					System.out.print(((ABSInitGameMessage)m).getShipSizeList().get(i));
					if ( i < ((ABSInitGameMessage)m).getShipSizeList().size() - 1 )
						System.out.print(", ");
				}
				System.out.println("].");
				break;
				
			case GAME_ACCEPT:
				System.out.println("Opponent accepted your game request.");
				break;
				
			case GAME_DENY:
				System.out.println("Opponent denied your game request.");
				break;
				
			case BOARD_HASH:
				System.out.println("Opponent sent his board hash: " + ((ABSBoardHashMessage)m).getBoardHash());
				break;
				
			case TARGET:
				System.out.println("Opponent attacked (" + ((ABSTargetMessage)m).getListOfTargets().get(0).getRow() + ", " + ((ABSTargetMessage)m).getListOfTargets().get(0).getColumn() + ").");
				break;
				
			case RESULTS:
				switch ( ((ABSResultsMessage)m).getOutcome() )
				{
					case MISS:
						System.out.println("You missed.");
						break;
					case HIT:
						System.out.println("You hit one of the opponent's ships!");
						break;
					case SUNK:
						System.out.println("You sunk one of the opponent's ships!");
						break;
				}
				break;
				
			case VICTORY:
				System.out.println("Opponent declared victory.");
				break;
				
			case ACCEPT_VICTORY:
				System.out.println("Opponent accepted your victory.");
				break;
				
			case REJECT_VICTORY:
				System.out.println("Opponent rejected your victory.");
				break;
		}
	}

	@Override
	public ABSMessage getMsgFromPlayer()
	{
		
		ABSMessage msg = null;
		
		while ( msg == null || msg.getMessageType() == null )
		{
			try
			{
				System.out.print(">> ");
				String rawMsg = reader.readLine();
				msg = ABSMessage.CreateMessage(rawMsg);
			}
			catch ( InvalidMessageException e )
			{
				msg = null;
				System.out.println("Invalid message.");
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		return msg;
		
	}

}
