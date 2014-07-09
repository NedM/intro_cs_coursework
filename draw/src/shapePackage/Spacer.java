package shapePackage;

//Julia
public class Spacer extends Shape{
	
	private final boolean visible;
	
	/**
	 * Constructs a new spacer
	 * @author jboortz
	 * @requires height and width should be positive
	 * @effects constructs a new spacer
	 * @param height double : to be the height of the bounding box of the shape
	 * @param width double : to be the width of the bounding box of the shape
	 * @throws RuntimeException if height or width are non-positive
	 */
	public Spacer(double height, double width){
		super(height, width);
		visible = false;
	}
	
	/**
	 * 
	 * Constructs a new spacer with given visibility
	 * @author jboortz, JVen
	 * @effects constructs a new spacer
	 * @param height double : to be the height of the bounding box of the shape
	 * @param width double : to be the width of the bounding box of the shape
	 * @param visible double : visibility of the spacer
	 * 
	 */
	
	protected Spacer(double height, double width, boolean visible)
	{
		super(height, width);
		this.visible = visible;
	}
	
	/**
	 * 
	 * Get visibility of Spacer
	 * 
	 * @author jboortz, JVen
	 * @return boolean : visibility of Spacer
	 * 
	 */
	public boolean get_visible()
	{
		return visible;
	}
	
	/**
	 * 
	 * Translate Spacer to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Spacer
	 * @param xcenter double : x coordinate of the center of the Spacer when drawn
	 * @param ycenter double : y coordinate of the center of the Spacer when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateSpacer(this, xcenter, ycenter);
	}
	
}

