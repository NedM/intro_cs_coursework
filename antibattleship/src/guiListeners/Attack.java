package guiListeners;

import players.*;
import client.*;
import img.*;
import gui.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import message.ResultType;

/**
 * 
 * Action Listener attacks ship
 * 
 * @author jboortz
 *
 */ 
public class Attack implements ActionListener
{
	
	private boolean salvo;
	private List<ABSBoardSquare> shipsToAttack;
	private JFrame myFrame;
	private int row;
	private int col;
	private List<JButton> myButtons;
	private int totCols;
	private GUIPlayer me;
	private List<JLabel> results;
	private List<JLabel> myBoardList;
	private int totRows;
	private ABSAttackBoard myBoard;
	private ABSMarkBoard oppBoard;
	
	public Attack ( JFrame myFrame, GUIPlayer me, List<JButton> myButtons, List<JLabel> myBoardList, List<JLabel> results, ABSAttackBoard myBoard, ABSMarkBoard oppBoard, List<ABSBoardSquare> shipsToAttack, int row, int col, int totRows, int totCols )
	{
		
		this.me = me;
		this.myFrame = myFrame;
		this.salvo = me.getIsSalvo();
		this.myButtons = myButtons;
		this.shipsToAttack = shipsToAttack;
		this.totCols = totCols;
		this.results = results;
		this.myBoardList = myBoardList;
		this.totRows = totRows;
		this.myBoard = myBoard;
		this.oppBoard = oppBoard;
		this.row = row;
		this.col = col;
		
	}
	
	public void actionPerformed ( ActionEvent e )
	{
		
		// make the button that was clicked disappear
		
		((Component) e.getSource()).setVisible(false);
		
		// disable play screen
		
		myFrame.setEnabled(false);
		
		// wait a little bit
		
		try
		{
			Thread.sleep(100);
		}
		catch ( Exception ex )
		{
		}
		
		// add the square clicked to the list of ships to attack
		
		shipsToAttack.add(new ABSBoardSquare(this.row,this.col));
		
		// if in salvo mode, check if have more attacks
		
		if ( this.salvo == true )
		{
			if ( this.shipsToAttack.size() < this.myBoard.getNumUnsunkShips() )
			{
				// the user still needs more attacks
				
				//JOptionPane.showMessageDialog(this.myFrame, "You still have more attacks.");
				
				// re-enable play screen
				
				myFrame.setEnabled(true);
				return;
			}
		}
		
		
		
		// send my targets
		
		me.sendMyTargets(shipsToAttack);
		
		// wait a little bit
		
		try
		{
			Thread.sleep(100);
		}
		catch ( Exception ex )
		{
		}
		
		// get results of my attack
		
		List<ResultType> myResults = new ArrayList<ResultType>();
		while ( (myResults = me.getMyResults() ) == null )
		{
			// check if the game is over
			
			if ( me.getGameResult() != GUIPlayer.GameResult.DONT_KNOW )
			{
				new GameOver(myFrame, me.getGameResult(), me.getName());
				myFrame.setEnabled(false);
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
		
		// now check what the result was for each square attacked and update opp board
		
		for ( int i = 0 ; i < shipsToAttack.size() ; i++)
		{
			
			ResultType shipResult = myResults.get(i);
			oppBoard.mark(shipsToAttack.get(i).getRow(), shipsToAttack.get(i).getColumn(), shipResult);
		
			// uncomment below for text labels
			
			/*
			
			ResultType textToPrint = shipResult;
			
			if ( textToPrint == ResultType.SUNK )
				textToPrint = ResultType.HIT;
			
			this.results.get(row * this.totCols + col).setText(textToPrint.toString());
			*/
			
			// get appropriate icon
			
			switch ( shipResult )
			{
				case MISS:
					this.results.get(shipsToAttack.get(i).getRow() * this.totCols + shipsToAttack.get(i).getColumn()).setIcon(ImageLibrary.getUnhitWaterIcon());
					break;
					
				case HIT:
				case SUNK:
					// TODO somehow know which thing to update upon a sunk? what if opponent violated adjacency? idk man
					this.results.get(shipsToAttack.get(i).getRow() * this.totCols + shipsToAttack.get(i).getColumn()).setIcon(ImageLibrary.getUnhitShipIcon());
					break;
			}
			
		}
		
	
		// clear the ships to attack so that a new set of ships can be attacked
		
		shipsToAttack.clear();		
		
		// check if we get another turn
		
		if ( !this.salvo && ( myResults.get(0) == message.ResultType.HIT || myResults.get(0) == message.ResultType.SUNK ) )
		{
			// re-enable play screen if it's our turn
			myFrame.setEnabled(true);
			
			return;
		}
		
		// check if we have any squares left
		
		if ( !oppBoard.isUntriedLeft() )
		{
			NoMoreTargets msg = new NoMoreTargets();
			while ( me.getGameResult() == GUIPlayer.GameResult.DONT_KNOW )
			{
				try
				{
					Thread.sleep(100);
				}
				catch ( Exception ex )
				{
				}
			}
			msg.dispose();
			new GameOver(myFrame, me.getGameResult(), me.getName());
			myFrame.setEnabled(false);
			return;
		}
		
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
					new GameOver(myFrame, me.getGameResult(), me.getName());
					myFrame.setEnabled(false);
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
				
				if ( !this.salvo && r != ResultType.MISS )
					oppTurn = true;
			}
			
			// update my board
			
			for ( int row = 0 ; row < this.totRows ; row++ )
			{
				for ( int col = 0 ; col < this.totCols ; col++ )
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
					
					if ( !icon.getDescription().equals(((ImageIcon)(this.myBoardList.get(row * this.totCols + col).getIcon())).getDescription()) )
						this.myBoardList.get(row * this.totCols + col).setIcon(icon);
					
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
			new GameOver(myFrame, me.getGameResult(), me.getName());
			myFrame.setEnabled(false);
			return;
		}
		
		// opponent's turn is over, re-enable play screen
		
		myFrame.setEnabled(true);
		
	}
}
