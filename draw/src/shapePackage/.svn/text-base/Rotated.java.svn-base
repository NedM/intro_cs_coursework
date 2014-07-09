package shapePackage;

public class Rotated extends Shape
{

	private final Shape shape;
	private final int angle;
	
	/**
	 * Constructor for Rotated class
	 * Rotated Shape
	 * 
	 * @author JVen
	 * @requires angle must be 90, 180, or 270
	 * @param shape Shape : to rotate
	 * @param angle int : (in degrees) by which to rotate shape
	 * @throws RuntimeException if angle is not 90, 180, or 270
	 *  
	 */
	
	public Rotated ( Shape shape, int angle )
	{
		super(getHeight(shape, angle), getWidth(shape, angle));
		this.shape = shape;
		this.angle = angle;
	}
	
	/**
	 * 
	 * Returns height of bounding box of polygon given shape and rotation angle
	 * 
	 * @author JVen
	 * @requires angle must be 90, 180, or 270
	 * @param shape Shape : to rotate
	 * @param angle int : (in degrees) by which to rotate shape
	 * @return double : height of bounding box
	 * @throws RuntimeException if angle is not 90, 180, or 270
	 * 
	 */
	
	private static double getHeight ( Shape shape, int angle )
	{
		switch ( angle )
		{
			case 90:
			case 270:
				return shape.get_width();
			
			case 180:
				return shape.get_height();
				
			default:
				throw new RuntimeException("Invalid rotation angle.");
		}
	}
	
	/**
	 * 
	 * Returns width of bounding box of polygon given shape and rotation angle
	 * 
	 * @author JVen
	 * @param shape Shape : to rotate
	 * @param angle int : (in degrees) by which to rotate shape
	 * @return double : width of bounding box
	 * @throws RuntimeException if angle is not 90, 180, or 270
	 * 
	 */
	
	private static double getWidth ( Shape shape, int angle )
	{
		switch ( angle )
		{
			case 90:
			case 270:
				return shape.get_height();
			
			case 180:
				return shape.get_width();
				
			default:
				throw new RuntimeException("Invalid rotation angle.");
		}
	}
	
	/**
	 * 
	 * Get the shape to be rotated
	 * 
	 * @author JVen
	 * @return Shape : the shape to be rotated
	 * 
	 */
	
	public Shape getShape()
	{
		return shape;
	}
	
	/**
	 * 
	 * Get the rotation angle
	 * 
	 * @author JVen
	 * @return int : the rotation angle
	 * 
	 */
	
	public int getAngle()
	{
		return angle;
	}
	
	/**
	 * 
	 * Translate Rotated to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Rotated
	 * @param xcenter double : x coordinate of the center of the Rotated when drawn
	 * @param ycenter double : y coordinate of the center of the Rotated when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateRotated(this, xcenter, ycenter);
	}
	
}
