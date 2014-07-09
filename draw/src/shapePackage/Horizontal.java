package shapePackage;

import java.util.*;

public class Horizontal extends CompoundShape
{
	/**
	 * 
	 * Constructor for the Horizontal CompoundShape object
	 * NOTE: the input shape list contains pointers to the original shapes,
	 * we do not create new shape instances with new centers
	 * NOTE: objects are placed left to right
	 * 
	 * @author nedmurp, JVen
	 * @requires listOfShapes must not be null
	 * @param listOfShapes List<Shape> : A list of the shape objects that compose the Compound object
	 * @throws RuntimeException if listOfShapes is null
	 * 
	 */
	
	public Horizontal (List<Shape> listOfShapes )
	{
		super(listOfShapes, CompoundShape.get_maxHeight(listOfShapes), CompoundShape.get_totalWidth(listOfShapes));
	}
	
	/**
	 * Constructor for Horizontal CompoundShape object
	 * NOTE: The input shape list contains pointers to the original shapes, we do not create new shape instances with new centers
	 * NOTE: the Shapes are placed left to right
	 * @author nedmurp
	 * @param shapes Shape[] : A number of shape objects that compose the Compound Object
	 */
	public Horizontal(Shape...shapes)
	{
		this(CompoundShape.arrayToList(shapes));
	}
	
	/**
	 * 
	 * Translate Horizontal to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Horizontal
	 * @param xcenter double : x coordinate of the center of the Horizontal when drawn
	 * @param ycenter double : y coordinate of the center of the Horizontal when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateHorizontal(this, xcenter, ycenter);
	}
	
}
