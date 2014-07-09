package client;

import java.util.*;

import message.ResultType;

/**
 * 
 * Represents state of a player's own ships as well as his opponent's attacks
 * 
 * @author JVen
 *
 */

public class ABSAttackBoard extends ABSBoard
{
	
	public static final int UNHIT_WATER = -1;
	public static final int HIT_WATER = -2;
	public static final int HIT_SHIP = -3;
	public static final int SUNK_SHIP = -4;
	
	private final List<ABSShip> ships;
	private final List<Integer> board;
	private boolean repeatedAttack;
	private int numUnsunkShips;
	
	/**
	 * 
	 * Constructor
	 * 
	 * @author JVen
	 * @param numRows the number of rows on the board
	 * @param numCols the number of columns on the board
	 * @param ships the ships to place on the board
	 * @requires numRows, numCols > 1 (be nice plz)
	 * 
	 */
	
	public ABSAttackBoard ( int numRows, int numCols, List<ABSShip> ships )
	{
		super(numRows, numCols);
		this.ships = new ArrayList<ABSShip>(ships);
		this.repeatedAttack = false;
		
		this.board = new ArrayList<Integer>();
		for ( int i = 0 ; i < super.numRows ; i++ )
			for ( int j = 0 ; j < super.numCols ; j++ )
				this.board.add(UNHIT_WATER);
				
		for ( int ID = 0 ; ID < this.ships.size(); ID++ )
		{
			ABSShip ship = this.ships.get(ID);
			int row = ship.getRow();
			int col = ship.getCol();
			
			// store the ID of the ship at each square the ship occupies
			for ( int l = 0 ; l < ship.getLength(); l++ )
			{
				if ( ship.getOrientation() == ABSShip.Orientation.HORIZONTAL )
					this.board.set(getIndex(row, col + l), ID);
				else
					this.board.set(getIndex(row + l, col), ID);
			}
		}
		
		this.numUnsunkShips = ships.size();
		
	}
	
	/**
	 * 
	 * Called when an attack is made to the board
	 * 
	 * @author JVen
	 * @param row the row to attack
	 * @param col the column to attack
	 * @return the outcome of the attack
	 * @modifies marks square as attacked and takes appropriate action on the ship on that square, if any
	 * @requires 0 <= r < numRows, 0 <= c < numCols
	 * 
	 */
	
	public ResultType attack ( int row, int col )
	{
		
		if ( this.board.get(getIndex(row, col)) == UNHIT_WATER )
		{
			this.board.set(getIndex(row, col), HIT_WATER);
			return ResultType.MISS;
		}
		else if ( board.get(getIndex(row, col)) == HIT_WATER )
		{
			this.repeatedAttack = true;
			return ResultType.MISS;
		}
		else if ( board.get(getIndex(row, col)) == HIT_SHIP )
		{
			this.repeatedAttack = true;
			return ResultType.HIT;
		}
		else if ( board.get(getIndex(row, col)) == SUNK_SHIP )
		{
			this.repeatedAttack = true;
			return ResultType.MISS;
		}
		else
		{
			// ship there!
			ABSShip s = this.ships.get(this.board.get(getIndex(row, col)));
			ResultType r = s.attack(row, col);
			
			// if it's sunk, sink the ship before saying it's sunk
			if ( r == ResultType.SUNK )
			{
				// decrement unsunk ship counter
				this.numUnsunkShips--;
				for ( int L = 0 ; L < s.getLength(); L++ )
				{
					if ( s.getOrientation() == ABSShip.Orientation.HORIZONTAL )
						this.board.set(getIndex(s.getRow(), s.getCol() + L), SUNK_SHIP );
					else
						this.board.set(getIndex(s.getRow() + L, s.getCol()), SUNK_SHIP );
				}
			}
			else
			{
				this.board.set(getIndex(row, col), HIT_SHIP );
			}
			
			return r;
		}
		
	}
	
	/**
	 * 
	 * Gets the index of board corresponding to square (r, c)
	 * 
	 * @author JVen
	 * @param r row
	 * @param c column
	 * @return the index in board corresponding to (r, c)
	 * @requires 0 <= r < numRows, 0 <= c < numCols
	 * 
	 */
	
	private int getIndex ( int r, int c )
	{
		return (super.numCols * r) + c;
	}
	
	public int getSquare ( int row, int col )
	{
		return this.board.get(getIndex(row,col));
	}
	
	/**
	 * 
	 * Return if the square (row, col) was/is occupied by a ship
	 * 
	 * @author JVen
	 * @param row row
	 * @param col column
	 * @return whether the square (row, col) was/is occupied by a ship
	 * @requires 0 <= r < numRows, 0 <= c < numCols
	 * 
	 */
	
	public boolean isOccupied ( int row, int col )
	{
		return ( board.get(getIndex(row, col)) != UNHIT_WATER && board.get(getIndex(row, col)) != HIT_WATER );
	}
	
	/**
	 * 
	 * Return if there has ever been an attack to the same square of the board
	 * 
	 * @author JVen
	 * @return whether there has ever been an attack to the same square of the board
	 * 
	 */
	
	public boolean repeatedAttack()
	{
		return repeatedAttack;
	}
	
	public List<ABSShip> getShips()
	{
		List<ABSShip> tempShips = new ArrayList<ABSShip>(this.ships);
		return tempShips;
	} 
	
	public List<Integer> getBoardState()
	{
		List<Integer> tempBoardState = new ArrayList<Integer>(this.board);
		return tempBoardState;
	}
	
	/**
	 * 
	 * Return the number of unsunk ships
	 * 
	 * @author JVen
	 * @return the number of unsunk ships
	 * 
	 */
	
	public int getNumUnsunkShips()
	{
		return numUnsunkShips;
	}
	
	public boolean allSunk()
	{
		return ((this.getNumUnsunkShips() == 0) ? true : false);
	}
	
	/**
	 * 
	 * Return the original state of the board as a string of 0s and 1s
	 * Used in calculating board hash
	 * 
	 * @author JVen
	 * @return a String of 0s and 1s, representing non-occupied and occupied squares, respectively, from top to bottom
	 * 
	 */
	
	public String getBoardRep()
	{
		String ans = "";
		for ( int n : this.board )
		{
			if ( n == UNHIT_WATER || n == HIT_WATER )
				ans += "0";
			else
				ans += "1";
		}
		return ans;
	}
	
	/**
	 * 
	 * Ascii-representation of the board! :D
	 * 
	 * @author JVen
	 * @return ascii art representing the board
	 * 
	 */
	
	@Override
	public String toString()
	{
		
		String ans = "\n";
		
		// line 1
		ans += "  ";
		for ( int c = 0 ; c < super.numCols ; c++ )
			ans += "   " + c%10;
		ans += "\n";
		
		// line 2
		ans += "   ";
		for ( int c = 0 ; c < super.numCols ; c++ )
			ans += " ___";
		ans += "\n";
		
		// the rest
		for ( int r = 0 ; r < super.numRows ; r++ )
		{
			ans += "   |";
			for ( int c = 0 ; c < super.numCols ; c++ )
				ans += "   |";
			ans += "\n";
			
			ans += " " + r%10 + " |";
			for ( int c = 0 ; c < super.numCols ; c++ )
			{
				ans += " ";
				
				// value of the square
				if ( this.board.get(getIndex(r, c)) == UNHIT_WATER )
					ans += " ";
				else if ( this.board.get(getIndex(r, c)) == HIT_WATER )
					ans += "~";
				else if ( this.board.get(getIndex(r, c)) == HIT_SHIP )
					ans += "x";
				else if ( this.board.get(getIndex(r, c)) == SUNK_SHIP )
					ans += "-";
				else
					ans += "o";
				
				ans += " |";
			}
			ans += "\n";
			
			ans += "   |";
			for ( int c = 0 ; c < super.numCols ; c++ )
				ans += "___|";
			ans += "\n";
		}
			
		ans += "\n";
		
		return ans;
		
	}
	
}
