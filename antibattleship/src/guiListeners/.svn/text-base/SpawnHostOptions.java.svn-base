package guiListeners;

import gui.*;
import java.awt.event.*;
import javax.swing.*;

public class SpawnHostOptions implements ActionListener
{
	
	private final JFrame mainMenu;
	private final JTextField usernameField;
	
	public SpawnHostOptions ( JFrame mainMenu, JTextField usernameField ) 
	{
		this.mainMenu = mainMenu;
		this.usernameField = usernameField;
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		
		// disable main menu while we're here
		
		mainMenu.setEnabled(false);
		
		// check if there's a username
		
		if ( usernameField.getText().isEmpty() )
		{
			JOptionPane.showMessageDialog(this.mainMenu, "Please input a username.");
			usernameField.setText("");
			mainMenu.setEnabled(true);
			return;
		}
		
		// spawn network host options window
		
		new NetworkHostOptions(mainMenu, usernameField.getText());
		
	}
	
}
