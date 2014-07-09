package players.jasper;

import java.util.*;

import players.ABSPlayer;
import message.*;
import client.*;

/**
 * 
 * JasperBot, plays Antibattleship
 * 
 * @author JVen
 * @see <a href="http://jasper.xvm.mit.edu/abs/">Jasper</a>
 *
 */

public class JasperBot implements ABSPlayer
{

	public enum MoveOrder
	{
		INITIATING,
		RECEIVING
	}
	
	public enum Verbosity
	{
		TOURNAMENT,
		DEBUG,
		OFF
	}
	
	public enum GameResult
	{
		WIN,
		LOSS,
		ERROR,
		DONT_KNOW
	}
	
	private enum GameVariant
	{
		NORMAL,
		SALVO
	}
	
	private enum GameStage
	{
		INIT_GAME,
		EXCHANGE_HASH,
		MY_TURN,
		OPP_TURN,
		DECLARE_VICTORY,
		DECIDE_VICTORY,
		GAME_ERROR,
		GG
	}
	
	private int tournamentDelay;
	
	private final boolean isInitiating;
	private final Verbosity verbosity;
	private final int salt;
	
	private GameVariant gameVariant;
	
	private GameStage stage;
	
	private int numRows;
	private int numCols;
	private List<Integer> shipSizeList;
	
	private String oppBoardRep;
	private int oppSalt;
	private String oppBoardHash;
	
	private List<ABSShip> ships;
	private ABSAttackBoard attackBoard;
	private JasperMarkBoard markBoard;
	
	private List<ABSBoardSquare> lastPositions;
	private List<ResultType> lastOutcomes;
	
	private int numMyHitsLeft;
	private int numOppHitsLeft;
	private int numOppShipsLeft;
	
	private GameResult gameResult = GameResult.DONT_KNOW;
	
	public JasperBot( boolean isInitiating, Verbosity v, int numRows, int numCols, List<Integer> shipSizeList, int tournamentDelay )
	{
		this.isInitiating = isInitiating;
		this.verbosity = v;
		this.salt = (int)(-2000000 - (Integer.MAX_VALUE - 2000001)* Math.random()); // :D
		this.stage = GameStage.INIT_GAME;
		this.numRows = numRows;
		this.numCols = numCols;
		if ( shipSizeList != null )
			this.shipSizeList = new ArrayList<Integer>(shipSizeList);
		else
			this.shipSizeList = null;
		this.tournamentDelay = tournamentDelay;
	}
	
	public JasperBot( MoveOrder moveOrder, Verbosity v, int numRows, int numCols, List<Integer> shipSizeList, int tournamentDelay )
	{
		this((moveOrder == MoveOrder.INITIATING), v, numRows, numCols, shipSizeList, tournamentDelay);
	}
	
	public JasperBot( boolean isInitiating, Verbosity v )
	{
		this(isInitiating, v, -1, -1, null, 10);
	}
	
	public JasperBot ( boolean isHost )
	{
		this(isHost, Verbosity.OFF, -1, -1, null, 10);
	}
	
	public ABSMarkBoard getMarkBoard()
	{
		return this.markBoard.getBoard();
	}
	
	public ABSAttackBoard getAttackBoard()
	{
		return this.attackBoard;
	}
	
	public int getNumOppHitsLeft()
	{
		return this.numOppHitsLeft;
	}
	
	public int getNumMyHitsLeft()
	{
		return this.numMyHitsLeft;
	}
	
	public GameResult getGameResult()
	{
		return this.gameResult;
	}
	
	/*
	 * 
	 * Methods for the tournament gui
	 * 
	 * -JVen
	 * 
	 */
	
	public boolean isBigShip()
	{
		for ( Integer size : shipSizeList )
			if ( size != 1 )
				return true;
		return false;
	}
	
	public int getProbLastShipSizeTwo()
	{
		
		if ( !isBigShip() )
			return -1;
		
		int twoSize = 0;
		int totSize = 0;
		
		for ( Integer size : shipSizeList )
		{
			if ( size != 1 )
			{
				if ( size == 2 )
					twoSize += size;
				totSize += size;
			}
		}
		
		return (int)(((twoSize + 0.0)/ totSize) * 100);
		
	}
	
	public List<ABSBoardSquare> getAvoidSet()
	{
		return this.markBoard.getAvoidSet();
	}
	
	public boolean isInitiating()
	{
		return this.isInitiating;
	}
	
	@Override
	public String getName()
	{
		return "JasperBot";
	}
	
	@Override
	public void sendMsgToPlayer ( ABSMessage m )
	{

		switch ( m.getMessageType() )
		{
		
			case SYNTAX_ERROR:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("Jasper: Opponent threw a syntax error.");
				stage = GameStage.GG;
				break;
				
			case GAME_ERROR:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("Jasper: Opponent threw a game error.");
				stage = GameStage.GG;
				break;
				
			case INIT_GAME:
				
				if ( stage == GameStage.INIT_GAME )
				{
					
					numRows = ((ABSInitGameMessage)m).getNumRows();
					numCols = ((ABSInitGameMessage)m).getNumColumns();
					shipSizeList = ((ABSInitGameMessage)m).getShipSizeList();
					
					// get the total number of hits
					
					for ( Integer size : shipSizeList )
					{
						numMyHitsLeft += size;
						numOppHitsLeft += size;
					}
					
					// get game variant
					
					if ( ((ABSInitGameMessage)m).getSalvo() )
					{
						if ( verbosity == Verbosity.DEBUG )
							System.out.println("Jasper: Playing salvo variant.");
						gameVariant = GameVariant.SALVO;
					}
					else
					{
						if ( verbosity == Verbosity.DEBUG )
							System.out.println("Jasper: Playing normal game.");
						gameVariant = GameVariant.NORMAL;
					}
					
					// set number of ships
					
					numOppShipsLeft = shipSizeList.size();
					
				}
				else
					stage = GameStage.GAME_ERROR;

				break;
				
			case GAME_ACCEPT:
				
				if ( stage == GameStage.INIT_GAME )
					stage = GameStage.EXCHANGE_HASH;
				else
					stage = GameStage.GAME_ERROR;
				
				break;
				
			case GAME_DENY:
				
				if ( stage == GameStage.INIT_GAME )
					stage = GameStage.GG;
				else
					stage = GameStage.GAME_ERROR;

				break;
				
			case BOARD_HASH:

				if ( stage == GameStage.EXCHANGE_HASH )
				{
					oppBoardHash = ((ABSBoardHashMessage)m).getBoardHash();
					if ( isInitiating )
						stage = GameStage.MY_TURN;
				}
				else
					stage = GameStage.GAME_ERROR;

				break;
				
			case TARGET:

				if ( stage == GameStage.OPP_TURN )
				{
					
					lastOutcomes = new ArrayList<ResultType>();
							
					// make sure we have the number of ships we're expecting
					
					if ( ( gameVariant == GameVariant.SALVO && ((ABSTargetMessage)m).getListOfTargets().size() != numOppShipsLeft )
							|| ( gameVariant == GameVariant.NORMAL && ((ABSTargetMessage)m).getListOfTargets().size() != 1 ) )
						stage = GameStage.GAME_ERROR;
					
					for ( ABSBoardSquare position : ((ABSTargetMessage)m).getListOfTargets() )
					{
						if ( !isValidLoc(position.getRow(), position.getColumn()) )
							stage = GameStage.GAME_ERROR;
						else
						{
							ResultType r = attackBoard.attack(position.getRow(), position.getColumn());
							if ( r != ResultType.MISS )
								numMyHitsLeft--;
							lastOutcomes.add(r);
						}
					}
					
				}
				else
					stage = GameStage.GAME_ERROR;
				
				break;
				
			case RESULTS:
				
				if ( stage == GameStage.MY_TURN )
				{
					
					// make sure we have the number of ships we're expecting
					
					if ( ( gameVariant == GameVariant.SALVO && ((ABSResultsMessage)m).getOutcomes().size() != attackBoard.getNumUnsunkShips() )
							|| ( gameVariant == GameVariant.NORMAL && ((ABSResultsMessage)m).getOutcomes().size() != 1 ) )
						stage = GameStage.GAME_ERROR;
					
					for ( int i = 0 ; i < ((ABSResultsMessage)m).getOutcomes().size() ; i++ )
					{
						
						ResultType r = ((ABSResultsMessage)m).getOutcomes().get(i);
						
						switch ( r )
						{
						
							case MISS:
								stage = GameStage.OPP_TURN;
								break;
								
							case HIT:
								numOppHitsLeft--;
								if ( gameVariant == GameVariant.NORMAL )
									stage = GameStage.MY_TURN;
								else // SALVO MODE
									stage = GameStage.OPP_TURN;
								break;
								
							case SUNK:
								numOppHitsLeft--;
								numOppShipsLeft--;
								if ( gameVariant == GameVariant.NORMAL )
									stage = GameStage.MY_TURN;
								else // SALVO MODE
									stage = GameStage.OPP_TURN;
								break;
								
						}
						
						markBoard.mark(lastPositions.get(i).getRow(), lastPositions.get(i).getColumn(), r);
						
					}
					
				}
				else
					stage = GameStage.GAME_ERROR;
				
				break;
				
			case VICTORY:

				if ( stage == GameStage.OPP_TURN )
				{
					oppBoardRep = ((ABSVictoryMessage)m).getBoardState();
					oppSalt = ((ABSVictoryMessage)m).getSalt();
					stage = GameStage.DECIDE_VICTORY;
				}
				else
					stage = GameStage.GAME_ERROR;
				
				break;
				
			case ACCEPT_VICTORY:

				if ( stage == GameStage.DECLARE_VICTORY )
				{
					if ( verbosity == Verbosity.DEBUG )
						System.out.println("Jasper: We won! :D");
					gameResult = GameResult.WIN;
					stage = GameStage.GG;
				}
				else
					stage = GameStage.GAME_ERROR;
					
				break;
				
			case REJECT_VICTORY:

				if ( stage == GameStage.DECLARE_VICTORY )
				{
					if ( verbosity == Verbosity.DEBUG )
						System.out.println("Jasper: Opponent rejected our victory. o.O");
					gameResult = GameResult.ERROR;
					stage = GameStage.GG;
				}
				else
					stage = GameStage.GAME_ERROR;
				
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
				
				if ( isInitiating )
				{
					
					gameVariant = GameVariant.NORMAL;
					
					if ( verbosity != Verbosity.TOURNAMENT )
					{
						numRows = 10;
						numCols = 8;
						
						shipSizeList = new ArrayList<Integer>();
						shipSizeList.add(3);
						shipSizeList.add(3);
						shipSizeList.add(4);
						shipSizeList.add(5);
					}
					
					msg = new ABSInitGameMessage(numRows, numCols, shipSizeList);
					
					// set total number of hits
					
					for ( Integer size : shipSizeList )
					{
						numMyHitsLeft += size;
						numOppHitsLeft += size;
					}
					
					// set number of ships
					
					numOppShipsLeft = shipSizeList.size();
					
				}
				else
				{
					if ( isValidGame(numRows, numCols, shipSizeList) )
					{
						if ( verbosity == Verbosity.TOURNAMENT && gameVariant == GameVariant.SALVO )
							msg = new ABSGameDenyMessage();
						else
							msg = new ABSGameAcceptMessage();
						stage = GameStage.EXCHANGE_HASH;
					}
					else
					{
						msg = new ABSGameDenyMessage();
						stage = GameStage.GG;
					}
				}
				
				break;
				
			case EXCHANGE_HASH:
				
				ships = new ArrayList<ABSShip>();
				
				// put the ships
					
				for ( int ship = 0 ; ship < shipSizeList.size(); ship++ )
				{
					// every other row/column
					
					if ( numRows > numCols )
						ships.add(new ABSShip(2 * ship, (int)((numCols - shipSizeList.get(ship) + 1) * Math.random()), shipSizeList.get(ship), ABSShip.Orientation.HORIZONTAL));
					else
						ships.add(new ABSShip((int)((numRows - shipSizeList.get(ship) + 1) * Math.random()), 2 * ship, shipSizeList.get(ship), ABSShip.Orientation.VERTICAL));
				}
				
				attackBoard = new ABSAttackBoard(numRows, numCols, ships);
				markBoard = new JasperMarkBoard(numRows, numCols);

				String boardHash = ABSBoardHash.computeBoardHash(attackBoard.getBoardRep(), numRows, numCols, salt);
				
				msg = new ABSBoardHashMessage();
				((ABSBoardHashMessage)msg).setBoardHash(boardHash);				
				
				if ( !isInitiating )
					stage = GameStage.OPP_TURN;
				
				break;
				
			case MY_TURN:
				
				// check if opponent has repeated an attack
				
				if ( attackBoard.repeatedAttack() )
				{
					stage = GameStage.GAME_ERROR;
					return getMsgFromPlayer();
				}

				lastPositions = new ArrayList<ABSBoardSquare>();
				
				switch ( gameVariant )
				{
				
					case NORMAL:
						lastPositions.add(markBoard.getTarget());
						break;
				
					case SALVO:
						for ( int i = 0 ; i < attackBoard.getNumUnsunkShips() ; i++ )
							lastPositions.add(markBoard.getTarget());
						break;
						
				}
				
				msg = new ABSTargetMessage();
				((ABSTargetMessage)msg).setTargets(lastPositions);				
				
				break;
				
			case OPP_TURN:
				
				switch ( gameVariant )
				{
					case NORMAL:
						
						if ( !lastOutcomes.contains(ResultType.MISS) )
							stage = GameStage.OPP_TURN;
						else if ( attackBoard.getNumUnsunkShips() == 0 )
							stage = GameStage.DECLARE_VICTORY;
						else
							stage = GameStage.MY_TURN;
						break;
						
					case SALVO:
						
						if ( attackBoard.getNumUnsunkShips() == 0 )
							stage = GameStage.DECLARE_VICTORY;
						else
							stage = GameStage.MY_TURN;
						break;
				}
				
				msg = new ABSResultsMessage();
				((ABSResultsMessage)msg).setOutcomes(lastOutcomes);
				
				break;
				
			case DECLARE_VICTORY:
				
				msg = new ABSVictoryMessage(salt, attackBoard.getBoardRep());				
				
				break;
				
			case DECIDE_VICTORY:
				
				String calcHash = ABSBoardHash.computeBoardHash(oppBoardRep, numRows, numCols, oppSalt);
				
				if ( this.numOppHitsLeft == 0 && calcHash.equals(oppBoardHash) )
				{
					if ( verbosity == Verbosity.DEBUG )
						System.out.println("Jasper: We lost. :'(");
					gameResult = GameResult.LOSS;
					msg = new ABSAcceptVictoryMessage();					
				}
				else
				{
					if ( verbosity == Verbosity.DEBUG )
						System.out.println("Jasper: Opponent declared victory incorrectly. :s");
					gameResult = GameResult.ERROR;
					msg = new ABSRejectVictoryMessage();					
				}
				
				break;
				
			case GAME_ERROR:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("Jasper: Opponent caused a game error. :[");
				gameResult = GameResult.ERROR;
				msg = new ABSGameErrorMessage();
				((ABSGameErrorMessage)msg).setErrorMessage("");				
				
				break;
				
			case GG:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("Jasper: GG NO RE");
				msg = new ABSGameErrorMessage();
				((ABSGameErrorMessage)msg).setErrorMessage("");		
				break;
				
		}
		
		if ( verbosity == Verbosity.TOURNAMENT )
		{
			try
			{
				Thread.sleep(tournamentDelay);
			}
			catch ( Exception ex )
			{
			}
			System.out.println(msg.toString());
		}
		
		return msg;
		
	}
	
	private boolean isValidLoc ( int row, int col )
	{
		return ( row >= 0 && row < this.numRows && col >= 0 && col < this.numCols );
	}
	
	private boolean isValidGame ( int numRows, int numCols, List<Integer> shipSizeList )
	{
			if ( numRows < 1 || numCols < 1 || shipSizeList.isEmpty() )
				return false;
			//if both the height and width are less than two times the number of ships
			if (( numRows < 2 * shipSizeList.size()) && (numCols < 2 * shipSizeList.size()) )
				return false;
			//now check if ships fit
			for ( Integer size : shipSizeList )
			{
				//if ship length less than one, then not valid
				if ( size < 1 ){
					return false;
				}
				//if the ship is too long to fit on the board not valid
				if ((size > numRows) && (size > numCols )){
					return false;
				// if the ships will only fit the direction that is less than 2 * the num of ships not valid
				}else if ((size > numRows) && (numRows < 2 * shipSizeList.size())){
					return false;
				}else if ((size > numCols) && (numCols < 2 * shipSizeList.size())){
					return false;
				}
			}
			return true;
	}
	
	private boolean isValidShip ( ABSAttackBoard board, int row, int col, int size, ABSShip.Orientation ori )
	{
		if ( !isValidLoc(row, col) )
			return false;
		if ( ori == ABSShip.Orientation.HORIZONTAL )
		{
			if ( col + size > numCols )
				return false;
			for ( int i = row - 1 ; i <= row + 1 ; i++ )
				for ( int j = col - 1; j <= col + size; j++ )
				{
					if ( isValidLoc(i, j) && board.isOccupied(i, j) )
						return false;
				}
		}
		else
		{
			if ( row + size > numRows )
				return false;
			for ( int i = row - 1 ; i <= row + size ; i++ )
				for ( int j = col - 1; j <= col + 1 ; j++ )
				{
					if ( isValidLoc(i, j) && board.isOccupied(i, j) )
						return false;
				}
		}
		return true;
	}
	
}
