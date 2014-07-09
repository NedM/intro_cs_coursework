package solver;

import maze.Direction;
import maze.MazeSolver;
import maze.Room;

public class WallFollower implements MazeSolver 
{
	private Direction facingDir;
	
	public WallFollower() 
	{
		this(Direction.NORTH);
	}
	
	public WallFollower(Direction initialDir)
	{
		facingDir = initialDir;
	}
	
	public Direction getFacingDirection()
	{
		return this.facingDir;
	}
	
	@Override
	public Direction visit(Room r) 
	{					
		if(!r.hasWall(this.facingDir.left()))
		{
			// If no wall to the left, turn left
			this.facingDir = this.facingDir.left();
		}
		else if(!r.hasWall(this.facingDir))
		{
			// If wall to the left but no wall straight, stay straight
			//No op
		}
		else if(!r.hasWall(this.facingDir.right()))
		{
			// If walls left and ahead, turn right
			this.facingDir = this.facingDir.right();
		}
		else if(!r.hasWall(this.facingDir.inverse()))
		{
			// if walls left right and straight, turn around
			this.facingDir = this.facingDir.inverse();
		}
		else
		{
			// Trapped! Can't do anything!			
		}		
		
		return this.facingDir;
	}
}
