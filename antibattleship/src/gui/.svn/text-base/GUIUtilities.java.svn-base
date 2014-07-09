package gui;

import img.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * A potpourri of GUI fun
 * 
 * @author JVen
 *
 */

public class GUIUtilities
{

	public static Font GIANT_FONT = new Font("Helvetica", Font.BOLD, 60);
	public static Font BIG_FONT = new Font("Helvetica", Font.BOLD, 35);
	public static Font SMALL_FONT = new Font("Helvetica", Font.BOLD, 20);
	public static double SPACER_SIZE = 15;
	
	public static void spawnAwesomeWindow ( JFrame window, Container contentPane )
	{

		makeAwesomeWindow(window, contentPane);
		window.setVisible(true);
        window.toFront();
        
	}
	
	public static void makeAwesomeWindow ( JFrame window, Container contentPane )
	{
		
		// no resizing!
		
        window.setResizable(false);
		
		// spawn in the middle of the screen
        
        GraphicsConfiguration gc = window.getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        Dimension size = window.getPreferredSize();
        window.setLocation((int)((bounds.width / 2) - (size.getWidth() / 2)), (int)((bounds.height / 2) - (size.getHeight() / 2)));
        
        // gogogo!
        
        window.pack();
        
	}
	
}
