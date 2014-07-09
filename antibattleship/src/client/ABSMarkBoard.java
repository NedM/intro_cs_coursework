package client;

import java.util.*;

import message.ResultType;

/**
 * 
 * Stores a player's previous attacks on the other player
 * 
 * @author JVen
 *
 */

public class ABSMarkBoard extends ABSBoard
{

	public enum MarkType
	{
		NOT_TRIED,
		TRIED_AND_UNKNOWN,
		TRIED_AND_MISS,
		TRIED_AND_HIT;
	}

	private final List<MarkType> board;
	
	/**
	 * 
	 * Constructor
	 * 
	 * @author JVen
	 * @param numRows the number of rows on the board
	 * @param numCols the number of columns on the board
	 * @requires numRows, numCols > 1 (be nice plz)
	 * 
	 */
	
	public ABSMarkBoard ( int numRows, int numCols )
	{
		super(numRows, numCols);
		
		this.board = new ArrayList<MarkType>();
		for ( int i = 0 ; i < super.numRows ; i++ )
			for ( int j = 0 ; j < super.numCols ; j++ )
				this.board.add(MarkType.NOT_TRIED);
	}
	
	/**
	 * 
	 * Marks (row, col) as tried
	 * 
	 * @param row row
	 * @param col column
	 * @param r the result of the attack on (row, col)
	 * @modifies marks (row, col) in board
	 * 
	 */
	
	public void mark ( int row, int col, ResultType r )
	{
		if ( this.board.get(getIndex(row, col)) == MarkType.NOT_TRIED || this.board.get(getIndex(row, col)) == MarkType.TRIED_AND_UNKNOWN )
		{
			if ( r == ResultType.MISS )
				this.board.set(getIndex(row, col), MarkType.TRIED_AND_MISS);
			else
				this.board.set(getIndex(row, col), MarkType.TRIED_AND_HIT);
		}
	}
	
	public void markUnknown ( int row, int col )
	{
		if ( this.board.get(getIndex(row, col)) == MarkType.NOT_TRIED )
			this.board.set(getIndex(row, col), MarkType.TRIED_AND_UNKNOWN);
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
	
	/**
	 * Gets the value of a particular square
	 * 
	 * @param row
	 * @param col
	 * @return int, the value of the square
	 */
	public MarkType getSquare ( int row, int col )
	{
		return this.board.get(getIndex(row,col));
	}
	
	/**
	 * @author nedmurp
	 * @return A List of MarkTypes representing the board state 
	 */
	public List<MarkType> getBoard()
	{
		List<MarkType> tempBoard = new ArrayList<MarkType>(this.board);
		return tempBoard;
	}
			
	/**
	 * 
	 * Returns whether the square (row, col) has been tried before
	 * 
	 * @author JVen
	 * @param row row
	 * @param col column
	 * @return whether the square (row, col) has been tried before
	 * @requires 0 <= r < numRows, 0 <= c < numCols
	 * 
	 */
	
	public boolean wasTried ( int row, int col )
	{
		return ( this.board.get(getIndex(row, col)) != MarkType.NOT_TRIED );
	}
	
	/**
	 * 
	 * Returns whether there is any untried square left on the board
	 * 
	 * @return JVen
	 * @return whether there is any untried square left on the board
	 * 
	 */
	
	public boolean isUntriedLeft()
	{
		for ( int i = 0 ; i < super.numRows ; i++ )
			for ( int j = 0 ; j < super.numCols ; j++ )
				if ( this.board.get(getIndex(i, j)) == MarkType.NOT_TRIED )
					return true;
		
		return false;
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
				if ( this.board.get(getIndex(r, c)) == MarkType.NOT_TRIED || this.board.get(getIndex(r,c)) == MarkType.TRIED_AND_UNKNOWN )
					ans += " ";
				else if ( this.board.get(getIndex(r, c)) == MarkType.TRIED_AND_MISS )
					ans += "o";
				else if ( this.board.get(getIndex(r, c)) == MarkType.TRIED_AND_HIT )
					ans += "x";
				
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
