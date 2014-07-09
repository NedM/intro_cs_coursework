package maze;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the Maze
 */
public class MazeTest {
	
	private Maze maze1;
	
	@Before public void createMazes(){
		maze1 = new Maze();
		Room r = maze1.getRoom(0, 0);
		maze1.dig(maze1.dig(maze1.dig(r, Direction.NORTH),Direction.EAST),Direction.SOUTH);
	}
	
	@Test public void testMazeRooms()
	{
		Room r = maze1.getRoom(0, 0);
		Assert.assertNotNull(r);
		r = maze1.getRoom(0, 1);
		Assert.assertNotNull(r);
		r = maze1.getRoom(1, 1);
		Assert.assertNotNull(r);
		r = maze1.getRoom(1, 0);
		Assert.assertNotNull(r);
		
		r = maze1.getRoom(-1, 0);
		Assert.assertNull(r);
		r = maze1.getRoom(0, -1);
		Assert.assertNull(r);
		r = maze1.getRoom(2, 0);
		Assert.assertNull(r);
		r = maze1.getRoom(0, -2);
		Assert.assertNull(r);
	}
	
	@Test public void testMazeWalls()
	{
		Room r = maze1.getRoom(0, 0);
		Assert.assertFalse(r.hasWall(Direction.NORTH));
		Assert.assertTrue(r.hasWall(Direction.SOUTH));
		Assert.assertTrue(r.hasWall(Direction.EAST));
		Assert.assertTrue(r.hasWall(Direction.WEST));
		r = maze1.getRoom(0, 1);
		Assert.assertTrue(r.hasWall(Direction.NORTH));
		Assert.assertFalse(r.hasWall(Direction.SOUTH));
		Assert.assertFalse(r.hasWall(Direction.EAST));
		Assert.assertTrue(r.hasWall(Direction.WEST));
		r = maze1.getRoom(1, 1);
		Assert.assertTrue(r.hasWall(Direction.NORTH));
		Assert.assertFalse(r.hasWall(Direction.SOUTH));
		Assert.assertTrue(r.hasWall(Direction.EAST));
		Assert.assertFalse(r.hasWall(Direction.WEST));
		r = maze1.getRoom(1, 0);
		Assert.assertFalse(r.hasWall(Direction.NORTH));
		Assert.assertTrue(r.hasWall(Direction.SOUTH));
		Assert.assertTrue(r.hasWall(Direction.EAST));
		Assert.assertTrue(r.hasWall(Direction.WEST));
	}

}
