package shapePackage;

import java.util.List;

import postscript.commands.*;

public class ToPostScript implements ITranslateShape
{

	private static final double defaultLinewidth = 1.5;
	
	private final boolean fill;
	private final double linewidth;
	
	/**
	 * Construct new ToPostScriptObject
	 * @author jboortz
	 * @effects constructs new ToPostScriptObject
	 */
	public ToPostScript(){
		this(false, defaultLinewidth);
	}
	
	/**
	 * Construct new ToPostScriptObject with user defined fill
	 * @author jboortz
	 * @effects constructs new ToPostScriptObject with user defined fill
	 * @param fill boolean : represents whether or not the object should be filled
	 */
	public ToPostScript(boolean fill){
		this(fill, defaultLinewidth);
	}
	
	/**
	 * Construct new ToPostScriptObject with a user defined linewidth
	 * @author jboortz
	 * @requires linewidth should be positive
	 * @effects constructs new ToPostScriptObject with a user defined linewidth
	 * @param linewidth double : represents what the linewidth of the drawing should be
	 * @throws RuntimeException if linewidth is non-positive
	 */
	public ToPostScript(double linewidth){
		this(false, linewidth);
	}
	/**
	 * Construct new ToPostScriptObject with user defined fill and a user defined linewidth
	 * @author jboortz
	 * @requires linewidth should be positive
	 * @effects constructs new ToPostScriptObject with user defined fill and a user defined linewidth
	 * @param fill boolean : represents whether or not the object should be filled
	 * @param linewidth double : represents what the linewidth of the drawing should be
	 * @throws RuntimeException if linewidth is non-positive
	 */
	public ToPostScript(boolean fill, double linewidth){
		this.fill = fill;
		if ( linewidth <= 0 )
			throw new RuntimeException("Linewidth must be positive.");
		this.linewidth = linewidth;
	}
	
	
	/*
	 * 
	 * 
	 * Where appropriate, the following translate methods should produce a
	 * String that looks roughly like:
	 * 
	 *  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
	 * |                               |
	 * | gsave                         |
	 * | ___ setlinewidth              |
	 * |                               |
	 * | *** COOL SHAPE CODE ***       |
	 * |                               |
	 * | grestore                      |
	 * |_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|
	 * 
	 * This ensures that the current transformation matrix (CTM) is the same
	 * coming out as it came in. Aside from just being a nice invariant,
	 * it also allows us to have uniform linewidths for Scaled shapes.
	 * 
	 * -JVen
	 * 
	 * 
	 */
	
	
	
	/**
	 * generate postscript code for a Spacer
	 * @author jboortz
	 * @effects generate postscript code for a Spacer
	 * @param myRectangle Rectangle : representing the rectangle to generate the postscript code for
	 * @param visible boolean : representing whether the spacer should actually be drawn
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : of postscript code
	 */
	public String translateSpacer(Spacer myRectangle, double xcenter, double ycenter){
		Newpath myNewPath = new Newpath();
		Gsave myGsave = new Gsave();
		
		//Move to lower right corner
		Moveto myMoveto = new Moveto(xcenter-(myRectangle.get_width()/2), ycenter-(myRectangle.get_height()/2));
		
		Rlineto myLower = new Rlineto(myRectangle.get_width(), 0);
		Rlineto myRight = new Rlineto(0, myRectangle.get_height());
		Rlineto myUpper = new Rlineto(myRectangle.get_width() * -1, 0);
		Rlineto myLeft = new Rlineto(0, myRectangle.get_height() * -1);
		
		Stroke myStroke = new Stroke();
		Fill myFill = new Fill();

		Closepath myClosePath = new Closepath();
		Grestore myGRestore = new Grestore();
		
		String psString = myGsave.toString() + 
		(new Setlinewidth(linewidth)).toString() +
		myNewPath.toString() +
		myMoveto.toString() +
		myLower.toString() +
		myRight.toString() +
		myUpper.toString() +
		myLeft.toString() +
		myClosePath.toString();
		
		
		if ( myRectangle.get_visible() )
		{
			if ( fill )
				psString += myFill.toString();
			else
				psString += myStroke.toString();
		}
		
		psString += myGRestore.toString();
		
		return psString;
	}
	
	/**
	 * generate postscript code for a Layered shape
	 * @author jboortz
	 * @effects generate postscript code for a Layered shape
	 * @param myLayered Layered : representing the Layered shape to generate the postscript code for
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : of postscript code
	 */
	public String translateLayered(Layered myLayered, double xcenter, double ycenter){
		String psString = (new Gsave()).toString() + (new Setlinewidth(linewidth)).toString();
		for (Shape s: myLayered.get_listOfShapes()){
			psString = psString + s.translate(this, xcenter, ycenter);
		}
		psString = psString + (new Grestore()).toString();
		
		return psString;
	}
	
	/**
	 * generate postscript code for a NewShape1
	 * @author jboortz
	 * @effects generate postscript code for a NewShape1
	 * @param myNewShape1 NewShape1 : representing the NewShape1 to generate the postscript code for
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : of postscript code
	 */
	public String translateMountain(Mountain myNewShape1, double xcenter, double ycenter){
		Newpath myNewPath = new Newpath();
		Gsave myGsave = new Gsave();
		
		//Move to lower left corner
		double rightx = xcenter - (myNewShape1.get_width()/2);
		double righty = ycenter - (myNewShape1.get_height()/2);
		Moveto myMoveto = new Moveto(rightx, righty);
		
		String psString =
		myGsave.toString() +
		(new Setlinewidth(linewidth)).toString() +
		myNewPath.toString() +
		myMoveto.toString();
		double halfWidPeak = myNewShape1.get_width()/(2*myNewShape1.get_numLgPeaks());
		
		for (int i = 0; i < myNewShape1.get_numLgPeaks(); i++){
			Rlineto peakL = new Rlineto(halfWidPeak, myNewShape1.get_height());
			Rlineto peakR = new Rlineto(halfWidPeak, myNewShape1.get_height()*-1);
			psString = psString + peakL + peakR;
		}
		
		for (int i = 0; i < myNewShape1.get_numLgPeaks() -1; i++){
			Moveto loc = new Moveto(rightx+1.5*halfWidPeak+2*halfWidPeak*i, righty+myNewShape1.get_height()/2);
			Rlineto peakL = new Rlineto(halfWidPeak/2.0,myNewShape1.get_height()/2);
			Rlineto peakR = new Rlineto(halfWidPeak/2.0,-1* myNewShape1.get_height()/2);
			psString = psString + loc + peakL + peakR;
		}
		Stroke myStroke = new Stroke();
		Fill myFill = new Fill();

		Grestore myGRestore = new Grestore();

		if ( fill )
			psString += myFill.toString();
		else
			psString += myStroke.toString();
		
		psString += myGRestore.toString();
				
		return psString;

	}

	/**
	 * 
	 * Generate postscript code for a Polygon shape
	 * 
	 * @author JVen
	 * @param polygon Polygon : shape to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : representing polygon postscript code
	 * 
	 */
	
	public String translatePolygon ( Polygon polygon, double xcenter, double ycenter )
	{
		int n = polygon.getNumSides();
		double e = polygon.getSideLength();
		double x = xcenter;
		double y = ycenter;
		double h = polygon.get_height();
		//double w = polygon.get_width(); // this guy is not used
		
		String ans = 
		(new Gsave()).toString() +
		(new Setlinewidth(linewidth)).toString() +
		(new Newpath()).toString() +
		(new Moveto(x - e / 2, y - h / 2)).toString();
		
		for ( int i = 1 ; i <= n ; i++ )
			ans += (new Rlineto(e, 0)).toString() + (new Rotate(360 / n));
		
		ans += (new Closepath()).toString();

		if ( fill )
			ans += (new Fill()).toString();
		else
			ans += (new Stroke()).toString();
		
		ans += (new Grestore()).toString();
		
		return ans;
	}
	
	/**
	 * 
	 * Generate postscript code for a Rotated shape
	 * 
	 * @author JVen
	 * @param rotated Rotated : shape to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : representing Rotated postscript code
	 * 
	 */
	
	public String translateRotated ( Rotated rotated, double xcenter, double ycenter )
	{
		Shape s = rotated.getShape();
		int a = rotated.getAngle();
		double x = xcenter;
		double y = ycenter;
		
		String ans = (new Gsave()).toString() + (new Setlinewidth(linewidth)).toString();
		
		switch ( a ) // we translate after rotation, so we must correct beforehand!
		{
			case 90:
				ans += (new Translate(x + y, y - x)).toString();
				break;
				
			case 180:
				ans += (new Translate(2 * x, 2 * y)).toString();
				break;
				
			case 270:
				ans += (new Translate(x - y, x + y)).toString();
				break;
		}
		
		ans += (new Rotate(rotated.getAngle())).toString() + s.translate(this, x, y) + (new Grestore()).toString();
		
		return ans;
	}
	
	/**
	 * 
	 * Generate postscript code for a Scaled shape
	 * 
	 * @author JVen
	 * @param scaled Scaled : shape to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : representing Scaled postscript code
	 * @see <a href="http://www.redgrittybrick.org/postscript/ellipse.html">http://www.redgrittybrick.org/postscript/ellipse.html</a>
	 * 
	 */
	
	public String translateScaled ( Scaled scaled, double xcenter, double ycenter )
	{
		Shape s = scaled.getShape();
		double x = xcenter;
		double y = ycenter;
		double fx = scaled.getFx();
		double fy = scaled.getFy();
		
		String ans =
		(new Gsave()).toString() +
		(new Setlinewidth(linewidth)).toString() +
		(new FixScaleDef()).toString() +
		(new Newpath()).toString() +
		(new Translate((1 - fx) * x, (1 - fy) * y)).toString() +
		(new Scale(fx, fy)).toString() +
		(s.translate(this, x, y)).replaceAll((new Stroke()).toString(), (new FixScale()).toString() + (new Stroke()).toString()) +
		(new Closepath()).toString() +
		(new Grestore()).toString();
		
		// for nested scaleds, we need to delete duplicate FixScales... yes this is a bit of a hack -JVen
		
		//ans = ans.replaceAll((new FixScale()).toString() + (new FixScale()).toString(), (new FixScale()).toString());
		
		return ans;
	}
	
	/**
	 * Generate PostScript code for Circle shape
	 * @author nedmurp
	 * @param Circle myCircle : Circle to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : of PostScript code that will draw a circle shape
	 */
	public String translateCircle(Circle myCircle, double xcenter, double ycenter)
	{
		Gsave myGsave = new Gsave();
		Translate myTranslate = new Translate(xcenter, ycenter);
		Arc myArc = new Arc(0, 0, myCircle.get_Radius(), 0, 360);
		Stroke myStroke = new Stroke();
		Fill myFill = new Fill();
		Grestore myGRestore = new Grestore();
		
		String psString =
					myGsave.toString() +
					(new Setlinewidth(linewidth)).toString() +
					myTranslate.toString() +
					myArc.toString();
		
		if ( fill )
			psString += myFill.toString();
		else
			psString += myStroke.toString();
		
		psString += myGRestore.toString();
					
		return psString;		
	}
	
	/**
	 * Generate PostScript code for Horizontal shape
	 * 
	 * @author nedmurp, JVen
	 * @param myHorizontal Horizontal : shape to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : representing Horizontal postscript code
	 */
	public String translateHorizontal ( Horizontal myHorizontal, double xcenter, double ycenter )
	{
		double x = xcenter;
		double y = ycenter;
		double w = myHorizontal.get_width();
		double currX = x - (w / 2);
		
		String psString = (new Gsave()).toString() + (new Setlinewidth(linewidth)).toString();
		
		for ( Shape s : myHorizontal.get_listOfShapes() )
		{
			currX += s.get_width() / 2;
			psString += (new Gsave()).toString();
			psString += s.translate(this, currX, y); //Concatenate the PostScript for each shape according to its specific construction
			psString += (new Grestore()).toString();
			currX += s.get_width() / 2;
		}
		
		psString += (new Grestore()).toString();
		
		return psString;
	}
	
	/**
	 * Generate PostScript code for Vertical shape
	 * 
	 * @author nedmurp, JVen	
	 * @param myVertical Vertical : shape to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : representing Vertical postscript code
	 */
	
	public String translateVertical ( Vertical myVertical, double xcenter, double ycenter )
	{
		double x = xcenter;
		double y = ycenter;
		double h = myVertical.get_height();
		double currY = y - (h / 2);
		
		String psString = (new Gsave()).toString() + (new Setlinewidth(linewidth)).toString();
		
		for ( Shape s : myVertical.get_listOfShapes() )
		{
			currY += s.get_height() / 2;
			psString += (new Gsave()).toString();
			psString += s.translate(this, x, currY); //Concatenate the PostScript for each shape according to its specific construction
			psString += (new Grestore()).toString();
			currY += s.get_height() / 2;
		}
		
		psString += (new Grestore()).toString();
		
		return psString;
	}
	
	/**
	 * Generate PostScript code for Star shape
	 * @author nedmurp
	 * @param Star myStar : the Star object to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * 
	 * @return String : of PostScript code that will draw the Star shape
	 */
	public String translateStar(Star myStar, double xcenter, double ycenter)
	{
		List<Point> myPath = myStar.get_Path();
		double startDrawY = myPath.get(0).getY();
		Gsave myGsave = new Gsave();
		Translate myTranslate = new Translate(xcenter, ycenter);
		Moveto myMoveTo = new Moveto(0, startDrawY);
		Lineto myLineTo;
		Newpath startPath = new Newpath();
		Closepath endPath = new Closepath();
		Stroke myStroke = new Stroke();
		Fill myFill = new Fill();
		Grestore myGRestore = new Grestore();
		
		
		String psStar = "";
		for(int i = 1; i < myPath.size(); i++)
		{
			myLineTo = new Lineto(myPath.get(i).getX(), myPath.get(i).getY());
			psStar = psStar.concat(myLineTo.toString());
		}
		
		String psString =
					myGsave.toString() +
					(new Setlinewidth(linewidth)).toString() +
					myTranslate.toString() +
					startPath.toString() + 
					myMoveTo.toString() + 					
					psStar +
					endPath.toString();
		
		if(fill)
			psString += myFill.toString();
		else
			psString += myStroke.toString();
		
					
		psString += myGRestore.toString();
					
		return psString;
	}
	
	/**
	 * 
	 * Generate postscript code for a Sierpinsky shape
	 * 
	 * @author JVen
	 * @param s Sierpinsky shape to be translated
	 * @param xcenter double : the x coordinate of the center around which to draw the shape
	 * @param ycenter double : the y coordinate of the center around which to draw the shape
	 * @return String : representing Sierpinsky postscript code
	 * 
	 */
	
	public String translateSierpinsky ( Sierpinsky s, double xcenter, double ycenter )
	{
		int d = s.getIterations();
		double e = s.getEdgeLength();
		double x = xcenter;
		double y = ycenter;
		double w = s.get_width();
		double h = s.get_height();
		
		/*
		 * Denote by S_n a Sierpinsky triangle with n iterations, where S_1 is just a triangle.
		 * Then S_n looks like:
		 * 
		 *  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
		 * |        |         /\         |        |
		 * |        |        /  \        |        |
		 * |        |       /    \       |        |
		 * |        |      /      \      |        |
		 * |        |     /   o    \     |        |
		 * |        |    /(x,y+h/4) \    |        |
		 * |        |   /            \   |        |
		 * |        |  /    S_n-1     \  |        |
		 * |        | /                \ |        |
		 * |_ _ _ _ |/_ _ _ _ o_ _ _ _ _\|_ _ _ _ |
		 * |        /\      (x,y)      / \        |
		 * |       /  \       |       /   \       |
		 * |      /    \      |      /     \      |
		 * |     /      \     |     /       \     |
		 * |    / S_n-1  \    |    /  S_n-1  \    |
		 * |   /    o     \   |   /     o     \   |
		 * |  /            \  |  /             \  |
		 * | /(x-w/4,y-h/4) \ | / (x+w/4,y-h/4) \ |
		 * |/_ _ _ _ _ _ _ _ \|/_ _ _ _ _ _ _ _ _\|
		 * 
		 * -JVen
		 * 
		 */
		
		String ans = (new Gsave()).toString() + (new Setlinewidth(linewidth)).toString();
		
		if ( d == 1 )
		{
			ans += (new Triangle(e)).translate(this, x, y);
		}
		else
		{
			Sierpinsky newS = new Sierpinsky(d - 1, e / 2);
			ans += newS.translate(this, x, y + h / 4) +
				   newS.translate(this, x - w / 4, y - h / 4) +
				   newS.translate(this, x + w / 4, y - h / 4);
		}
		
		ans += (new Grestore()).toString();
		
		return ans;
		
	}
	
}

