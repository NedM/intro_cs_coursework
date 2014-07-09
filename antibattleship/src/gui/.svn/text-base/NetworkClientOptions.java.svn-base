package gui;

import guiListeners.*;

import java.awt.*;
import javax.swing.*;

import info.clearthought.layout.TableLayout;


public class NetworkClientOptions extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public NetworkClientOptions ( JFrame mainMenu, String username )
	{
		
		super("Antibattleship - Connect to a Network Game");

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
        cp = this.getContentPane();
        cp.setBackground(Color.LIGHT_GRAY);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	100,
        	spacerSize / 2,
        	100,
        	spacerSize / 2,
        	100,
        	100,
        	spacerSize}
        ,
        {spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize / 2,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // hostname label
        
        JLabel hostnameLabel = new JLabel("Hostname:", SwingConstants.RIGHT);
        hostnameLabel.setForeground(Color.BLACK);
        cp.add(hostnameLabel, "1,1");
        
        // hostname field
        
        JTextField hostnameField = new JTextField();
        cp.add(hostnameField, "3,1,6,1");
        
        // port label
        
        JLabel portLabel = new JLabel("Port:", SwingConstants.RIGHT);
        portLabel.setForeground(Color.BLACK);
        cp.add(portLabel, "1,3");
        
        // port field
        
        JTextField portField = new JTextField();
        cp.add(portField, "3,3");
        
        // cancel button
        
        JButton cancelButton = new JButton("Cancel");
        cp.add(cancelButton, "3,5");
        
        // OK button
        
        JButton okButton = new JButton("OK");
        cp.add(okButton, "5,5");
        
        // add listeners
        
        cancelButton.addActionListener(new BackToMainMenu(mainMenu, this));
        okButton.addActionListener(new PlayNetworkClient(mainMenu, this, hostnameField, portField, username));
        
        // make window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

