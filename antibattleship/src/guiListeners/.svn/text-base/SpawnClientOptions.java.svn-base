package guiListeners;

import gui.*;
import java.awt.event.*;
import javax.swing.*;

public class SpawnClientOptions implements ActionListener
{
	
	private final JFrame mainMenu;
	private final JTextField usernameField;
	
	public SpawnClientOptions ( JFrame mainMenu, JTextField usernameField ) 
	{
		this.mainMenu = mainMenu;
		this.usernameField = usernameField;
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		
		// check if there's a username
		
		if ( usernameField.getText().isEmpty() )
		{
			JOptionPane.showMessageDialog(this.mainMenu, "Please input a username.");
			usernameField.setText("");
			return;
		}
		
		// spawn network host options window
		
		new NetworkClientOptions(mainMenu, usernameField.getText());
		
	}
	
}
