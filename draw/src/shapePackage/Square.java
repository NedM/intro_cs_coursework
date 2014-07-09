package shapePackage;


//Julia
public class Square extends Rectangle{
	
	/**
	 * Constructs a new square
	 * @author jboortz
	 * @requires sideLen must be positive
	 * @effects constructs a new square
	 * @param sideLen double : to be the length of the sides 
	 * @throws RuntimeException if sideLen is non-positive
	 */
	public Square(double sideLen){
		super(sideLen, sideLen);
	}
	
	/**
	 * 
	 * Translate Square to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Square
	 * @param xcenter double : x coordinate of the center of the Square when drawn
	 * @param ycenter double : y coordinate of the center of the Square when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateSpacer(this, xcenter, ycenter);
	}
	
}
