package solver;

import java.util.Stack;

import maze.Direction;
import maze.MazeSolver;
import maze.Room;

public class ImprovedSolver implements MazeSolver 
{
	private Direction facingDir;
	private Stack<Intersection> route;
	private boolean backtracing;

	/**
	 * Default constructor
	 */
	public ImprovedSolver()
	{
		this(Direction.NORTH);
	}
	
	/**
	 * Constructor with initial direction specified
	 * @param initialDir Direction : Direction of entry into the maze.
	 */
	public ImprovedSolver(Direction initialDir)
	{
		facingDir = initialDir;
		route = new Stack<Intersection>();
		backtracing = false;
	}
	
	/**
	 * Method to return the route taken. Useful for printing the final route
	 */
	public Stack<Intersection> getRoute()
	{		
		Stack<Intersection> returnRoute = new Stack<Intersection>();
		
		returnRoute.addAll(this.route);
		
		return returnRoute;
	}	
	
	@Override
	public Direction visit(Room r) 
	{		
		Intersection intersect;
		Direction rDir;
		
		//Wrap the current room in an intersection object which will track the directions 
		//one can move from the current room, the directions already visited, the number of 
		//times the current room has been visited and other useful information
		intersect = IntersectionFactory.MakeIntersection(r, this.facingDir);
		
		//If we haven't been to this room before, push the current room onto the route stack
		if(intersect.isContainedIn(this.route) < 0)
		{
			this.route.push(intersect);
		}
		
		if(intersect.canGoSomewhere() == false)
		{
			//If the current room is a dead end, back track
			rDir = intersect.getBacktrackDir();
			//Pop the top of the stack
			this.route.pop();
			//Set backtracing flag to true
			this.backtracing = true;
		}
		else if((intersect.getVisitCount() > 0) && (this.backtracing == false))
		{
			//If we've already visited the current room but are not back tracking 
			//(i.e. entered this room from another direction already), remove the current direction from the
			//Intersection's list of canGo directions and back track;
			intersect.removeCanGoDirection(this.facingDir.inverse());			
			rDir = this.facingDir.inverse();
			//pop the top of the stack
			this.route.pop();
			//Set backtracing flag to true
			this.backtracing = true;
		}
		else
		{
			//First time visiting room or backtracked to room with at least one canGoDir
			
			//Get a direction we can go
			rDir = intersect.getRandomCanGoDirection();
			//Remove that direction from the list of canGoDirs
			intersect.removeCanGoDirection(rDir);
			
			//increment the visit count
			intersect.incrementVisitCount();
			//Reset the backtracing flag
			this.backtracing = false;
		}
		
		//Set the new facing direction
		this.facingDir = rDir;
		
		if(route.isEmpty())
		{
			throw new RuntimeException("ERROR: Back at start and nowhere to go!");
		}
		
		return rDir;
	}

}
