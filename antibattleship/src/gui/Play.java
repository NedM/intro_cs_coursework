package gui;

import client.*;
import players.*;
import guiListeners.*;
import img.*;
import info.clearthought.layout.TableLayout;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import message.ResultType;

/**
 * 
 * GUI for play screen
 * 
 * @author jboortz
 *
 */
public class Play extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	protected Container cp;
	private List<JLabel> shipLabel;
	private List<JLabel> myLabels;
 	private List<JButton> oppBoardList;
	private List<ABSBoardSquare> shipsToAttack;
	private ABSAttackBoard myBoard;
	private ABSMarkBoard oppBoard;
	private List<ABSShip> myShips;
	
	public Play ( GUIPlayer me, List<JLabel> myLabels, List<ABSShip> myShips, int numCols, int numRows )
	{
		
		super("Antibattleship - Game Play");
		
		this.shipsToAttack = new ArrayList<ABSBoardSquare>();
		this.myLabels = myLabels;
		this.myBoard = new ABSAttackBoard(numRows, numCols, myShips);
		this.oppBoard = new ABSMarkBoard(numRows, numCols);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    
		cp = this.getContentPane();
//		cp.setSize(300, 300);
		cp.setLayout(new GridLayout());
		
		JPanel pane = new JPanel();
		pane.setBackground(Color.BLACK);
		
    
		cp.setBackground(Color.BLACK);
		//double width = .9/(numCols*2+1);
		//double height = .8/(numRows);
		
		double xSize[] = new double[numCols*2+3];
		for ( int i = 0 ; i <= 2 * numCols + 2 ; i++ )
		{
			xSize[i] = ImageLibrary.BOARD_ICON_SIZE;
		}

		double ySize[] = new double[numRows+3];
		for ( int i = 0 ; i <= numRows + 1 ; i++ )
		{
			ySize[i] = ImageLibrary.BOARD_ICON_SIZE;
		}
		
		double size[][] = {xSize,ySize};
		
//		cp.setLayout(new TableLayout(size));
		pane.setLayout(new TableLayout(size));
		int endYourBoard = 1+numCols;
		JLabel yourLab = new JLabel("Your Board");
		yourLab.setForeground(Color.white);
		pane.add(yourLab, "1,0," + endYourBoard + ",0");
		int oppLoc = numCols+2;
		int oppLocPlus = numCols+numCols;
		String thisLoc = oppLoc + ",0," + oppLocPlus + ",0";
		JLabel oppLab = new JLabel("Your Opponent's Board");
		oppLab.setForeground(Color.white);
		pane.add(oppLab, thisLoc);
		
		// create list of labels representing attack board
		
		List <JLabel> myBoardList = new ArrayList<JLabel>();
		
		// initialize board with ship information
		
		ABSAttackBoard tmpBoard = new ABSAttackBoard(numRows, numCols, myShips);
		
		for ( int i = 0 ; i < numRows ; i++ )
		{
			for ( int j = 0; j < numCols ; j++ )
			{
				
				JLabel squareLabel = getLabel(myLabels, i, j, numCols);
				
				int curX = j+1;
				int curY = i+1;
				String myLoc = curX + "," + curY;
				
				pane.add(squareLabel, myLoc);
				squareLabel.setForeground(Color.white);
				myBoardList.add(squareLabel);

				// if this square contains a ship, label it
				
				if ( tmpBoard.getSquare(i, j) == ABSAttackBoard.UNHIT_WATER )
				{
					// uncomment below for text label
					//squareLabel.setText(" ");
					
					// get water icon
					squareLabel.setIcon(ImageLibrary.getUnhitWaterIcon());
				}
				else
				{
					// uncomment below for text label
					//squareLabel.setText("o");
					
					// get ship icon
					squareLabel.setIcon(ImageLibrary.getUnhitShipIcon());
				}
				
			}
		}
		
		shipLabel = new ArrayList<JLabel>();
		oppBoardList = new ArrayList<JButton>();
		for ( int r = 0 ; r < numRows ; r++ )
		{
			for ( int c = 0 ; c < numCols ; c++ )
			{
				
				//Integer curLen = oppBoardList.size();
				oppBoardList.add(new JButton(" "));
				int curX = c+numCols+2;
				int curY = r+1;
				String myLoc = curX + "," + curY;
				pane.add(oppBoardList.get(oppBoardList.size() - 1), myLoc);
				JLabel newShipLabel = new JLabel(ImageLibrary.getUnknownIcon());
				newShipLabel.setForeground(Color.white);
				shipLabel.add(newShipLabel);
				pane.add(newShipLabel, myLoc);
		
				oppBoardList.get(oppBoardList.size() - 1).addActionListener(new Attack(this, me, oppBoardList, myBoardList, shipLabel, myBoard, oppBoard, shipsToAttack, r, c, numRows, numCols));
				
			}
		}
		
		int yPlus = numCols+1;
		int butWidth = numRows;

		int begLoc = butWidth + 2;
		int endLoc = begLoc + numCols-1;
		String insLoc = begLoc + "," + yPlus + "," + endLoc + "," + yPlus;
		JLabel instructions = new JLabel("Click on the square you would like to attack!");
		instructions.setBackground(Color.BLACK);
		instructions.setForeground(Color.WHITE);
		pane.add(instructions, insLoc);
		
		// spawn window
		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cp.add(scrollPane, "0,0");
        super.pack();
        super.setVisible(true);
		
//		GUIUtilities.spawnAwesomeWindow(this, cp);
		
		this.repaint();
		
		// if we are not initiating, get opponent's first attack
		
		if ( !me.getIsInitiating() )
		{
			// disable board
			
			this.setEnabled(false);
			
			// repeat the following as long as it's the opponent's turn
			
			boolean oppTurn = true;
			while ( oppTurn )
			{
				
				// unless we're not in salvo mode and the opponent doesn't miss, it's our turn next
				
				oppTurn = false;
				
				// wait a little bit
				
				try
				{
					Thread.sleep(100);
				}
				catch ( Exception ex )
				{
				}
				
				// get opponent's targets
				
				List <ABSBoardSquare> oppTargets = new ArrayList<ABSBoardSquare>();
				while ( (oppTargets = me.getOppTargets()) == null )
				{
					
					// check if the game is over
					
					if ( me.getGameResult() != GUIPlayer.GameResult.DONT_KNOW )
					{
						new GameOver(this, me.getGameResult(), me.getName());
						this.setEnabled(false);
						return;
					}
					try
					{
						Thread.sleep(100);
					}
					catch ( InterruptedException e1 )
					{
					}
					
				}
				
				// get results of opponent's attack
				
				for ( ABSBoardSquare target : oppTargets )
				{
					ResultType r = this.myBoard.attack(target.getRow(), target.getColumn());
					
					// check if it's his turn next
					
					if ( !me.getIsSalvo() && r != ResultType.MISS )
						oppTurn = true;
				}
				
				// update my board
				
				for ( int row = 0 ; row < numRows ; row++ )
				{
					for ( int col = 0 ; col < numCols ; col++ )
					{
						
						Integer attackType = this.myBoard.getSquare(row, col);
						
						// uncomment below for text labels
						
						/*
						String lab;
						
						switch ( attackType )
						{
						
							case ABSAttackBoard.UNHIT_WATER:
								lab = " ";
								break;
								
							case ABSAttackBoard.HIT_WATER:
								lab = "~";
								break;
								
							case ABSAttackBoard.HIT_SHIP:
								lab = "x";
								break;
								
							case ABSAttackBoard.SUNK_SHIP:
								lab = "-";
								break;
								
							default:
								// unhit ship
								lab = "o";
								break;
								
						}
						
						this.myBoardList.get(row * this.totCols + col).setText(lab);
						*/
						
						ImageIcon icon = null;
						
						switch ( attackType )
						{
						
							case ABSAttackBoard.UNHIT_WATER:
								icon = ImageLibrary.getUnhitWaterIcon();
								break;
								
							case ABSAttackBoard.HIT_WATER:
								icon = ImageLibrary.getHitWaterIcon();
								break;
								
							case ABSAttackBoard.HIT_SHIP:
								icon = ImageLibrary.getHitShipIcon();
								break;
								
							case ABSAttackBoard.SUNK_SHIP:
								icon = ImageLibrary.getSunkShipIcon();
								break;
								
							default:
								// unhit ship
								icon = ImageLibrary.getUnhitShipIcon();
								break;
								
						}
						
						// only update the icon if it's of a different type
						
						if ( !icon.getDescription().equals(((ImageIcon)(myBoardList.get(row * numCols + col).getIcon())).getDescription()) )
							myBoardList.get(row * numCols + col).setIcon(icon);
						
					}
				}
				
			}
			
			// wait a little bit
			
			try
			{
				Thread.sleep(300);
			}
			catch ( Exception ex )
			{
			}
			
			// check if the game is over
			
			if ( me.getGameResult() != GUIPlayer.GameResult.DONT_KNOW )
			{
				new GameOver(this, me.getGameResult(), me.getName());
				this.setEnabled(false);
				return;
			}
			
			// opponent's turn is over, re-enable play screen
			
			this.setEnabled(true);
		}
		
	}
		
	private JLabel getLabel (List<JLabel> lab, int row, int col, int totCols )
	{
		return lab.get(row * totCols + col);
	}
	
}
