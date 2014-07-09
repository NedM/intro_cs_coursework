package maze;

public enum Direction {
	NORTH("North",0,1),
	EAST("East",1,0),
	SOUTH("South",0,-1),
	WEST("West",-1,0);

	private final String name;
	private int dx, dy;
	
	private Direction(String name, int dx, int dy) { 
		this.name = name; 
		this.dx = dx;
		this.dy = dy;
	}
	
	public Direction right() {
		return Direction.values()[(ordinal()+1)%4];
	}

	public Direction inverse() {
		return right().right();
	}
	
	public Direction left() {
		return inverse().right();
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
	
	public String toString() { 
		return name; 
	}

}