package shapePackage;
// Super class Shape

public abstract class Shape {

	private final double height;
	private final double width;
	
	/**
	 * Construct a new shape
	 * @requires height and width should be positive
	 * @effects Constructs a shape object
	 * @param height double :  to be the height of the bounding box of the shape
	 * @param width double : to be the width of the bounding box of the shape
	 * @throws RuntimeException if height or width are non-positive
	 */
	protected Shape(double height, double width){
		if ( height <= 0 )
			throw new RuntimeException("Height must be positive.");
		if ( width <= 0 )
			throw new RuntimeException("Width must be positive.");
		this.height = height;
		this.width = width;
	}
	
	/**
	 * Get the height of the bounding box of the shape
	 * @effects returns the height of the bounding box of the shape
	 * @return double : height of the bounding box of the shape
	 */
	public double get_height(){
		return this.height;
	}
	
	/**
	 * Get the width of the bounding box of the shape
	 * @effects returns the width of the bounding box of the shape
	 * @return double : width of the bounding box of the shape
	 */
	public double get_width(){
		return this.width;
	}
	
	/**
	 * 
	 * Translate shape to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language from Shape
	 * @param xcenter double : x coordinate of the center of the Shape when drawn
	 * @param ycenter double : y coordinate of the center of the Shape when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public abstract String translate ( ITranslateShape translator, double xcenter, double ycenter );
	
}

