package shapePackage;

// Ned
public class Circle extends Shape
{
	private double radius;
	
	/**
	 * Constructor for the Circle Shape object
	 * @author nedmurp
	 * @requires radius must be positive
	 * @effects Creates a Circle object
	 * @param radius double : the radius of the circle object
	 */
	public Circle(double radius)
	{
		super((2*radius), (2*radius));
		this.radius = radius;
	}
	
	/**
	 * Method that returns the radius of the circle
	 * @author nedmurp
	 * @effects Returns the radius of the current circle object
	 * @return double : the Radius of the circle object
	 */
	public double get_Radius()
	{
		return this.radius;
	}
	
	/**
	 * 
	 * Translate Circle to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Circle
	 * @param xcenter double : x coordinate of the center of the Circle when drawn
	 * @param ycenter double : y coordinate of the center of the Circle when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateCircle(this, xcenter, ycenter);
	}
	
}