package postscript.commands;

/**
 * 
 * Represents the postscript command `\savematrix matrix defaultmatrix def'.
 * This command generates the original transformation matrix before ANY scaleds/translates/etc. 
 * 
 * @author JVen
 * @see <a href="http://www.redgrittybrick.org/postscript/ellipse.html">http://www.redgrittybrick.org/postscript/ellipse.html</a>
 * 
 */

public class FixScaleDef implements PostscriptCommand
{

	public FixScaleDef()
	{
		
	}

	public String toString()
	{
		return "/savematrix matrix defaultmatrix def\n";
	}
}
