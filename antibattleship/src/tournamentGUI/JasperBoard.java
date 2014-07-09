package tournamentGUI;

import img.*;
import client.*;

import info.clearthought.layout.TableLayout;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class JasperBoard extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private final ABSAttackBoard board;
	
	private Container cp;
	private List<JLabel> squareLabels = new ArrayList<JLabel>();
	
	public JasperBoard ( ABSAttackBoard board )
	{
        
		this.board = board;
		
        cp = this.getContentPane();
        this.setBackground(Color.BLACK);
        
        // set sizes
        
        double[] colSizes = new double[board.getNumCols()];
        double[] rowSizes = new double[board.getNumRows()];
        
        for ( int col = 0 ; col < colSizes.length ; col++ )
        	colSizes[col] = ImageLibrary.BOARD_ICON_SIZE;
        
        for ( int row = 0 ; row < rowSizes.length ; row++ )
        	rowSizes[row] = ImageLibrary.BOARD_ICON_SIZE;
        
        double[][] layoutSize = {colSizes, rowSizes};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // make border
        
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        
        // initialize
        
        for ( int row = 0 ; row < rowSizes.length ; row++ )
        {
        	for ( int col = 0 ; col < colSizes.length ; col++ )
        	{
        		JLabel squareLabel = new JLabel(ImageLibrary.getUnknownIcon(row + col), SwingConstants.CENTER);
        		squareLabel.setBorder(border);
        		cp.add(squareLabel, "" + col + "," + row);
        		this.squareLabels.add(squareLabel);
        	}
        }
        
        update();
		
	}
	
	public void update()
	{
		for ( int row = 0 ; row < this.board.getNumRows() ; row++ )
		{
			for ( int col = 0 ; col < this.board.getNumCols() ; col++ )
			{
				Integer attackType = this.board.getSquare(row, col);
				ImageIcon icon = null;
				
				switch ( attackType )
				{
					case ABSAttackBoard.UNHIT_WATER:
						icon = ImageLibrary.getUnhitWaterIcon(row + col);
						break;
						
					case ABSAttackBoard.HIT_WATER:
						icon = ImageLibrary.getHitWaterIcon(row + col);
						break;
						
					case ABSAttackBoard.HIT_SHIP:
						icon = ImageLibrary.getHitShipIcon(row + col);
						break;
						
					case ABSAttackBoard.SUNK_SHIP:
						icon = ImageLibrary.getSunkShipIcon(row + col);
						break;
						
					default:
						// unhit ship
						icon = ImageLibrary.getUnhitShipIcon(row + col);
						break;
				}
				
				// only update the icon if it's of a different type
				
				if ( !icon.getDescription().equals(((ImageIcon)(getLabel(row, col).getIcon())).getDescription()) )
					getLabel(row, col).setIcon(icon);
			}
		}
	}
	
	private JLabel getLabel ( int row, int col )
	{
		int index = col + row * this.board.getNumCols();
		return this.squareLabels.get(index);
	}
	
}
