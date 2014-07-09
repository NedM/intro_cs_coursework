package shapePackage;

import java.util.List;

//Julia
public class Layered extends CompoundShape{
	
	/**
	 * @author jboortz
	 * @effects constructs a new layered object
	 * @requires listOfShapes must not be null
	 * @param listOfShapes List<Shapes> : to be a list of the shapes to be layered to make the layered shape
	 * @param height double : to be the height of the bounding box of the shape
	 * @param width double : to be the width of the bounding box of the shape
	 * @throws RuntimeException if listOfShapes is null
	 */
	public Layered(List<Shape> listOfShapes)
	{
		super(listOfShapes, findMaxHeight(listOfShapes), findMaxWidth(listOfShapes));
	}
	
	/**
	 * Constructor for Layered CompoundShape object
	 * NOTE: The input shape list contains pointers to the original shapes, we do not create new shape instances with new centers
	 * NOTE: the Shapes are placed one on top of the next
	 * @author nedmurp
	 * @param shapes Shape[] : An array of the shape objects that compose the Compound Object
	 */
	public Layered(Shape...shapes)
	{
		this(CompoundShape.arrayToList(shapes));
	}
		
	/**
	 * @author jboortz
	 * @effects return the maximum height of the bounding box of any shape in the list
	 * @param listOfShapes : List<Shape> takes in a list of shapes that will compose the layered shape
	 * @return double representing the maximum height of the bounding box of any shape in the list
	 */
	private static double findMaxHeight(List<Shape> listOfShapes){
		double max = 0.0;
		for (Shape s : listOfShapes){
			if (s.get_height() > max){
				max = s.get_height();
			}
		}
		return max;
	}
	
	/**
	 * @author jboortz
	 * @effects return the maximum width of the bounding box of any shape in the list
	 * @param listOfShapes List<Shape> : takes in a list of shapes that will compose the layered shape
	 * @return double representing the maximum width of the bounding box of any shape in the list
	 */
	private static double findMaxWidth(List<Shape> listOfShapes){
		double max = 0.0;
		for (Shape s : listOfShapes){
			if (s.get_width() > max){
				max = s.get_width();
			}
		}
		return max;
	}
	
	/**
	 * 
	 * Translate Layered to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from Layered
	 * @param xcenter double : x coordinate of the center of the Layered when drawn
	 * @param ycenter double : y coordinate of the center of the Layered when drawn
	 * @return String : string representing graphics language code
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		return translator.translateLayered(this, xcenter, ycenter);
	}
	
}
