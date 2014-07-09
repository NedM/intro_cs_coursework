package gui;

import img.*;
import threads.*;
import info.clearthought.layout.TableLayout;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * Generates waiting messages using a factory method (fancy fancy)
 * 
 * @author JVen
 *
 */

public class WaitMessage extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public WaitMessage ( String msg )
	{
		
		super("Waiting");
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setUndecorated(true);
		
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	93,
        	spacerSize / 2,
        	TableLayout.PREFERRED,
        	spacerSize}
        ,
        {spacerSize,
        	119,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // icon
        
        JLabel iconLabel = new JLabel(ImageLibrary.getWaitImg());
        cp.add(iconLabel, "1,1");
        
        // wait message
        
        JLabel infoLabel = new JLabel(msg, SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setVerticalAlignment(SwingConstants.CENTER);
        cp.add(infoLabel, "3,1");
        
        // spawn window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
	public static WaitMessage generate ( String msg )
	{
		WaitThread waitThread = new WaitThread(msg);
		Thread thread = new Thread(waitThread);
		thread.start();
		while ( !waitThread.isReady() )
		{
			// wait a little bit
			try
			{
				Thread.sleep(100);
			}
			catch ( Exception e )
			{
			}
		}
		return waitThread.getWaitMessage();
	}
	
}
