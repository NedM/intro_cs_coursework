package shapePackage;

import java.util.ArrayList;
import java.util.List;


//Ned
public class Star extends Shape
{
	private int numberOfPoints;
	
	/**
	 * Creates a Star object
	 * @author nedmurp
	 * @requires heightOrWidth must be positive, numPoints should be >= 3
	 * @effects Constructs a Star object
	 * @param height double : The height and/or width of the star
	 * @param numPoints int : The number of points on the star
	 */
	public Star(double heightOrWidth, int numPoints)
	{
		super(heightOrWidth, heightOrWidth);
		if(numPoints < 3)
			throw new RuntimeException("numPoints must be greater than 2!");
		this.numberOfPoints = numPoints;
	}
	
	/**
	 * Returns the number of points on the star
	 * @author nedmurp
	 * @effects Returns an integer which is the number of points on the shape
	 * @return int : Number of points on the star as an integer
	 */
	public int get_numberOfPoints()
	{
		return this.numberOfPoints;
	}
	
	/**
	 * Returns a list of the coordinates of the points of the star. This is to aid in drawing and is marked package protected
	 * @author nedmurp
	 * @effects generates a list of the coordinates of the points of a triangle
	 * @return List<Point> : a List of Point objects containing the coordinates of the verticies of the star
	 */
	List<Point> get_Points()
	{
		List<Point> points = new ArrayList<Point>();
		double x, y;
		double r = this.get_height()/2;
		double theta = (2 * Math.PI)/this.numberOfPoints;
		
		//We want to define the vertices of the star so that they are evenly spaced around
		//a circle with radius = this.height/2
		for(int i = 0; i < this.numberOfPoints; i++)
		{
			x = -r * Math.sin(i * theta);
			y = r * Math.cos(i * theta);
			points.add(new Point(x, y));
		}
		
		return points;
	}
	
	/**
	 * Builds a list of points defining the star in order. This is to aid in the drawing the star and is marked package protected
	 * @author nedmurp
	 * @effects Creates an ordered list of the points which may be connected to define the star
	 * @return List<Point> : an ordered list of Points which may be connected to define the star
	 */
	List<Point> get_Path()
	{
		Point previous, centroid, current;
		List<Point> points = this.get_Points();
		Point origin = new Point(0, 0);
		List<Point> path = new ArrayList<Point>();
		
		//We want to draw a path such that each of the points of the star are evenly spaced
		// around an imaginary circle with radius = this.height/2. We also want the path to pass through the 
		// centroid of each triangle defined by the nth point, the (n - 1)th point and the center point.
		// the for loop below should perform the necessary calculations and add the points to a list in the correct order
		for(int i = 1; i < this.numberOfPoints; i++)
		{
			previous = points.get(i - 1);			
			current = points.get(i);		
			centroid = Triangle.get_centroid(previous, current, origin);
			if(!path.contains(previous))
				path.add(previous);
			path.add(centroid);
			path.add(current);
		}
		
		//Add the last centroid
		centroid = Triangle.get_centroid(points.get(this.numberOfPoints - 1), points.get(0), origin);
		path.add(centroid);
		
		return path;
	}
	

	/**
	 * Translates the shape into a string of graphics output commands based on the visitor supplied
	 * @author nedmurp
	 * @effects creates a string that can be drawn by the graphics language for this visitor
	 * @param translator ITranslateShape : a shape translator that converts the shape to a string of graphics commands
	 * @param xcenter double : the x coordinate of the center of the star
	 * @param ycenter double : the y coordinate of the center of the star
	 * @return String : graphics commands that will draw the shape
	 */
	@Override
	public String translate(ITranslateShape translator, double xcenter, double ycenter) 
	{
		return translator.translateStar(this, xcenter, ycenter);
	}
}
