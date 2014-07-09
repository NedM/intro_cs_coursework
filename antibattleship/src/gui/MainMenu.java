package gui;

import img.*;
import guiListeners.*;

import java.awt.*;
import javax.swing.*;

import info.clearthought.layout.TableLayout;

/**
 * 
 * GUI Main Menu. Can initiate games from here.
 * 
 * @author jboortz
 *
 */
public class MainMenu extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public MainMenu()
	{
		
		super("Antibattleship - Main Menu");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	TableLayout.PREFERRED,
        	TableLayout.PREFERRED,
        	TableLayout.PREFERRED,
        	spacerSize}
        ,
        {spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize / 2,
        	TableLayout.PREFERRED,
        	spacerSize / 2,
        	TableLayout.PREFERRED,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // set title
        
        JLabel welcome = new JLabel(ImageLibrary.getTitle());
        cp.add(welcome, "1,1,3,1");
        
        // username label
        
        JLabel usernameLabel = new JLabel("Username", SwingConstants.CENTER);
        usernameLabel.setForeground(Color.white);
        cp.add(usernameLabel, "2,3");
        
        // username field
        
        JTextField usernameField = new JTextField();
        cp.add(usernameField, "2,4");
        
        // play computer button
        
        JButton playComp = new JButton("Play the Computer");
        cp.add(playComp, "2,6");
        
        // play network host
        
        JButton playNetworkHost = new JButton("Host a Network Game");
        cp.add(playNetworkHost, "2,8");
        
        // play network client
        
        JButton playNetworkClient = new JButton("Connect to a Network Game");
        cp.add(playNetworkClient, "2,10");
        
        // add listeners
        
        playComp.addActionListener(new PlayComputer(this, usernameField));
        playNetworkHost.addActionListener(new SpawnHostOptions(this, usernameField));
        playNetworkClient.addActionListener(new SpawnClientOptions(this, usernameField));
        
        // spawn window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
        
	}
	
}
	

