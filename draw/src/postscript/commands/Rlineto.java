package postscript.commands;

/**
 * Represents the postscript command `x y rlineto'.
 */
public class Rlineto implements PostscriptCommand {
	
	private final double x;
	private final double y;

	public Rlineto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return x + " " + y + " rlineto\n";
	}
}
