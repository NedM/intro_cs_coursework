package maze;

import java.util.HashSet;
import java.util.Set;

public class Room {
	private int x, y;
	protected Set<Direction> walls;
	public boolean hasWall(Direction dir) {
		return walls.contains(dir);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Room(int x, int y) {
		this.x = x;
		this.y = y;
		walls = new HashSet<Direction>();
		walls.add(Direction.NORTH);
		walls.add(Direction.EAST);
		walls.add(Direction.SOUTH);
		walls.add(Direction.WEST);
	}
	protected void removeWall(Direction dir) {
		walls.remove(dir);
	}
}
