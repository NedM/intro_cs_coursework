package shapePackage;

//Julia
public class Mountain extends Shape{

	/**
	 * Create a mountain range shape
	 * @author jboortz
	 * @requires numLgPeaks must be positive
	 * @effects create a ski area shape
	 * @param height double : to be the height of the bounding box of the shape
	 * @param width double : to be the width of the bounding box of the shape
	 * @param numLgPeaks int : with numPeaks large peaks and numPeaks-1 small peaks
	 * @throws RuntimeException if numLgPeaks is non-positive
	 */
	
	private int numLgPeaks;
	public Mountain(double height, double width, int numLgPeaks)
	{
		super(height, width);
		if ( numLgPeaks < 1 )
			throw new RuntimeException("numLgPeaks must be positive.");
		this.numLgPeaks = numLgPeaks;
	}
	
	public int get_numLgPeaks(){
		return this.numLgPeaks;
	}
	
	/**
	 * 
	 * Translate NewShape1 to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Mountain
	 * @param xcenter double : x coordinate of the center of the Mountain when drawn
	 * @param ycenter double : y coordinate of the center of the Mountain when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateMountain(this, xcenter, ycenter);
	}
	
}
