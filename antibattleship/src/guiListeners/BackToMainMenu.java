package guiListeners;

import java.awt.event.*;
import javax.swing.*;

public class BackToMainMenu implements ActionListener
{
	
	private JFrame mainMenu;
	private JFrame toClose;
	
	public BackToMainMenu ( JFrame mainMenu, JFrame toClose )
	{
		this.mainMenu = mainMenu;
		this.toClose = toClose;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		mainMenu.setEnabled(true);
		toClose.dispose();
	}
	
}
