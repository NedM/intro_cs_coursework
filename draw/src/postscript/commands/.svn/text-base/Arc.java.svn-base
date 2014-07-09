package postscript.commands;

/**
 * Represents the postscript command `x y r ang1 ang2 arc'.
 */
public class Arc implements PostscriptCommand {

	private double x;
	private double y;
	private double r;
	private double ang1;
	private double ang2;

	public Arc(double x, double y, double r, double ang1, double ang2) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.ang1 = ang1;
		this.ang2 = ang2;
	}

	public String toString() {
		return x + " " + y + " "
		       + r + " " + ang1 + " "
                       + ang2 + " arc\n";
	}

}
