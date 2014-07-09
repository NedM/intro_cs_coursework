package players.jasper;

import java.util.*;

import message.ResultType;
import client.*;
import client.ABSMarkBoard.MarkType;

/**
 * 
 * Used by Jasper to determine which square to target next
 * 
 * @author JVen
 *
 */

public class JasperMarkBoard
{

	private final int numRows;
	private final int numCols;
	private final ABSMarkBoard markBoard;
	
	private final Set<String> avoidSet;
	
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
	
	public JasperMarkBoard ( int numRows, int numCols )
	{
		this.numRows = numRows;
		this.numCols = numCols;
		markBoard = new ABSMarkBoard(numRows, numCols);
		avoidSet = new HashSet<String>();
	}
	
	public ABSMarkBoard getBoard()
	{
		return this.markBoard;
	}
	
	/**
	 * 
	 * Get target of next attack
	 * 
	 * @author JVen
	 * @return position (row, col) of next attack
	 * @modifies marks the returned position and tried and unknown so that
	 * Jasper does not repeat this target in salvo mode
	 * 
	 */
	
	public ABSBoardSquare getTarget()
	{
		
		for ( int row = 0 ; row < numRows ; row++ )
		{
			for ( int col = 0 ; col < numCols ; col++ )
			{
				String position = "(" + row + "," + col + ")";
				if ( !markBoard.wasTried(row, col) && !avoidSet.contains(position) )
				{
					markBoard.markUnknown(row, col);
					return new ABSBoardSquare(row, col);
				}
			}
		}
		
		// there are no more avoid squares
		// if there are 2 avoid squares, find the one with 2 hit neighbors
		
		for ( int row = 0 ; row < numRows ; row++ )
		{
			for ( int col = 0 ; col < numCols ; col++ )
			{
				String position = "(" + row + "," + col + ")";
				if ( avoidSet.contains(position) )
				{
					int numHitNeighbors = 0;
					if ( isValidLoc(row - 1, col - 1) && markBoard.getSquare(row - 1, col - 1) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row - 1, col + 0) && markBoard.getSquare(row - 1, col + 0) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row - 1, col + 1) && markBoard.getSquare(row - 1, col + 1) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row + 0, col - 1) && markBoard.getSquare(row + 0, col - 1) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row + 0, col + 1) && markBoard.getSquare(row + 0, col + 1) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row + 1, col - 1) && markBoard.getSquare(row + 1, col - 1) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row + 1, col + 0) && markBoard.getSquare(row + 1, col + 0) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					if ( isValidLoc(row + 1, col + 1) && markBoard.getSquare(row + 1, col + 1) == MarkType.TRIED_AND_HIT )
						numHitNeighbors++;
					
					if ( numHitNeighbors == 1 )
					{
						avoidSet.remove(position);
						return new ABSBoardSquare(row, col);	
					}
				}
			}
		}
		
		// no dice, just pick any avoid square
		
		for ( int row = 0 ; row < numRows ; row++ )
		{
			for ( int col = 0 ; col < numCols ; col++ )
			{
				String position = "(" + row + "," + col + ")";
				if ( avoidSet.contains(position) )
				{
					avoidSet.remove(position);
					return new ABSBoardSquare(row, col);
				}
			}
		}
		
		// we lost AND we have to repeat a square :(
		
		return new ABSBoardSquare(0, 0);
		
	}
	
	public List<ABSBoardSquare> getAvoidSet()
	{
		List<ABSBoardSquare> avoidList = new ArrayList<ABSBoardSquare>();
		for ( String s : this.avoidSet )
		{
			String[] toks = s.substring(1, s.length() - 1).split(",");
			int row = Integer.parseInt(toks[0]);
			int col = Integer.parseInt(toks[1]);
			avoidList.add(new ABSBoardSquare(row, col));
		}
		return avoidList;
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
		markBoard.mark(row, col, r);
		if ( avoidSet.isEmpty() && r == ResultType.HIT )
		{
			if ( col < numCols - 1 )
			{
				String avoid = "(" + row + "," + (col + 1) + ")";
				avoidSet.add(avoid);
			}
			if ( row < numRows - 1 )
			{
				String avoid = "(" + (row + 1) + "," + col + ")";
				avoidSet.add(avoid);
			}
		}
	}
	
	/**
	 * 
	 * Returns whether the square (row, col) has been tried before
	 * 
	 * @param row row
	 * @param col column
	 * @return whether the square (row, col) has been tried before
	 * @requires 0 <= r < numRows, 0 <= c < numCols
	 * 
	 */
	
	public boolean wasTried ( int row, int col )
	{
		return markBoard.wasTried(row, col);
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
		return markBoard.toString();
	}
	
	/**
	 * 
	 * Returns whether (row, col) represents a valid board location
	 * 
	 * @param row row
	 * @param col column
	 * @return whether (row, col) is a valid board location
	 * 
	 */
	
	private boolean isValidLoc ( int row, int col )
	{
		return ( row >= 0 && row < numRows && col >= 0 && col < numCols );
	}
	
}
