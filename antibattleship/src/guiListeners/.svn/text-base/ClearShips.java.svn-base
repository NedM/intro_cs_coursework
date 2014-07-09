package guiListeners;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ClearShips implements ActionListener
{

	private final DefaultListModel shipSizeListModel;
	private final List<Integer> shipSizes;
	
	public ClearShips ( DefaultListModel shipSizeListModel, List<Integer> shipSizes )
	{
		this.shipSizeListModel = shipSizeListModel;
		this.shipSizes = shipSizes;
	}
	
	@Override
	public void actionPerformed ( ActionEvent e )
	{
		shipSizeListModel.clear();
		shipSizes.clear();
	}
	
}
