package shapePackage;


//Julia
public class Rectangle extends Spacer{
	
	/**
	 * Constructs a new rectangle
	 * @author jboortz
	 * @requires height and width should be positive
	 * @effects Constructs a new rectangle
	 * @param height double : to be the height of the bounding box of the shape
	 * @param width double : to be the width of the bounding box of the shape
	 * @throws RuntimeException if height or width are non-positive
	 */
	public Rectangle(double height, double width){
		super(height, width, true);
	}
	
	/**
	 * 
	 * Translate Rectangle to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Rectangle
	 * @param xcenter double : x coordinate of the center of the Rectangle when drawn
	 * @param ycenter double : y coordinate of the center of the Rectangle when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateSpacer(this, xcenter, ycenter);
	}
	
}
