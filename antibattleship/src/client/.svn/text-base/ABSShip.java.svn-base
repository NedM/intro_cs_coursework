package client;

import java.util.*;

import message.ResultType;

/**
 * 
 * Represents a ship on an ABSBoard... note that this class is not intended to be decoupled from
 * ABSBoard. A ship doesn't mean anything to us if it's not on a board
 * 
 * @author JVen
 *
 */

public class ABSShip
{

	public enum Orientation
	{
		HORIZONTAL,
		VERTICAL
	}
	
	private final int row;
	private final int col;
	private final int length;
	private final Orientation orientation;
	private final List<Boolean> hits;
	
	/**
	 * 
	 * Constructor. Note that we expect this ship to be tied to an ABSBoard.
	 * 
	 * @author JVen
	 * @param row the row of the topmost or leftmost square of the ship
	 * @param col the column of the topmost or leftmost square of the ship
	 * @param length the length of the ship (sometimes called size in other classes)
	 * @param orientation the orientation of the ship (horizontal/vertical)
	 * @requires 0 <= row < numRows - length, 0 <= col < numCols - length, where numRows and numCols come from the ship's containing ABSBoard
	 * 
	 */
	
	public ABSShip ( int row, int col, int length, Orientation orientation )
	{
		this.row = row;
		this.col = col;
		this.length = length;
		this.orientation = orientation;
		this.hits = new ArrayList<Boolean>(length);
		for ( int i = 0 ; i < length ; i++ )
			hits.add(false);
	}
	
	/**
	 * 
	 * Gets the index in hits corresponding to square (r, c)
	 * 
	 * @author JVen
	 * @param r row
	 * @param c column
	 * @return the index in hits corresponding to (r, c)
	 * 
	 */
	
	private int getIndex ( int row, int col )
	{
		if ( orientation == Orientation.HORIZONTAL && row == this.row && col - this.col >= 0 && col - this.col < length )
			return col - this.col;
		if ( orientation == Orientation.VERTICAL && col == this.col && row - this.row >= 0 && row - this.row < length )
			return row - this.row;
		else
			return -1;
	}
	
	/**
	 * 
	 * Returns whether the ship has been sunk
	 * NOTE: This method is private... from the board up, we want to use ResultTypes for this kind of information
	 * 
	 * @author JVen
	 * @return whether the ship has been sunk
	 * 
	 */
	
	private boolean isSunk()
	{
		for ( Boolean b : hits )
			if ( !b.booleanValue() )
				return false;
		return true;
	}
	
	/**
	 * 
	 * Called when an attack is made to the ship
	 * 
	 * @author JVen
	 * @param row the row to attack
	 * @param col the column to attack
	 * @return the outcome of the attack
	 * @modifies marks square as attacked
	 * 
	 */
	
	public ResultType attack ( int row, int col )
	{
		if ( getIndex(row, col) == -1 )
			return ResultType.MISS;
		else
		{
			hits.set(getIndex(row, col), true);
			if ( !isSunk() )
				return ResultType.HIT;
			else
				return ResultType.SUNK;
		}
	}
	
	/*
	 * Getters
	 */
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public ABSBoardSquare getPosition()
	{
		return new ABSBoardSquare(this.row, this.col);
	}
	
	public int getLength()
	{
		return length;
	}
	
	public Orientation getOrientation()
	{
		return orientation;
	}
	
}
