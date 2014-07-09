package client;

import java.util.List;

/**
 * Base class for ABSAttackBoard and ABSMarkBoard
 * Holds board dimensions
 * @author nedmurp
 *
 */
public abstract class ABSBoard 
{
	protected final int numRows;
	protected final int numCols;
	
	protected ABSBoard(int rows, int columns)
	{
		if(rows < 1 || columns < 1)
		{
			throw new IllegalArgumentException("Number of rows and number of columns must both be greater than 1.\nNum rows was " + rows + " num columns was " + columns);
		}
		this.numRows = rows;
		this.numCols = columns;
	}
	
	public int getNumRows()
	{
		return numRows;
	}
	
	public int getNumCols()
	{
		
		return numCols;
	}
	
}
