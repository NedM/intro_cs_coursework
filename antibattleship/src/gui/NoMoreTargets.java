package gui;

import img.*;
import guiListeners.*;

import java.awt.*;
import javax.swing.*;

import info.clearthought.layout.TableLayout;


public class NoMoreTargets extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public NoMoreTargets()
	{
		
		super("");
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setUndecorated(true);
		
        cp = this.getContentPane();
        cp.setBackground(Color.LIGHT_GRAY);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	200,
        	spacerSize}
        ,
        {spacerSize,
        	20,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // set title
        
        JLabel infoLabel = new JLabel("There are no more squares to attack. Waiting for opponent to end game...");
        cp.add(infoLabel, "1,1");
        
        // spawn window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

