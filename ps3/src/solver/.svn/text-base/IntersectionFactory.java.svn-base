package solver;

import java.util.ArrayList;
import java.util.List;

import maze.Direction;
import maze.Room;

public class IntersectionFactory 
{
	private static List<Intersection> visited = new ArrayList<Intersection>();
	
	public static Intersection MakeIntersection(Room r, Direction enteringDir)
	{
		Intersection rInter;
		Intersection tempInter = new Intersection(r, enteringDir.inverse());
		int index = tempInter.isContainedIn(IntersectionFactory.visited);
		
		if(visited.isEmpty())
		{
			//First intersection
			rInter = new Intersection(r);
			IntersectionFactory.visited.add(rInter);
		}
		else if(index < 0)
		{
			//Unvisited intersection
			rInter = tempInter;
			IntersectionFactory.visited.add(rInter);
		}
		else 
		{
			//Previously visited intersection
			rInter = IntersectionFactory.visited.get(index);
		}
		
		return rInter;
	}
}
