package client;

import java.io.*;
import java.util.*;
import java.text.*;

import players.ABSPlayer;

import message.*;

/**
 * 
 * The main client class: handles message handling between players
 * 
 * @author JVen
 *
 */

public class ABSGame
{

	public enum LogOption
	{
		ENABLE,
		DISABLE
	}
	
	public enum GameResult
	{
		A_VICTORY,
		B_VICTORY,
		A_THREW_ERROR,
		B_THREW_ERROR,
		NOT_INITIATED,
		DONT_KNOW
	}
	
	private enum Turn
	{
		A,
		B,
		GAME_OVER
	}
	
	private final ABSPlayer A;
	private final ABSPlayer B;
	private boolean writeToLog;
	private final PrintStream printStream;
	
	private Turn nextTurn = Turn.A;
	private boolean isSalvo;
	private boolean gameStarted = false;
	
	private GameResult winner = GameResult.DONT_KNOW;
	
	/////////////////////////////////////////////////////////////////////////////
	//
	//
	
	private static final String jasperPath = "/home/jven/Documents/abs/logs/";
	private static final String jvenPath = "C:\\Users\\Justin\\Documents\\My Dropbox\\6.005\\antibattleship\\logs\\";
		
	// change this to change where the logs are written
	private final String path = jvenPath;
	
	//
	//
	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Constructor. Defaults to not create a log of the game.
	 * 
	 * @author JVen
	 * @param A the initiating player
	 * @param B the listening player
	 * 
	 */
	
	public ABSGame ( ABSPlayer A, ABSPlayer B )
	{
		this(A, B, false);
	}
	
	/**
	 * 
	 * Constructor
	 * 
	 * @author JVen
	 * @param A the initiating player
	 * @param B the listening player
	 * 
	 */
	
	public ABSGame ( ABSPlayer A, ABSPlayer B, boolean writeToLog )
	{
		this.A = A;
		this.B = B;
		this.writeToLog = writeToLog;
		
		if ( writeToLog )
		{
			// get date and time
			DateFormat dateFormat = new SimpleDateFormat("MMddyy-HHmmss");
			String filename = dateFormat.format(new Date());
			
			PrintStream ps = null;
			
			try
			{
				ps = new PrintStream(new FileOutputStream(path + filename + ".log"));
				ps.println("Initiating player A: " + A.getName());
				ps.println("Listening player B: " + B.getName());
				ps.println("");
			}
			catch ( Exception e )
			{
				ps = null;
				System.err.println("Unable to create log. File \"" + path + filename + ".log\" will not be written.");
				this.writeToLog = false;
			}
			finally
			{
				this.printStream = ps;
			}
			
		}
		else
			this.printStream = null;
	}
	
	public ABSGame ( ABSPlayer A, ABSPlayer B, LogOption logOption )
	{
		this(A, B, (logOption == LogOption.ENABLE));
	}
	
	public boolean isGameStarted()
	{
		return this.gameStarted;
	}
	
	/**
	 * 
	 * Starts the game... gogogo!
	 * 
	 * @author JVen
	 * 
	 */
	
	public GameResult play()
	{		
		if ( !initializeGame() )
			return GameResult.NOT_INITIATED;
		if ( !initializeBoards() )
			return GameResult.NOT_INITIATED;
		gameStarted = true;
		// wait a little bit
		try
		{
			Thread.sleep(100);
		}
		catch ( Exception e )
		{
		}
		while ( nextTurn != Turn.GAME_OVER )
		{
			if ( nextTurn == Turn.A )
				nextTurn = AattackB();
			else
				nextTurn = BattackA();
		}
		return winner;
	}
	
	/**
	 * 
	 * Have the players agree on board dimensions and ship sizes
	 * 
	 * @author JVen
	 * @return True if and only if a game was accepted
	 * 
	 */
	
	private boolean initializeGame()
	{
		
		// exchange initialization info between players
		
		ABSMessage initGame = null;
		ABSMessage initGameResponse = null;
		
		boolean retry = false;
		do
		{
			retry = false;
			try 
			{
				ABSMessage msg = A.getMsgFromPlayer();
				String ans = msg.toString();
				initGame = ABSMessage.CreateMessage(ans);
			} 
			catch (InvalidMessageException e1) 
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(initGame, A, B) )
		{
			return false;
		}
		
		B.sendMsgToPlayer(initGame);
		if ( writeToLog )
			printStream.println("A > " + initGame.toString());
		
		do
		{
			retry = false;		
			try 
			{
				initGameResponse = ABSMessage.CreateMessage(B.getMsgFromPlayer().toString());
			}
			catch (InvalidMessageException e) 
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(initGame, B, A) )
			return false;
		
		A.sendMsgToPlayer(initGameResponse);
		if ( writeToLog )
			printStream.println("B < " + initGameResponse.toString());
		
		if ( initGame.getMessageType() == MsgType.INIT_GAME )
			this.isSalvo = ((ABSInitGameMessage)initGame).getSalvo();
		
		return ( initGameResponse.getMessageType() == MsgType.GAME_ACCEPT );		
	}
	
	/**
	 * 
	 * Have the players exchange hash information
	 * 
	 * @author JVen
	 * @return True if and only if board hashes are exchanged successfully
	 * 
	 */
	
	private boolean initializeBoards()
	{
		
		// exchange board hashes between players
		
		boolean gotBoardA = false;
		boolean gotBoardB = false;
		
		ABSMessage boardA = null;
		
		boolean retry = false;
		do
		{
			retry = false;		
			try 
			{
				boardA = ABSMessage.CreateMessage(A.getMsgFromPlayer().toString());
			} 
			catch (InvalidMessageException e) 
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(boardA, A, B) )
			return false;
		
		B.sendMsgToPlayer(boardA);
		if ( writeToLog )
			printStream.println("A > " + boardA.toString());
		
		ABSMessage boardB = null;
		do
		{
			retry = false;
			try 
			{
				boardB = ABSMessage.CreateMessage(B.getMsgFromPlayer().toString());
			}
			catch (InvalidMessageException e) 
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(boardA, B, A) )
			return false;
		
		A.sendMsgToPlayer(boardB);
		if ( writeToLog )
			printStream.println("B < " + boardB.toString());
			
		if ( boardA.getMessageType() == MsgType.BOARD_HASH )
			gotBoardA = true;
		if ( boardB.getMessageType() == MsgType.BOARD_HASH )
			gotBoardB = true;
		
		return ( gotBoardA && gotBoardB );
		
	}
	
	/**
	 * 
	 * A's turn to attack B
	 * 
	 * @author JVen
	 * @return whose turn is it next
	 * 
	 */
	
	private Turn AattackB()
	{
		boolean retry;
		ABSMessage target = null;
		do
		{	
			retry = false;
			try 
			{
				target = A.getMsgFromPlayer();
			} 
			catch (InvalidMessageException e) 
			{
				//Try again
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(target, A, B) )
			return Turn.GAME_OVER;
		
		B.sendMsgToPlayer(target);
		if ( writeToLog )
			printStream.println("A > " + target.toString());
		
		ABSMessage result = null;
		do
		{
			retry = false;
			try
			{
				result = B.getMsgFromPlayer();
			}
			catch(InvalidMessageException e)
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(target, B, A) )
			return Turn.GAME_OVER;
		
		A.sendMsgToPlayer(result);
		if ( writeToLog )
			printStream.println("B < " + result.toString());		
		
		if ( target.getMessageType() == MsgType.VICTORY )
		{
			if ( result.getMessageType() == MsgType.ACCEPT_VICTORY )
				winner = GameResult.A_VICTORY;
			else if ( result.getMessageType() == MsgType.REJECT_VICTORY )
				winner = GameResult.B_THREW_ERROR;
			else
			{
				B.sendMsgToPlayer(new ABSGameErrorMessage());
				winner = GameResult.A_THREW_ERROR;
			}
			return Turn.GAME_OVER;
		}
		else
		{
			if ( result.getMessageType() != MsgType.RESULTS || ( !this.isSalvo && ((ABSResultsMessage)result).getOutcome() != ResultType.MISS ) )
				return Turn.A;
			else
				return Turn.B;
		}
		
	}
	
	/**
	 * 
	 * B's turn to attack A
	 * 
	 * @author JVen
	 * @return whose turn is it next
	 * 
	 */
	
	private Turn BattackA()
	{
		boolean retry = false;
		ABSMessage target = null;
		do
		{
			retry = false;
			try
			{
				target = B.getMsgFromPlayer();
			}
			catch(InvalidMessageException e)
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(target, B, A) )
			return Turn.GAME_OVER;
		
		A.sendMsgToPlayer(target);
		if ( writeToLog )
			printStream.println("B < " + target.toString());
		
		ABSMessage result = null;
		do
		{
			retry = false;
			try
			{
				result = A.getMsgFromPlayer();
			}
			catch(InvalidMessageException e)
			{
				retry = true;
			}
		}while(retry);
		
		// handle errors
		
		if ( handleError(target, A, B) )
			return Turn.GAME_OVER;
		
		B.sendMsgToPlayer(result);
		if ( writeToLog )
			printStream.println("A > " + result.toString());
		
		if ( target.getMessageType() == MsgType.VICTORY )
		{
			if ( result.getMessageType() == MsgType.ACCEPT_VICTORY )
				winner = GameResult.B_VICTORY;
			else if ( result.getMessageType() == MsgType.REJECT_VICTORY )
				winner = GameResult.A_THREW_ERROR;
			else
			{
				A.sendMsgToPlayer(new ABSGameErrorMessage());
				winner = GameResult.B_THREW_ERROR;
			}
			return Turn.GAME_OVER;
		}
		else
		{
			if ( result.getMessageType() != MsgType.RESULTS || ( !this.isSalvo && ((ABSResultsMessage)result).getOutcome() != ResultType.MISS ) )
				return Turn.B;
			else
				return Turn.A;
		}		
	}
	
	public Turn getNextTurn()
	{
		return this.nextTurn;
	}
	
	public boolean isSalvo()
	{
		return this.isSalvo;
	}
	
	private boolean handleError ( ABSMessage message, ABSPlayer playerWhoSent, ABSPlayer otherPlayer )
	{
		if ( message == null )
		{
			playerWhoSent.sendMsgToPlayer(new ABSSyntaxErrorMessage());
			otherPlayer.sendMsgToPlayer(new ABSAcceptVictoryMessage());
			nextTurn = Turn.GAME_OVER;
			if ( playerWhoSent == A )
				winner = GameResult.B_THREW_ERROR;
			else
				winner = GameResult.A_THREW_ERROR;
			return true;
		}
		else if ( message.getMessageType() == MsgType.SYNTAX_ERROR || message.getMessageType() == MsgType.GAME_ERROR )
		{
			otherPlayer.sendMsgToPlayer(message);
			nextTurn = Turn.GAME_OVER;
			if ( playerWhoSent == A )
				winner = GameResult.A_THREW_ERROR;
			else
				winner = GameResult.B_THREW_ERROR;
			return true;
		}
		else
			return false;
	}
}
