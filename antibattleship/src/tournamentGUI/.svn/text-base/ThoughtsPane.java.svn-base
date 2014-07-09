package tournamentGUI;

import gui.*;
import img.*;
import client.*;
import players.jasper.*;

import info.clearthought.layout.TableLayout;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ThoughtsPane extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	public ThoughtsPane ( JasperBot jasper )
	{
		
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        // set sizes
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize = {
        		{spacerSize * 3,
        			TableLayout.PREFERRED,
        			spacerSize * 5,
        			TableLayout.PREFERRED},
        		{spacerSize * 3,
        			TableLayout.PREFERRED,
        			spacerSize,
        			TableLayout.PREFERRED,
        			spacerSize,
        			TableLayout.PREFERRED,
        			spacerSize,
        			TableLayout.PREFERRED,
        			spacerSize,
        			TableLayout.PREFERRED}};
        
        cp.setLayout(new TableLayout(layoutSize));

        // make border
        
        //Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        
        // who went first
        
        JLabel firstLabel = new JLabel("Did we move first?", SwingConstants.CENTER);
        firstLabel.setForeground(Color.WHITE);
        firstLabel.setFont(GUIUtilities.SMALL_FONT);
        //firstLabel.setBorder(border);
        cp.add(firstLabel, "1,1");
        
        // answer
        
        JLabel ansFirstLabel = new JLabel("", SwingConstants.CENTER);
        
        if ( jasper.isInitiating() )
        {
        	ansFirstLabel.setText("YES");
        	ansFirstLabel.setForeground(Color.RED);
        }
        else
        {
        	ansFirstLabel.setText("NO");
        	ansFirstLabel.setForeground(Color.GREEN);
        }
        
        ansFirstLabel.setFont(GUIUtilities.BIG_FONT);
        //ansFirstLabel.setBorder(border);
        cp.add(ansFirstLabel, "3,1");
        
        // ship size one label
        
        JLabel shipSizeOneLabel = new JLabel("Any ships of size > 1?", SwingConstants.CENTER);
        shipSizeOneLabel.setForeground(Color.WHITE);
        shipSizeOneLabel.setFont(GUIUtilities.SMALL_FONT);
        //shipSizeOneLabel.setBorder(border);
        cp.add(shipSizeOneLabel, "1,3");
        
        // answer
        
        JLabel ansOneLabel;
        
        if ( jasper.isBigShip() )
        {
        	ansOneLabel = new JLabel("YES", SwingConstants.CENTER);
        	ansOneLabel.setForeground(Color.GREEN);
        }
        else
        {
        	ansOneLabel = new JLabel("NO", SwingConstants.CENTER);
        	ansOneLabel.setForeground(Color.RED);
        }
        
        ansOneLabel.setFont(GUIUtilities.BIG_FONT);
        //ansOneLabel.setBorder(border);
        cp.add(ansOneLabel, "3,3");
        
        // ship size two label
        
        JLabel shipSizeTwoLabel = new JLabel("P(last ship is of size 2)?", SwingConstants.CENTER);
        shipSizeTwoLabel.setForeground(Color.WHITE);
        shipSizeTwoLabel.setFont(GUIUtilities.SMALL_FONT);
        //shipSizeTwoLabel.setBorder(border);
		cp.add(shipSizeTwoLabel, "1,5");
        
		// answer
		
		JLabel ansTwoLabel;
		
		int ansTwo = jasper.getProbLastShipSizeTwo();
		
		if ( ansTwo == -1 )
		{
			ansTwoLabel = new JLabel("N/A", SwingConstants.CENTER);
			ansTwoLabel.setForeground(Color.LIGHT_GRAY);
		}
		else
		{
			ansTwoLabel = new JLabel("" + ansTwo + "%", SwingConstants.CENTER);
			ansTwoLabel.setForeground(new Color((int)(255 * ansTwo / 100.0), (int)(255 * (100 - ansTwo) / 100.0), 0));
		}
		ansTwoLabel.setFont(GUIUtilities.BIG_FONT);
		//ansTwoLabel.setBorder(border);
		
		cp.add(ansTwoLabel, "3,5");
		
		// squares to avoid
		
		JLabel avoidLabel = new JLabel("Squares to avoid:", SwingConstants.CENTER);
		avoidLabel.setForeground(Color.WHITE);
		avoidLabel.setFont(GUIUtilities.SMALL_FONT);
		//avoidLabel.setBorder(border);
		cp.add(avoidLabel, "1,7,1,9");
		
		// answer
		
		List<ABSBoardSquare> avoidSet = jasper.getAvoidSet();
		JLabel ansAvoidLabel1 = new JLabel("-", SwingConstants.CENTER);
		JLabel ansAvoidLabel2 = new JLabel("-", SwingConstants.CENTER);
		
		if ( avoidSet.size() >= 1 )
			ansAvoidLabel1.setText("(" + avoidSet.get(0).getRow() + ", " + avoidSet.get(0).getColumn() + ")");
		if ( avoidSet.size() >= 2 )
			ansAvoidLabel2.setText("(" + avoidSet.get(1).getRow() + ", " + avoidSet.get(1).getColumn() + ")");
		
		ansAvoidLabel1.setForeground(Color.WHITE);
		ansAvoidLabel2.setForeground(Color.WHITE);
		
		ansAvoidLabel1.setFont(GUIUtilities.BIG_FONT);
		ansAvoidLabel2.setFont(GUIUtilities.BIG_FONT);
		
		//ansAvoidLabel1.setBorder(border);
		//ansAvoidLabel2.setBorder(border);
		
		cp.add(ansAvoidLabel1, "3,7");
		cp.add(ansAvoidLabel2, "3,9");
		
	}
	
}
