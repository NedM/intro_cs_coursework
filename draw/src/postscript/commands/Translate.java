package postscript.commands;

/**
 * Represents the postscript command `translate'.
 */
public class Translate implements PostscriptCommand {
	
	private double x;
	private double y;

	public Translate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return Double.toString(x) + " " + Double.toString(y) + " " + "translate\n";
	}
}
