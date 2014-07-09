package guiListeners;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * 
 * Action Listener adds ship to board
 * 
 * @author jboortz
 *
 */

public class AddShip implements ActionListener {

	private JFrame myFrame;
	private JTextField enterShip;
	private DefaultListModel listModel;
	private List<Integer> shipLen;
	
	public AddShip ( JFrame myFrame, JTextField enterShip, DefaultListModel listModel, List<Integer> shipLen )
	{
		this.myFrame = myFrame;
		this.enterShip = enterShip;
		this.listModel = listModel;
		this.shipLen = shipLen;
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		try
		{
			if ( enterShip.getText().isEmpty() )
				return;
			if ( Integer.parseInt(enterShip.getText()) < 1 )
			{
				JOptionPane.showMessageDialog(this.myFrame, "Ship lengths must be positive.");
				return;
			}
			String text = "Ship of length " + enterShip.getText();
			listModel.addElement(text);
			shipLen.add(Integer.parseInt(enterShip.getText()));
			enterShip.setText("");
		}
		catch ( NumberFormatException exp )
		{
			JOptionPane.showMessageDialog(this.myFrame, enterShip.getText() + " is not a valid ship length.  Please enter an integer.");
			enterShip.setText("");
		}
	}
}
