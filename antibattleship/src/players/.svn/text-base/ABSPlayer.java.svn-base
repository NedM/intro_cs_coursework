package players;

import message.ABSMessage;
import message.InvalidMessageException;

/**
 * 
 * Represents a player of the Antibattleship game
 * 
 * @author JVen
 *
 */

public interface ABSPlayer
{

	/**
	 * 
	 * Return the name of the player
	 * 
	 * @author JVen
	 * @return the name of the player
	 * 
	 */
	
	public String getName();
	
	/**
	 * 
	 * Send a message to the player
	 * 
	 * @author JVen
	 * @param m the message to send to the player
	 * 
	 */
	
	public void sendMsgToPlayer ( ABSMessage m );
	
	/**
	 * 
	 * Get a message from the player
	 * 
	 * @author JVen
	 * @return the message sent from the player
	 * @throws InvalidMessageException 
	 * 
	 */
	
	public ABSMessage getMsgFromPlayer() throws InvalidMessageException;
	
	
}
