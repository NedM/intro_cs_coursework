package shapePackage;

public class Polygon extends Shape
{
	
	private final int numSides;
	private final double sideLength;
	
	/**
	 * Constructor for Polygon class
	 * Regular polygon Shape
	 * 
	 * @author JVen
	 * @requires numSides must be >= 3 and sideLength must be positive
	 * @param numSides int : number of sides of polygon
	 * @param sideLength double : length of one side of polygon
	 * @throws RuntimeException if numSides < 3 or sideLength is non-positive
	 *  
	 */
	
	public Polygon ( int numSides, double sideLength )
	{
		super(getHeight(numSides, sideLength), getWidth(numSides, sideLength));
		this.numSides = numSides;
		this.sideLength = sideLength;
	}
	
	/**
	 * 
	 * Returns height of bounding box of polygon given numSides and sideLength
	 * Should only be called by constructor
	 * 
	 * @author JVen
	 * @requires numSides must be >= 3 and sideLength must be positive
	 * @param numSides int : number of sides of polygon
	 * @param sideLength double : length of one side of polygon
	 * @return double : height of bounding box
	 * @throws RuntimeException if number of sides is not an integer >= 3 or side length is not > 0
	 * 
	 */
	
	private static double getHeight ( int numSides, double sideLength )
	{
		if ( numSides < 3 )
			throw new RuntimeException("Invalid number of sides for polygon.");
		if ( sideLength <= 0 )
			throw new RuntimeException("Invalid side length.");
		
		switch ( numSides % 4 )
		{
			case 0:
				return sideLength * ( Math.cos(Math.PI / numSides) / Math.sin(Math.PI / numSides) );
				
			case 2:
				return sideLength * ( Math.cos(Math.PI / numSides) / Math.sin(Math.PI / numSides) );
				
			case 1:
			case 3:
				return sideLength * ( ( 1 + Math.cos(Math.PI / numSides) ) / ( 2 * Math.sin(Math.PI / numSides) ) );
				
			default:
				throw new RuntimeException("Error."); // this is unreachable
		}
	}
	
	/**
	 * 
	 * Returns width of bounding box of polygon given numSides and sideLength
	 * Should only be called by constructor
	 * 
	 * @author JVen
	 * @requires numSides must be >= 3 and sideLength must be positive
	 * @param numSides int : number of sides of polygon
	 * @param sideLength double : length of one side of polygon
	 * @return double : width of bounding box
	 * @throws RuntimeException if number of sides is not an integer >= 3 or side length is not > 0
	 * 
	 */
	
	private static double getWidth ( int numSides, double sideLength )
	{
		if ( numSides < 3 )
			throw new RuntimeException("Invalid number of sides for polygon.");
		if ( sideLength <= 0 )
			throw new RuntimeException("Invalid side length.");
		
		switch ( numSides % 4 )
		{				
			case 0:
				return sideLength * ( Math.cos(Math.PI / numSides) / Math.sin(Math.PI / numSides) );
				
			case 2:
				return sideLength * ( 1 / Math.sin(Math.PI / numSides) );
				
			case 1:
			case 3:
				return sideLength * ( Math.sin(Math.PI * (numSides - 1.0) / (2 * numSides)) / Math.sin(Math.PI / numSides) );
				
			default:
				throw new RuntimeException("Error."); // this is unreachable
		}
	}
	
	/**
	 * 
	 * Get the number of sides of the polygon
	 * 
	 * @author JVen
	 * @return int : the number of sides of the polygon
	 * 
	 */
	
	public int getNumSides()
	{
		return numSides;
	}
	
	/**
	 * 
	 * Get the side length of the polygon
	 * 
	 * @author JVen
	 * @return double : the side length of the polygon
	 * 
	 */
	
	public double getSideLength()
	{
		return sideLength;
	}
	
	/**
	 * 
	 * Translate Polygon to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Polygon
	 * @param xcenter double : x coordinate of the center of the Polygon when drawn
	 * @param ycenter double : y coordinate of the center of the Polygon when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translatePolygon(this, xcenter, ycenter);
	}
	
}
