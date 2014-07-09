package shapePackage;

public class Point 
{
	private double x;
	private double y;
	
	/**
	 * Creates a Point object
	 * @author nedmurp
	 * @effects Constructs a Point object
	 * @param x double : the x coordinate of the point
	 * @param y double : the y coordinate of the point
	 */
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the X coordinate of the current point object
	 * @author nedmurp
	 * @return the X coordinate of the current point object as a double
	 */
	public double getX()
	{
		return this.x;
	}
	
	/**
	 * Returns the Y coordinate of the current point object
	 * @author nedmurp
	 * @return the Y coordinate of the current point object as a double
	 */
	public double getY()
	{
		return this.y;
	}
		
	/**
	 * Adds a point's coordinates to the current point's coordinates
	 * @author nedmurp
	 * @param p Point : the point to add to the current point
	 * @return A new Point which results from the addition operation
	 */
	public Point add(Point p)
	{
		return new Point(this.x + p.getX(), this.y + p.getY());
	}
	
	/**
	 * Subtracts a point's coordinates from the current point's coordinates
	 * @author nedmurp
	 * @param p Point : to the point to subtract from the current point
	 * @return A new Point which results from the subtraction operation
	 */
	public Point subtract(Point p)
	{
		return new Point(this.x - p.getX(), this.y - p.getY());
	}
	
	/**
	 * Overrides the toString method to return a display an coordinate pair: (x,y)
	 * @author nedmurp
	 * @return String : an ordered pair of coordinates: "(x,y)"
	 */
	@Override
	public String toString()
	{
		return "(" + this.getX() + "," + this.getY() + ")";
	}
}
