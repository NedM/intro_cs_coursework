package players;

import client.*;

import java.util.*;

import message.*;

public class GUIPlayer implements ABSPlayer
{
	
	public enum Verbosity
	{
		DEBUG,
		OFF
	}
	
	public enum InitGameResponse
	{
		ACCEPT,
		REJECT,
		DONT_KNOW
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
	
	private boolean complain = true;
	
	private final String username;
	private final Verbosity verbosity;
	private final int salt;
	private final boolean isInitiating;
	
	private GameStage stage;

	private GameVariant gameVariant;
	
	private boolean initGameSent = false;
	private int numRows;
	private int numCols;
	private List<Integer> shipSizeList;
	
	private InitGameResponse doWeAccept = InitGameResponse.DONT_KNOW;
	private InitGameResponse didOpponentAccept = InitGameResponse.DONT_KNOW;
	private boolean isBoardHashReceived = false;
	
	private String oppBoardHash;
	private String oppBoardRep;
	private int oppSalt;
	
	private boolean shipsSent = false;
	private List<ABSShip> ships;
	private ABSAttackBoard attackBoard;
	private ABSMarkBoard markBoard;
	
	private boolean targetsSent = false;
	private List<ABSBoardSquare> guiTargets;
	private List<ABSBoardSquare> lastMyPositions;
	private List<ABSBoardSquare> lastOppPositions;
	private List<ResultType> lastMyOutcomes;
	private List<ResultType> lastOppOutcomes;
	
	private int numOppHitsLeft;
	private int numOppShipsLeft;
	
	private GameResult gameResult = GameResult.DONT_KNOW;
	
	public GUIPlayer( boolean isHost )
	{
		this(isHost, "Captain Noname", Verbosity.OFF);
	}
	
	public GUIPlayer ( boolean isHost, String username )
	{
		this(isHost, username, Verbosity.OFF);
	}
	
	public GUIPlayer ( boolean isHost, Verbosity verbosity )
	{
		this(isHost, "Captain Noname", verbosity);
	}
	
	public GUIPlayer ( boolean isInitiating, String username, Verbosity verbosity )
	{
		this.salt = (int)(Integer.MAX_VALUE * Math.random()); // :D
		this.isInitiating = isInitiating;
		this.username = username;
		this.stage = GameStage.INIT_GAME;
		this.verbosity = verbosity;
	}
	
	public void sendBoardInfo ( int numRows, int numCols, List<Integer> shipSizeList, boolean salvo )
	{
		this.initGameSent = true;
		this.numRows = numRows;
		this.numCols = numCols;
		this.shipSizeList = new ArrayList<Integer>(shipSizeList);
		
		if ( salvo )
			gameVariant = GameVariant.SALVO;
		else
			gameVariant = GameVariant.NORMAL;
	}
	
	public boolean isBoardHashReceived()
	{
		return isBoardHashReceived;
	}
	
	public void sendDoWeAccept ( InitGameResponse doWeAccept )
	{
		this.doWeAccept = doWeAccept;
	}
	
	public InitGameResponse didOpponentAccept()
	{
		return didOpponentAccept;
	}
	
	public void sendShipLocs ( List<ABSShip> ships )
	{
		this.shipsSent = true;
		this.ships = new ArrayList<ABSShip>(ships);
	}
	
	public boolean isInitGameSent()
	{
		return initGameSent;
	}
	
	public int getNumRows()
	{
		return numRows;
	}
	
	public int getNumCols()
	{
		return numCols;
	}
	
	public List<Integer> getShipSizeList()
	{
		return new ArrayList<Integer>(shipSizeList);
	}
	
	public void sendMyTargets ( List<ABSBoardSquare> targets )
	{
		this.targetsSent = true;
		this.guiTargets = new ArrayList<ABSBoardSquare>(targets);
	}
	
	public List<ResultType> getMyResults()
	{
		if ( lastMyOutcomes != null )
		{
			List<ResultType> ans = new ArrayList<ResultType>(lastMyOutcomes);
			lastMyOutcomes = null;
			return ans;
		}
		else
			return null;
	}
	
	public List<ABSBoardSquare> getOppTargets()
	{
		if ( lastOppPositions != null )
		{
			List<ABSBoardSquare> ans = new ArrayList<ABSBoardSquare>(lastOppPositions);
			lastOppPositions = null;
			return ans;
		}
		else
			return null;
	}
	
	public GameResult getGameResult()
	{
		return gameResult;
	}
	
	public boolean getIsInitiating()
	{
		return isInitiating;
	}
	
	public boolean getIsSalvo()
	{
		return ( gameVariant == GameVariant.SALVO );
	}
	
	@Override
	public String getName()
	{
		return this.username;
	}

	@Override
	public void sendMsgToPlayer ( ABSMessage m )
	{
		
		switch ( m.getMessageType() )
		{
		
			case SYNTAX_ERROR:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("GUIPlayer: Opponent threw a syntax error.");
				gameResult = GameResult.ERROR;
				stage = GameStage.GG;
				break;
				
			case GAME_ERROR:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("GUIPlayer: Opponent threw a game error.");
				gameResult = GameResult.ERROR;
				stage = GameStage.GG;
				break;
				
			case INIT_GAME:
				
				if ( stage == GameStage.INIT_GAME )
				{
					
					// get information
					
					numRows = ((ABSInitGameMessage)m).getNumRows();
					numCols = ((ABSInitGameMessage)m).getNumColumns();
					shipSizeList = ((ABSInitGameMessage)m).getShipSizeList();
					
					// get the total number of hits
					
					for ( Integer size : shipSizeList )
						numOppHitsLeft += size;
					
					// set number of ships
					
					numOppShipsLeft = shipSizeList.size();
					
					// get game variant
					
					if ( ((ABSInitGameMessage)m).getSalvo() )
						gameVariant = GameVariant.SALVO;
					else
						gameVariant = GameVariant.NORMAL;
					
					// signal GUI
					
					initGameSent = true;
					
				}
				else
					stage = GameStage.GAME_ERROR;

				break;
				
			case GAME_ACCEPT:
				
				if ( stage == GameStage.INIT_GAME )
				{
					didOpponentAccept = InitGameResponse.ACCEPT;
					stage = GameStage.EXCHANGE_HASH;
				}
				else
					stage = GameStage.GAME_ERROR;
				
				break;
				
			case GAME_DENY:
				
				if ( stage == GameStage.INIT_GAME )
				{
					didOpponentAccept = InitGameResponse.REJECT;
					stage = GameStage.GG;
				}
				else
					stage = GameStage.GAME_ERROR;

				break;
				
			case BOARD_HASH:

				if ( stage == GameStage.EXCHANGE_HASH )
				{
					isBoardHashReceived = true;
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
					
					lastOppOutcomes = new ArrayList<ResultType>();
					
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
							lastOppOutcomes.add(r);
						}
					}
					
					// wait for gui to get last positions
					
					while ( lastOppPositions != null )
					{
						try
						{
							Thread.sleep(100);
						}
						catch ( InterruptedException ex )
						{
						}
					}
					
					// wait a little bit
					
					try
					{
						Thread.sleep(200);
					}
					catch ( InterruptedException ex )
					{
					}
					
					// send opp targets to gui
					
					lastOppPositions = new ArrayList<ABSBoardSquare>(((ABSTargetMessage)m).getListOfTargets());
					
					// wait a little bit
					
					try
					{
						Thread.sleep(200);
					}
					catch ( InterruptedException ex )
					{
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
					
					// handle the results
					
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
						
						// mark the mark board
						
						markBoard.mark(lastMyPositions.get(i).getRow(), lastMyPositions.get(i).getColumn(), r);
						
					}
					
					// send my results to gui
					
					lastMyOutcomes = ((ABSResultsMessage)m).getOutcomes();
					
					// wait a little bit
					
					try
					{
						Thread.sleep(200);
					}
					catch ( InterruptedException ex )
					{
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
						System.out.println("GUIPlayer: We won! :D");
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
						System.out.println("GUIPlayer: Opponent rejected our victory.");
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
					complain = true;
					while ( !initGameSent )
					{
						if ( complain )
						{
							complain = false;
							if ( verbosity == Verbosity.DEBUG )
								System.out.println("GUIPlayer: Waiting for game!");
						}
						try
						{
							Thread.sleep(100);
						}
						catch ( InterruptedException e1 )
						{
						}
					}

					if ( gameVariant == GameVariant.SALVO )
						msg = new ABSInitGameMessage(numRows, numCols, shipSizeList, true);
					else
						msg = new ABSInitGameMessage(numRows, numCols, shipSizeList, false);
					
					// set total number of hits
					
					for ( Integer size : shipSizeList )
						numOppHitsLeft += size;
					
					// set number of ships
					
					numOppShipsLeft = shipSizeList.size();
					
				}
				else
				{
					
					// auto reject
					
					if ( !isValidGame(numRows, numCols, shipSizeList) )
					{
						msg = new ABSGameDenyMessage();						
						stage = GameStage.GG;
					}
					else
					{
					
						while ( doWeAccept == InitGameResponse.DONT_KNOW )
						{
							
							// wait for gui to tell us
							try
							{
								Thread.sleep(100);
							}
							catch ( InterruptedException e1 )
							{
							}
							
						}
						
						if ( doWeAccept == InitGameResponse.ACCEPT )
						{
							msg = new ABSGameAcceptMessage();
							stage = GameStage.EXCHANGE_HASH;
						}
						else
						{
							msg = new ABSGameDenyMessage();
							stage = GameStage.GG;
						}
						
					}
					
				}
				
				break;
				
			case EXCHANGE_HASH:
				
				complain = true;
				while ( !shipsSent )
				{
					if ( complain )
					{
						complain = false;
						if ( verbosity == Verbosity.DEBUG )
							System.out.println("GUIPlayer: Waiting for ships!");
					}
					try
					{
						Thread.sleep(100);
					}
					catch ( InterruptedException e1 )
					{
					}
				}
				
				attackBoard = new ABSAttackBoard(numRows, numCols, ships);
				markBoard = new ABSMarkBoard(numRows, numCols);
				
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
				
				// check if there's a square left to hit
				
				if ( !markBoard.isUntriedLeft() )
				{
					// we lost, just send (0,0)
					msg = new ABSTargetMessage();
					ABSBoardSquare origin = new ABSBoardSquare(0,0);
					List<ABSBoardSquare> targets = new ArrayList<ABSBoardSquare>();
					targets.add(origin);
					((ABSTargetMessage)msg).setTargets(targets);
					return msg;
				}
				
				// get targets
				
				complain = true;
				while ( !targetsSent )
				{
					
					if ( complain )
					{
						complain = false;
						if ( verbosity == Verbosity.DEBUG )
							System.out.println("GUIPlayer: Waiting for targets!");
					}
					try
					{
						Thread.sleep(100);
					}
					catch ( InterruptedException e1 )
					{
					}
					
				}
				
				// wait a little bit
				
				try
				{
					Thread.sleep(200);
				}
				catch ( InterruptedException ex )
				{
				}
				
				// reset for next time
				
				targetsSent = false;
				
				lastMyPositions = new ArrayList<ABSBoardSquare>(this.guiTargets);
				msg = new ABSTargetMessage();
				((ABSTargetMessage)msg).setTargets(this.guiTargets);
				
				break;
				
			case OPP_TURN:
				
				switch ( gameVariant )
				{
					case NORMAL:
						
						if ( !lastOppOutcomes.contains(ResultType.MISS) )
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
				((ABSResultsMessage)msg).setOutcomes(lastOppOutcomes);				
				
				break;
				
			case DECLARE_VICTORY:
				
				msg = new ABSVictoryMessage(salt, attackBoard.getBoardRep());
				
				break;
				
			case DECIDE_VICTORY:
				
				String calcHash = ABSBoardHash.computeBoardHash(oppBoardRep, numRows, numCols, oppSalt);
				
				if ( calcHash.equals(oppBoardHash) )
				{
					// TODO make sure placement is valid
					if ( verbosity == Verbosity.DEBUG )
						System.out.println("GUIPlayer: We lost. :'(");
					gameResult = GameResult.LOSS;
					msg = new ABSAcceptVictoryMessage();					
				}
				else
				{
					if ( verbosity == Verbosity.DEBUG )
						System.out.println("GUIPlayer: Opponent declared victory incorrectly. :s");
					gameResult = GameResult.ERROR;
					msg = new ABSRejectVictoryMessage();
				}
				
				break;
				
			case GAME_ERROR:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("GUIPlayer: Opponent caused a game error. :[");
				gameResult = GameResult.ERROR;
				msg = new ABSGameErrorMessage();
				((ABSGameErrorMessage)msg).setErrorMessage("");
				
				break;
				
			case GG:
				
				if ( verbosity == Verbosity.DEBUG )
					System.out.println("GUIPlayer: GG NO RE");
				msg = new ABSGameErrorMessage();
				((ABSGameErrorMessage)msg).setErrorMessage("");
				break;
				
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
