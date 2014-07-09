package shapePackage;

import java.util.*;

//Julia

public abstract class CompoundShape extends Shape{
	
	private final List<Shape> listOfShapes;
	
	/**
	 * Construct a new Compound shape. Initialize height, width.
	 * @author jboortz
	 * @requires listOfShapes must neither be null nor empty
	 * @effects construct a new compound shape.  Initialize height, width.
	 * @param listOfShapes ArrayList<Shape>  which is a list of shapes to take in to create a compound shape
	 * @param height double height of the bounding box of the CompoundShape
	 * @param width double width of the bounding box of the CompoundShape
	 * @throws RuntimeException if listOfShapes is null or empty
	 */
	protected CompoundShape(List<Shape> listOfShapes, double height, double width){
		super(height, width);
		if ( listOfShapes == null || listOfShapes.size() == 0 )
			throw new RuntimeException("listOfShapes must neither be null nor empty.");
		this.listOfShapes = new ArrayList<Shape>(listOfShapes);
	}
	
	/** 
	 * Return a list of the shapes in the compound object
	 * @author jboortz
	 * @effects return a list of the shapes in the compound object
	 * @return ArrayList<Shape> a list of the shapes in the compound object
	 */
	public List<Shape> get_listOfShapes(){
		return new ArrayList<Shape>(listOfShapes);
	}
	
	/**
	 * Gets the maximum width from among the shapes in the list specified
	 * @author nedmurp
	 * @param shapeList List<Shape> : the list of shapes whose widths should be searched for the max
	 * @return the maximum width from among the shapes in the list as a double
	 */
	static double get_maxWidth(List<Shape> shapeList)
	{
		double maxWidth = 0.0;
		
		//If the current shape is wider than the current widest,
		//make the max width the width of the current shape
		for(Shape s : shapeList)
		{
			if(s.get_width() > maxWidth)
				maxWidth = s.get_width();
		}
		
		return maxWidth;
	}
	
	/**
	 * Adds up the widths of all the shapes in the list specified
	 * @author nedmurp
	 * @param shapeList List<Shape>: The list of shapes whose widths to add
	 * @return the total width of all the shapes in the list as a double
	 */
	static double get_totalWidth(List<Shape> shapeList)
	{
		double tempWidth = 0.0;
		
		//Add the width of each shape in the list to the total width of the Horizontal object
		for(Shape s : shapeList)
		{
			tempWidth += s.get_width();
		}
		return tempWidth;
	}

	/**
	 * Gets the maximum height from among the shapes in the list specified
	 * @author nedmurp
	 * @param shapeList List<Shape>: the list of shapes whose heights should be searched for the max
	 * @return the maximum height from among the shapes in the list as a double
	 */
	static double get_maxHeight(List<Shape> shapeList)
	{
		double tempMaxHeight = 0.0;
		
		//If the current shape is taller than the tallest shape,
		//make the max height the height of the current shape
		for(Shape s : shapeList)
		{
			if(s.get_height() > tempMaxHeight)
				tempMaxHeight = s.get_height();
		}
		
		return tempMaxHeight;
	}
	
	/**
	 * Adds up the heights of all the shapes in the list specified
	 * @author nedmurp
	 * @param shapeList List<Shape>: The list of shapes whose heights to add
	 * @return the total height of all the shapes in the list as a double
	 */
	static double get_totalHeight(List<Shape> shapeList)
	{
		double totalHeight = 0.0;
		
		//Add the height of the current shape to the total height
		for(Shape s : shapeList)
		{
			totalHeight += s.get_height();
		}
		return totalHeight;
	}
	
	/**
	 * Converts an array of shapes to a List<Shape>
	 * @author nedmurp
	 * @param shapeArray Shape[] : an array of shape objects
	 * @return A List of Shape objects
	 */
	public static List<Shape> arrayToList(Shape[] shapeArray)
	{
		List<Shape> shapeList = new ArrayList<Shape>();
		for(Shape s : shapeArray)
		{
			shapeList.add(s);
		}
		
		return shapeList;
	}
	
	/**
	 * 
	 * Translate CompoundShape to graphics language code
	 * Follows visitor design pattern
	 * 
	 * @author JVen
	 * @param translator ITranslateShape : translator that creates graphics language code from CompoundShape
	 * @param xcenter double : x coordinate of the center of the CompoundShape when drawn
	 * @param ycenter double : y coordinate of the center of the CompoundShape when drawn
	 * @return String : string representing graphics language code
	 * @throws RuntimeException if subclass does not override this method
	 * 
	 */
	
	public String translate ( ITranslateShape translator, double xcenter, double ycenter )
	{
		throw new RuntimeException("Subclass of CompoundShape does not override translate.");
	}
	
}
