package shapePackage;

public class Scaled extends Shape
{

	private final Shape shape;
	private final double fx;
	private final double fy;
	
	/**
	 * Constructor for Scaled class
	 * Scaled Shape
	 * 
	 * @author JVen
	 * @requires scaling factors must be positive
	 * @param shape Shape : to scale
	 * @param fx double : horizontal scaling factor
	 * @param fy double : vertical scaling factor
	 * @throws RuntimeException if scaling factors are non-positive
	 *  
	 */
	
	public Scaled ( Shape shape, double fx, double fy )
	{
		super(getHeight(shape, fy), getWidth(shape, fx));
		this.shape = shape;
		this.fx = fx;
		this.fy = fy;
	}
	
	/**
	 * 
	 * Returns height of bounding box of polygon given shape and vertical scaling factor
	 * NOTE: this method is primarily to check that fy is valid
	 * 
	 * @author JVen
	 * @requires scaling factor must be positive
	 * @param shape Shape : to scale
	 * @param fy double : vertical scaling factor
	 * @return double : height of bounding box
	 * @throws RuntimeException if scaling factor is non-positive
	 * 
	 */
	
	private static double getHeight ( Shape shape, double fy )
	{
		if ( fy <= 0 )
			throw new RuntimeException("Invalid vertical scaling factor.");
		return shape.get_height() * fy;
	}
	
	/**
	 * 
	 * Returns width of bounding box of polygon given shape and horizontal scaling factor
	 * NOTE: this method is primarily to check that fx is valid
	 * 
	 * @author JVen
	 * @requires scaling factor must be positive
	 * @param shape Shape : to scale
	 * @param fx double : horizontal scaling factor
	 * @return double : width of bounding box
	 * @throws RuntimeException if scaling factor is non-positive
	 * 
	 */
	
	private static double getWidth ( Shape shape, double fx )
	{
		if ( fx <= 0 )
			throw new RuntimeException("Invalid horizontal scaling factor.");
		return shape.get_width() * fx;
	}
	
	/**
	 * 
	 * Get the shape to be scaled
	 * 
	 * @author JVen
	 * @return Shape : the shape to be scaled
	 * 
	 */
	
	public Shape getShape()
	{
		return shape;
	}
	
	/**
	 * 
	 * Get the horizontal scaling factor
	 * 
	 * @author JVen
	 * @return double : the horizontal scaling factor
	 * 
	 */
	
	public double getFx()
	{
		return fx;
	}
	
	/**
	 * 
	 * Get the vertical scaling factor
	 * 
	 * @author JVen
	 * @return double : the vertical scaling factor
	 * 
	 */
	
	public double getFy()
	{
		return fy;
	}
	
	/**
	 * 
	 * Translate Scaled to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Scaled
	 * @param xcenter double : x coordinate of the center of the Scaled when drawn
	 * @param ycenter double : y coordinate of the center of the Scaled when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateScaled(this, xcenter, ycenter);
	}
	
}
