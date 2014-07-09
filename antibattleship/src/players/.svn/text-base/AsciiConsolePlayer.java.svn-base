package players;

import java.io.*;
import java.util.*;

import message.ABSBoardHashMessage;
import message.ABSGameErrorMessage;
import message.ABSInitGameMessage;
import message.ABSMessage;
import message.ABSResultsMessage;
import message.ABSSyntaxErrorMessage;
import message.ABSTargetMessage;
import message.ABSVictoryMessage;
import message.ResultType;
import client.*;

/**
 * 
 * Wraps console with human input into our ABSPlayer interface
 * Cleaner, more forgiving prompt than HumanConsolePlayer with ascii art UI :D
 * 
 * @author JVen
 *
 */

public class AsciiConsolePlayer implements ABSPlayer
{

	private enum GameStage
	{
		INIT_GAME,
		EXCHANGE_HASH,
		MY_TURN,
		OPP_TURN,
		DECLARE_VICTORY,
		THROW_GAME_ERROR,
		THROW_SYNTAX_ERROR,
		DECIDE_VICTORY
	}
	
	private static final int salt = 6005;
	
	private GameStage stage;
	private final BufferedReader reader;
	private int numRows;
	private int numCols;
	private List<Integer> shipSizeList;
	private List<ABSShip> ships;
	private ABSAttackBoard attackBoard;
	private ABSMarkBoard markBoard;
	
	private List<Integer> lastPosition;
	private ResultType lastOutcome;
	
	private boolean validInput;
	
	public AsciiConsolePlayer()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		stage = GameStage.INIT_GAME;
	}
	
	@Override
	public String getName()
	{
		return "AsciiConsolePlayer";
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
				if ( stage == GameStage.INIT_GAME )
					stage = GameStage.EXCHANGE_HASH;
				break;
				
			case GAME_DENY:
				System.out.println("Opponent denied your game request.");
				break;
				
			case BOARD_HASH:
				System.out.println("Opponent sent his board hash: " + ((ABSBoardHashMessage)m).getBoardHash());
				if ( stage == GameStage.EXCHANGE_HASH )
					stage = GameStage.MY_TURN;
				break;
				
			case TARGET:
				int row = ((ABSTargetMessage)m).getSingleTarget().getRow();
				int col = ((ABSTargetMessage)m).getSingleTarget().getColumn();
				System.out.println("Opponent attacked (" + row + ", " + col + ").");
				lastOutcome = attackBoard.attack(row, col);
				break;
				
			case RESULTS:
				switch ( ((ABSResultsMessage)m).getOutcome() )
				{
					case MISS:
						System.out.println("You missed.");
						stage = GameStage.OPP_TURN;
						break;
					case HIT:
						System.out.println("You hit one of the opponent's ships!");
						stage = GameStage.MY_TURN;
						break;
					case SUNK:
						System.out.println("You sunk one of the opponent's ships!");
						stage = GameStage.MY_TURN;
						break;
				}
				markBoard.mark(lastPosition.get(0), lastPosition.get(1), ((ABSResultsMessage)m).getOutcome());
				break;
				
			case VICTORY:
				System.out.println("Opponent declared victory.");
				stage = GameStage.DECIDE_VICTORY;
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
		
		switch ( stage )
		{
		
			case INIT_GAME:
				
				System.out.println("Initializing game...");
				
				// get dimensions
				
				System.out.println("Input desired board dimensions. (e.g. '10 9' for a 10x9 board.)");
				validInput = false;
				while ( !validInput )
				{
					try
					{
						validInput = true;
						System.out.print(">> ");
						String rawDims = reader.readLine();
						String[] dimToks = rawDims.split(" ");
						if ( dimToks.length != 2 )
							throw new Exception();
						numRows = Integer.parseInt(dimToks[0]);
						numCols = Integer.parseInt(dimToks[1]);
						if ( numRows <= 0 || numCols <= 0 || ( numRows == 1 && numCols == 1 ) )
							throw new Exception();
					}
					catch ( Exception e )
					{
						validInput = false;
						System.out.println("Invalid board dimensions.");
					}
				}
				
				// get ship size list
				
				System.out.println("Input ship size list. (e.g. '1 1 2 3' for four ships with sizes [1,1,2,3].)");
				validInput = false;
				while ( !validInput )
				{
					try
					{
						validInput = true;
						System.out.print(">> ");
						String rawShipSizeList = reader.readLine();
						String[] shipSizeToks = rawShipSizeList.split(" ");
						shipSizeList = new ArrayList<Integer>();
						for ( String rawSize : shipSizeToks )
							shipSizeList.add(Integer.parseInt(rawSize));
						if ( !isValidShipSizeList(numRows, numCols, shipSizeList) )
							throw new Exception();
					}
					catch ( Exception e )
					{
						validInput = false;
						System.out.println("Invalid ship size list.");
					}
				}
				
				msg = new ABSInitGameMessage(numRows, numCols, shipSizeList);			
				
				break;
				
			case EXCHANGE_HASH:
				
				System.out.println("Setting up board...");
				
				List<Integer> shipsToPlace = new ArrayList<Integer>(shipSizeList);
				ships = new ArrayList<ABSShip>();
				attackBoard = new ABSAttackBoard(numRows, numCols, ships);
				markBoard = new ABSMarkBoard(numRows, numCols);
				
				while ( !shipsToPlace.isEmpty() )
				{
					
					System.out.println(attackBoard.toString());
					
					// get ship to remove
					
					System.out.print("Choose a ship to place:");
					for ( Integer size : shipsToPlace )
						System.out.print(" " + size);
					System.out.println("");
					
					int size = -1;
					
					validInput = false;
					while ( !validInput )
					{
						try
						{
							validInput = true;
							System.out.print(">> ");
							String sizeRaw = reader.readLine();
							size = Integer.parseInt(sizeRaw);
							if ( !shipsToPlace.contains(size) )
								throw new Exception();
						}
						catch ( Exception e )
						{
							validInput = false;
							System.out.println("Invalid ship size.");
						}
					}
					
					// get location/orientation
					
					System.out.println("Choose the location and orientation of the ship. (e.g. '2 3 h' to put the ship's top/leftmost square at row 2, column 3, horizontally.");
					
					validInput = false;
					while ( !validInput )
					{
						try
						{
							validInput = true;
							System.out.print(">> ");
							String locOriRaw = reader.readLine();
							String[] locOriToks = locOriRaw.split(" ");
							if ( locOriToks.length != 3 )
								throw new Exception();
							int row = Integer.parseInt(locOriToks[0]);
							int col = Integer.parseInt(locOriToks[1]);
							ABSShip.Orientation ori;
							if ( locOriToks[2].toLowerCase().startsWith("h") )
								ori = ABSShip.Orientation.HORIZONTAL;
							else if ( locOriToks[2].toLowerCase().startsWith("v") )
								ori = ABSShip.Orientation.VERTICAL;
							else
								throw new Exception();
							if ( !isValidShip(attackBoard, row, col, size, ori) )
								throw new Exception();
							ships.add(new ABSShip(row, col, size, ori));
							shipsToPlace.remove(new Integer(size));
						}
						catch ( Exception e )
						{
							validInput = false;
							System.out.println("Invalid ship location/orientation.");
						}
					}
					
					// generate new board before going back up
					
					attackBoard = new ABSAttackBoard(numRows, numCols, ships);
					
				}
				
				System.out.println(attackBoard.toString());
				String boardHash = ABSBoardHash.computeBoardHash(attackBoard.getBoardRep(), numRows, numCols, salt);
				System.out.println("Sending board hash: " + boardHash);
				
				msg = new ABSBoardHashMessage();
				((ABSBoardHashMessage)msg).setBoardHash(boardHash);
				
				break;
				
			case MY_TURN:
				
				System.out.println(attackBoard);
				System.out.println(markBoard);
				System.out.println("Choose a square to attack. (e.g. '3 4' for row 3, column 4.)");
				
				// get target
				
				validInput = false;
				int row = -1;
				int col = -1;
				while ( !validInput )
				{
					try
					{
						validInput = true;
						System.out.print(">> ");
						String targetRaw = reader.readLine();
						String[] targetToks = targetRaw.split(" ");
						if ( targetToks.length != 2 )
							throw new Exception();
						row = Integer.parseInt(targetToks[0]);
						col = Integer.parseInt(targetToks[1]);
						if ( row < 0 || col < 0 || row >= numRows || col >= numCols )
							throw new Exception();
					}
					catch ( Exception e )
					{
						validInput = false;
						System.out.println("Invalid target.");
					}
					if ( validInput && markBoard.wasTried(row, col) )
					{
						validInput = false;
						System.out.println("You've already attacked that square... continue? (y/n)");
						System.out.print(">> ");
						try
						{
							String yn = reader.readLine();
							if ( yn.toLowerCase().startsWith("y") )
								validInput = true;
						}
						catch ( Exception e )
						{
							
						}
					}
				}
				
				lastPosition = new ArrayList<Integer>();
				lastPosition.add(row);
				lastPosition.add(col);
				
				msg = new ABSTargetMessage();				
				((ABSTargetMessage)msg).setSingleTarget(new ABSBoardSquare(row, col));
				break;
				
			case OPP_TURN:
				
				if ( lastOutcome == ResultType.MISS )
				{
					System.out.println("Opponent missed.");
					if ( attackBoard.getNumUnsunkShips() == 0 && lastOutcome == ResultType.MISS )
						stage = GameStage.DECLARE_VICTORY;
					else
						stage = GameStage.MY_TURN;
				}
				else if ( lastOutcome == ResultType.HIT )
					System.out.println("Opponent hit one of your ships!");
				else if ( lastOutcome == ResultType.SUNK )
					System.out.println("Opponent sunk one of your ships!");
				msg = new ABSResultsMessage();
				((ABSResultsMessage)msg).setSingleOutcome(lastOutcome);
				break;
				
			case DECLARE_VICTORY:
				
				System.out.println("All of our ships are sunk! Declaring victory...");
				msg = new ABSVictoryMessage(salt, attackBoard.getBoardRep());				
				break;
				
			case THROW_GAME_ERROR:
				
				System.out.println("Throwing game error...");
				msg = new ABSGameErrorMessage();
				((ABSGameErrorMessage)msg).setErrorMessage("");
				break;
				
			case THROW_SYNTAX_ERROR:
				
				System.out.println("Throwing syntax error...");
				msg = new ABSSyntaxErrorMessage();
				((ABSSyntaxErrorMessage)msg).setErrorMessage("");
				break;
				
		}
		
		return msg;
		
	}

	private boolean isValidShipSizeList ( int numRows, int numCols, List<Integer> shipSizeList )
	{
		if ( numRows < 2 * shipSizeList.size() && numCols < 2 * shipSizeList.size() )
			return false;
		for ( Integer size : shipSizeList )
		{
			if ( size > numRows || size > numCols )
				return false;
		}
		return true;
	}
	
	private boolean isValidShip ( ABSAttackBoard board, int row, int col, int size, ABSShip.Orientation ori )
	{
		// TODO add constraint that ships can't be "bunched up"
		if ( row < 0 || row >= numRows || col < 0 || col >= numCols )
			return false;
		if ( ori == ABSShip.Orientation.HORIZONTAL )
		{
			if ( col + size > numCols )
				return false;
			for ( int j = col ; j < col + size ; j++ )
				if ( board.isOccupied(row, j) )
					return false;
		}
		else
		{
			if ( row + size > numRows )
				return false;
			for ( int i = row ; i < row + size ; i++ )
				if ( board.isOccupied(i, col) )
					return false;
		}
		return true;
	}
	
}
