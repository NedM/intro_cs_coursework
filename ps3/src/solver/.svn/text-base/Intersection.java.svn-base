package solver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import maze.Direction;
import maze.Room;

public class Intersection
{
	private Direction[] allDirs = { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
	private List<Direction> canGoDirections = new ArrayList<Direction>(4);
	private int xCoord, yCoord;
	private Direction backtrackDir = null;
	private int visitCount;
	
	/**
	 * Default constructor. Adds the directions we can move in to the list of CanGoDirections
	 * Use this constructor for the initial room only if you're starting inside the maze
	 * @param r Room : the current room which Intersection wraps
	 */
	public Intersection(Room r)
	{		
		xCoord = r.getX();
		yCoord = r.getY();
		visitCount = 0;
		
		for(Direction d : allDirs)
		{
			if(!r.hasWall(d))
			{
				canGoDirections.add(d);
			}
		}
	}
	
	/**
	 * Constructor. Use this constructor for all subsequent rooms visited
	 * @param r Room : the current room
	 * @param enteredFromDir Direction : the direction we entered this room from
	 */
	public Intersection(Room r, Direction enteredFromDir)
	{
		this(r);
		backtrackDir = enteredFromDir;
		canGoDirections.remove(enteredFromDir);
	}
	
	/** 
	 * @return The X coordinate of the room that this intersection wraps 
	 */
	public int getX()
	{
		return this.xCoord;
	}
	
	/**
	 * @return the Y coordinate of the room that this intersection wraps
	 */
	public int getY()
	{
		return this.yCoord;
	}

	/**
	 * Tests whether one intersection is the same as another based on the X,Y coordinates of the two intersections
	 * If the X,Y coordinates are the same, the intersections are equal
	 * @param i Intersection : the intersection to compare this to
	 * @return boolean : true if the intersections are the same. False otherwise
	 */
	public boolean Equals(Intersection i)
	{
		boolean rVal = false;
		if((i.getX() == this.getX()) && (i.getY() == this.getY()))
				rVal = true;
		return rVal;
	}
	
	/**
	 * Returns the index of the current intersection in the collection passed in if it is found in the collection
	 * returns -1 if the intersection is not contained in the list
	 * @param interCollect Collection<Intersection> : Collection of intersections to search for an instance of the current intersection 
	 * @return -1 if the current intersection is not in the collection. The index of the current intersection in the collection otherwise
	 */
	public int isContainedIn(Collection<Intersection> interCollect)
	{
		int rVal = -1;
		int iCount = 0;
		
		//Iterate through the intersections in the collection
		for(Intersection i : interCollect)
		{
			//Check to see if they're equal to the current intersection
			if(this.Equals(i))
			{				
				//If so, return the index of the item
				rVal = iCount;
				break;
			}
			
			//increment the index count
			iCount++;
		}
		
		return rVal;
	}
	
	/**
	 * Returns true if you can travel in the specified direction from the current intersection
	 * @param d Direction : the direction of travel desired
	 * @return boolean : true if you can travel in the direction specified and haven't already been in that direction
	 */
	public boolean canGo(Direction d)
	{
		return canGoDirections.contains(d) ? true : false;
	}
	
	/**
	 * Returns true of there is at least one unvisited unwalled direction in the current intersection 
	 * @return boolean : true if there is at least one direction we can go from the current intersection
	 */
	public boolean canGoSomewhere()
	{
		return this.canGoDirections.isEmpty() ? false : true;
	}
	
	/**
	 * When we visit a direction, we want to remove that direction from the list of directions we can go from the current intersection
	 * @param d Direction : the direction to remove from the list of directions we can go from this intersection
	 */
	public void removeCanGoDirection(Direction d)
	{
		if(this.canGo(d))
		{
			canGoDirections.remove(d);
		}
	}
	
	/**
	 * Returns a random direction from the list of directions we can go. If there are not directions we can go, return NULL 
	 * @return Direction or NULL : a random direction we can move from the current intersection or NULL if there are no directions we can go
	 */
	public Direction getRandomCanGoDirection()
	{
		int randIndex;
		Direction canGoDir = null;
		int maxRand = canGoDirections.size();

		if(maxRand > 0)
		{
			//If there are any directions we can go...
			//Get a random int from 0 to the size of the canGoDirections list inclusive			
			randIndex = (int)(Math.floor(Math.random() * maxRand));
			//Assert that the random int is in the range of 0 to 4 directions
			Assert.assertTrue((randIndex >= 0) && (randIndex <= 3));
					
			canGoDir = canGoDirections.get(randIndex);
		}
		
		return canGoDir;		
	}
	
	/**
	 * The breadcrumb method. Returns the direction from which we first entered this room
	 * @return the direction from which we entered. Go in this direction to back track
	 */
	public Direction getBacktrackDir()
	{
		return this.backtrackDir;
	}
	
	/**
	 * The number of times we have visited this intersection
	 * @return int : the number of times we have visited this intersection
	 */
	public int getVisitCount()
	{
		return this.visitCount;
	}
	
	/**
	 * Adds one to the number of times we have visited this intersection
	 */
	public void incrementVisitCount()
	{
		this.visitCount++;
	}
}
