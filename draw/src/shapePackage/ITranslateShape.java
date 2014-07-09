package shapePackage;

public interface ITranslateShape 
{
	
	/**
	 * 
	 * Translates a Shape from the shape library into graphics language code
	 * Uses visitor design pattern
	 * 
	 * @param s Shape the shape to translate to graphics language
	 * @param xcenter the x coordinate of the center around which to draw the shape
	 * @param ycenter the y coordinate of the center around which to draw the shape
	 * @return String of code of some graphics language representing the Shape s
	 * 
	 */
	
	public String translateCircle(Circle s, double xcenter, double ycenter);
	public String translateHorizontal(Horizontal s, double xcenter, double ycenter);
	public String translateLayered(Layered s, double xcenter, double ycenter);
	public String translateMountain(Mountain s, double xcenter, double ycenter);
	public String translateStar(Star s, double xcenter, double ycenter);
	public String translatePolygon(Polygon s, double xcenter, double ycenter);
	public String translateRotated(Rotated s, double xcenter, double ycenter);
	public String translateScaled(Scaled s, double xcenter, double ycenter);
	public String translateSierpinsky(Sierpinsky s, double xcenter, double ycenter);
	public String translateSpacer(Spacer s, double xcenter, double ycenter);
	public String translateVertical(Vertical s, double xcenter, double ycenter);
	
}
