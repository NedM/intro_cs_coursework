package postscript.commands;

/**
 * Represents the postscript command `x y moveto'.
 */
public class Moveto implements PostscriptCommand {

	private final double x;
	private final double y;

	public Moveto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return x + " " + y + " moveto\n";
	}

}
