package maze;

import static maze.Direction.EAST;
import static maze.Direction.NORTH;
import static maze.Direction.SOUTH;
import static maze.Direction.WEST;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import solver.ImprovedSolver;
import solver.Intersection;
import solver.WallFollower;

public class MazeGame {

	public static final Color ROOM_COLOR = Color.darkGray;
	public static final Color PLAYER_COLOR = Color.red;	
	public static final Color PATH_COLOR = Color.green;
	
	public static final int ROOM_SIZE = 40;
	public static final int WALL_THICKNESS = 6;
	public static final int MARGIN = 20;
	public static final Color WALL_COLOR = Color.black;	

	private int minX=0,minY=0,maxX=0,maxY=0;
	private int numSteps=0;
	
	private MazeSolver mazeSolver;
	private Maze maze;
	private Room currentRoom;
	private Component view;


	public void drawWall(Graphics g, int x, int y, int w, int h) {
		g.setColor(WALL_COLOR); 
		g.fillRect(x, y, w, h); 
	}
	
	public Dimension getDimension() {
		Dimension dim = new Dimension(0,0);
		if (maze.rooms.size() > 0) {
			for (Room r : maze.rooms) {
				int x = r.getX();
				int y = r.getY();
				if (x > maxX) maxX = x;
				if (x < minX) minX = x;
				if (y > maxY) maxY = y;
				if (y < minY) minY = y;
			}
			dim = new Dimension(maxX - minX + 1, maxY - minY + 1);
		}
		return dim;
	}

	public void draw(Graphics g) 
	{
		Dimension dim = getDimension();
		int dx = MARGIN;
		int dy = MARGIN;
		// draw rooms first
		for (Room room: maze.rooms) 
		{
			int x = dx + (room.getX()-minX) * ROOM_SIZE;
			int y = dy + (maxY-room.getY()) * ROOM_SIZE;
		    g.setColor(ROOM_COLOR);
		    if (room instanceof EnchantedRoom)
		    	g.setColor(Color.getHSBColor(.1f*(numSteps+2*room.getX()+3*room.getY()), .3f, 1f));
		    if (room == maze.getExit())
		    	g.setColor(Color.green);
		    g.fillRect(x, y, ROOM_SIZE, ROOM_SIZE); 
		    if (room==currentRoom) { 
		      g.setColor(Color.black);
		      g.fillOval(x + ROOM_SIZE / 2 - 7, y + ROOM_SIZE / 2 - 7, 14, 14); 
		      g.setColor(PLAYER_COLOR);
		      g.fillOval(x + ROOM_SIZE / 2 - 5, y + ROOM_SIZE / 2 - 5, 10, 10); 
		    }
		}

		// draw walls
		for (Room room : maze.rooms) {
			if (room.hasWall(Direction.NORTH))
				drawWall(g,
					 	 dx + (room.getX()-minX) * ROOM_SIZE - WALL_THICKNESS / 2,
					 	 dy + (maxY-room.getY()) * ROOM_SIZE - WALL_THICKNESS / 2,
						 ROOM_SIZE + WALL_THICKNESS, 
						 WALL_THICKNESS);
			if (room.hasWall(Direction.EAST))
				drawWall(g,
						 dx + (room.getX()-minX) * ROOM_SIZE + ROOM_SIZE - WALL_THICKNESS / 2,
						 dy + (maxY-room.getY()) * ROOM_SIZE - WALL_THICKNESS / 2,
						 WALL_THICKNESS, 
						 ROOM_SIZE + WALL_THICKNESS); 
			if (room.hasWall(Direction.SOUTH))
				drawWall(g,
						 dx + (room.getX()-minX) * ROOM_SIZE - WALL_THICKNESS / 2,
						 dy + (maxY-room.getY()) * ROOM_SIZE + ROOM_SIZE - WALL_THICKNESS / 2,
						 ROOM_SIZE + WALL_THICKNESS, 
						 WALL_THICKNESS);
			if (room.hasWall(Direction.WEST))
				drawWall(g,
						 dx + (room.getX()-minX) * ROOM_SIZE - WALL_THICKNESS / 2,
						 dy + (maxY-room.getY()) * ROOM_SIZE - WALL_THICKNESS / 2,
						 WALL_THICKNESS, 
						 ROOM_SIZE + WALL_THICKNESS);
		} 
	}
	
	private void drawPath(Graphics g, Collection<Intersection> interCollect)
	{
		g.setColor(PATH_COLOR);
		for(Intersection i : interCollect)
		{
			int x = MARGIN + ((i.getX()-minX) * ROOM_SIZE) + ((ROOM_SIZE / 2) - 5);
			int y = MARGIN + ((maxY-i.getY()) * ROOM_SIZE) + ((ROOM_SIZE / 2) - 5);
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

			g.fillOval(x, y, 15, 15);			
		}
	}
	
	public MazeGame(Maze maze, MazeSolver solver) {
		this.maze = maze;
		this.mazeSolver = solver;
		currentRoom = maze.getRoom(0, 0);
	}

	public boolean step() {
		++numSteps;
		Direction nextDirection = mazeSolver.visit(currentRoom);
		
		int x = currentRoom.getX();
		int y = currentRoom.getY();
		
		if (currentRoom.hasWall(nextDirection)) {
			if (view != null)
				view.repaint();
			return false;
		}
		x += nextDirection.getDx();
		y += nextDirection.getDy();

		Room nextRoom = maze.getRoom(x, y);
		if (nextRoom != null) {
			currentRoom = nextRoom;
		}
		
		if (view != null)
			view.repaint();

		return currentRoom == maze.getExit();

	}

	protected void setView(Component view) {
		this.view = view; 
	}

	private void showFrame(String frameTitle) {
		JFrame frame;    
		frame = new JFrame(frameTitle);
		frame.setContentPane(new MazePanel(this,ROOM_SIZE,MARGIN)); 
		frame.pack();
		Dimension frameDim = frame.getSize(); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frameDim.width / 2, 
				screenSize.height / 2 - frameDim.height / 2);    
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Maze m = new Maze();
		
//		Room r = m.getRoom(0,0);
////		dig NORTH, NORTH, EAST, SOUTH, back up, dig  EAST, SOUTH, SOUTH, WEST, WEST
//		r = m.dig(m.dig(m.dig(r,NORTH),NORTH),EAST);
//		m.dig(r,SOUTH);
//		r = m.dig(m.dig(m.dig(r,EAST),SOUTH),SOUTH);
//		m.setExit(r);
//		m.dig(m.dig(r,WEST),WEST);
		
//		m.makeExample2();
		m.makeExample3();
		
//		MazeSolver solver = new WallFollower();
		MazeSolver solver = new ImprovedSolver();
		MazeGame mazeGame = new MazeGame(m, solver);
		mazeGame.showFrame("Amazed");
		while(mazeGame.currentRoom != m.getExit())
		{
			mazeGame.step();
		}
		
		//For debugging purposes
//		System.out.println(mazeGame.numSteps + " steps to solve");
//		mazeGame.drawPath(mazeGame.view.getGraphics(), ((ImprovedSolver)solver).getRoute());
	}


}
