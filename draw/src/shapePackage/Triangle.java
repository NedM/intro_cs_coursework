package shapePackage;

public class Triangle extends Polygon
{

	/**
	 * 
	 * Constructor for Triangle class
	 * Equilateral triangle Shape
	 * 
	 * @author JVen
	 * @param sideLength double : length of one side of triangle
	 * 
	 */
	
	public Triangle( double sideLength )
	{
		super(3, sideLength);
	}
	
	
	/**
	 * 
	 * Translate Triangle to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Triangle
	 * @param xcenter double : x coordinate of the center of the Triangle when drawn
	 * @param ycenter double : y coordinate of the center of the Triangle when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translatePolygon(this, xcenter, ycenter);
	}
	
	/**
	 * Returns the centroid of the triangle defined by the three points passed in
	 * @author nedmurp
	 * @param a Point : a vertex of the triangle
	 * @param b Point : another vertex of the triangle
	 * @param c Point : the final vertex of the triangle
	 * @return the centroid of the triangle as a Point
	 */
	public static Point get_centroid(Point a, Point b, Point c)
	{
		double x = (a.getX() + b.getX() + c.getX())/3;
		double y = (a.getY() + b.getY() + c.getY())/3;
		return new Point(x, y);
	}
}
