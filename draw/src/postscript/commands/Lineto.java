package postscript.commands;

/**
 * Represents the postscript command `x y lineto'.
 */
public class Lineto implements PostscriptCommand {
	
	private final double x;
	private final double y;

	public Lineto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return x + " " + y + " lineto\n";
	}
}
