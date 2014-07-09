package gui;

import guiListeners.*;

import java.awt.*;
import javax.swing.*;

import info.clearthought.layout.TableLayout;


public class NetworkHostOptions extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public NetworkHostOptions ( JFrame mainMenu, String username )
	{
		
		super("Antibattleship - Host a Network Game");

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
        cp = this.getContentPane();
        cp.setBackground(Color.LIGHT_GRAY);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	100,
        	spacerSize / 2,
        	100,
        	spacerSize}
        ,
        {spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // port label
        
        JLabel portLabel = new JLabel("Port:", SwingConstants.RIGHT);
        portLabel.setForeground(Color.BLACK);
        cp.add(portLabel, "1,1");
        
        // port field
        
        JTextField portField = new JTextField();
        cp.add(portField, "3,1");
        
        // cancel button
        
        JButton cancelButton = new JButton("Cancel");
        cp.add(cancelButton, "1,3");
        
        // OK button
        
        JButton okButton = new JButton("OK");
        cp.add(okButton, "3,3");
        
        // add listeners
        
        cancelButton.addActionListener(new BackToMainMenu(mainMenu, this));
        okButton.addActionListener(new PlayNetworkHost(mainMenu, this, portField, username));
        
        // make window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

