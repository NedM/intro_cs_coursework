package shapePackage;

public class Sierpinsky extends Shape
{

	private final int iterations;
	private final double edgeLength;
	
	/**
	 * 
	 * Sierpinsky's triangle fractal
	 * 
	 * @author JVen
	 * @requires iterations should be positive and <= 10 for sake of runtime, edgeLength must be positive
	 * @param iterations int number of iterations to use when generating fractal
	 * @param edgeLength double length of the outermost triangle of the fractal
	 * @throws RuntimeException if iterations < 1 or iterations > 10 or edgeLength is non-positive 
	 * 
	 */
	
	public Sierpinsky ( int iterations, double edgeLength )
	{
		super(edgeLength * Math.sqrt(3)/2, edgeLength);
		if ( iterations < 1 || iterations > 10 )
			throw new RuntimeException("Invalid number of iterations for fractal.");
		this.iterations = iterations;
		this.edgeLength = edgeLength;
	}
	
	/**
	 * 
	 * Get the number of iterations of the fractal
	 * 
	 * @author JVen
	 * @return int : the number of iterations of the fractal
	 * 
	 */
	
	public int getIterations()
	{
		return iterations;
	}
	
	/**
	 * 
	 * Get the edge length of the outermost triangle of the fractal
	 * 
	 * @author JVen
	 * @return double : the edge length of the outermost triangle of the fractal
	 * 
	 */
	
	public double getEdgeLength()
	{
		return edgeLength;
	}
	
	/**
	 * 
	 * Translate Sierpinsky to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Sierpinsky
	 * @param xcenter double : x coordinate of the center of the Sierpinsky when drawn
	 * @param ycenter double : y coordinate of the center of the Sierpinsky when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateSierpinsky(this, xcenter, ycenter);
	}
	
}
