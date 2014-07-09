package shapePackage;

import java.util.*;

public class Vertical extends CompoundShape
{
	/**
	 * 
	 * Constructor for the Vertical CompoundShape object
	 * NOTE: the input shape list contains pointers to the original shapes,
	 * we do not create new shape instances with new centers
	 * NOTE: objects are stacked bottom to top
	 * 
	 * @author nedmurp, JVen
	 * @requires listOfShapes must not be null
	 * @param listOfShapes List<Shape> : A list of the shape objects that compose the Compound object
	 * @throws RuntimeException if listOfShapes is null
	 * 
	 */
	
	public Vertical (List<Shape> listOfShapes)
	{
		super(listOfShapes, CompoundShape.get_totalHeight(listOfShapes), CompoundShape.get_maxWidth(listOfShapes));
	}
	
	/**
	 * Constructor for Vertical CompoundShape object
	 * NOTE: The input shape list contains pointers to the original shapes, we do not create new shape instances with new centers
	 * NOTE: the Shapes are placed bottom to top
	 * @author nedmurp
	 * @param shapes Shape[] : An number of shape objects that compose the Compound Object
	 */
	public Vertical (Shape...shapes)
	{
		this(CompoundShape.arrayToList(shapes));
	}
	
	/**
	 * 
	 * Translate Vertical to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Vertical
	 * @param xcenter double : x coordinate of the center of the Vertical when drawn
	 * @param ycenter double : y coordinate of the center of the Vertical when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateVertical(this, xcenter, ycenter);
	}
	
}
