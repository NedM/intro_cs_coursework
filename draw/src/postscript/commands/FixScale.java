package postscript.commands;

import postscript.commands.PostscriptCommand;

/**
 * 
 * Represents the postscript command `savematrix setmatrix'.
 * This command sets the current transformation matrix (CTM) to the defaultmatrix produced by FixScaleDef
 * 
 * @author JVen
 * @see <a href="http://www.redgrittybrick.org/postscript/ellipse.html">http://www.redgrittybrick.org/postscript/ellipse.html</a>
 * 
 */

public class FixScale implements PostscriptCommand
{

	public FixScale()
	{
		
	}

	public String toString()
	{
		return "savematrix setmatrix\n";
	}
}
