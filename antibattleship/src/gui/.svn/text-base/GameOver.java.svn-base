package gui;

import guiListeners.*;
import img.*;
import leaderboard.*;
import players.GUIPlayer.GameResult;

import java.util.List;
import info.clearthought.layout.TableLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import leaderboard.Leaderboard;

public class GameOver extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Shows end game information
	 * 
	 * @author JVen
	 * @param gameResult The result of the game returned by GUIPlayer
	 * @requires gameResult != GameResult.DONT_KNOW
	 * 
	 */
	
	private Container cp;
	
	public GameOver ( JFrame playScreen, GameResult gameResult, String username )
	{
		
		super("Antibattleship - Game Over");
		
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
		// handle gameResult
        
        ImageIcon image = null;
        String description = "";
        
        switch ( gameResult )
        {
        	case WIN:
        		image = ImageLibrary.getVictoryImg();
        		description = "Congratulations! You are victorious! Here is the online leaderboard:";
        		Leaderboard.reportWin(username);
        		break;
        	case LOSS:
        		image = ImageLibrary.getDefeatImg();
        		description = "You failed to achieve victory. Here is the online leaderboard:";
        		Leaderboard.reportLoss(username);
        		break;
        	case ERROR:
        	default:
        		image = ImageLibrary.getErrorImg();
        		description = "An error was thrown. Your online stats will not be affected.";
        		break;
        }
		
        // wait a little bit
        
        try
        {
        	Thread.sleep(200);
        }
        catch ( Exception e )
        {
        }
        
		// get leaderboard info
		
        List<UserStats> userStats = Leaderboard.getStats();
		int numRows = userStats.size();
		
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        // get top left of leaderboard
        
        int leaderboardStartRow = 6;
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        // set column sizes
        
        double[] colSizes = {spacerSize,
        		200,
        		100,
        		100,
        		spacerSize};
        
        // set row sizes
        
        double[] rowSizes = new double [ numRows + leaderboardStartRow + 3 ];
        
        rowSizes[0] = spacerSize;
        rowSizes[1] = 200;
        rowSizes[2] = spacerSize;
        rowSizes[3] = 40;
        rowSizes[4] = spacerSize;
        rowSizes[5] = 60;
        
        for ( int row = leaderboardStartRow ; row <= numRows + leaderboardStartRow - 1; row++ )
        	rowSizes[row] = 50;
        
        rowSizes[numRows + leaderboardStartRow] = spacerSize;
        rowSizes[numRows + leaderboardStartRow + 1] = 30;
        rowSizes[numRows + leaderboardStartRow + 2] = spacerSize;
        
        // set layout size
        
        double[][] layoutSize = {colSizes, rowSizes};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // banner
        
        JLabel banner = new JLabel(image);
        cp.add(banner, "1,1,3,1");
        
        // description label
        
        JLabel descriptionLabel = new JLabel(description, SwingConstants.CENTER);
        descriptionLabel.setForeground(Color.WHITE);
        cp.add(descriptionLabel, "1,3,3,3");
        
        // make border
        
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        
        // username label
        
        JLabel usernameLabel = new JLabel("Username", SwingConstants.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBorder(border);
        cp.add(usernameLabel, "1,5");
        
        // wins label
        
        JLabel winsLabel = new JLabel("Wins", SwingConstants.CENTER);
        winsLabel.setForeground(Color.WHITE);
        winsLabel.setBorder(border);
        cp.add(winsLabel, "2,5");
        
        // losses label
        
        JLabel lossesLabel = new JLabel("Losses", SwingConstants.CENTER);
        lossesLabel.setForeground(Color.WHITE);
        lossesLabel.setBorder(border);
        cp.add(lossesLabel, "3,5");
        
        // iterate through user stats
        
        for ( int index = 0 ; index < numRows ; index++ )
        {
        	UserStats stat = userStats.get(index);
        	
        	JLabel statUserLabel = new JLabel(stat.getUsername(), SwingConstants.CENTER);
        	JLabel statWinsLabel = new JLabel("" + stat.getWins(), SwingConstants.CENTER);
        	JLabel statLossesLabel = new JLabel("" + stat.getLosses(), SwingConstants.CENTER);
        	
        	statUserLabel.setForeground(Color.WHITE);
        	statWinsLabel.setForeground(Color.WHITE);
        	statLossesLabel.setForeground(Color.WHITE);
        	
        	statUserLabel.setBorder(border);
        	statWinsLabel.setBorder(border);
        	statLossesLabel.setBorder(border);
        	
        	cp.add(statUserLabel, "1," + (leaderboardStartRow + index));
        	cp.add(statWinsLabel, "2," + (leaderboardStartRow + index));
        	cp.add(statLossesLabel, "3," + (leaderboardStartRow + index));
        }
        
        // OK button
        
        JButton okButton = new JButton("OK");
        cp.add(okButton, "3," + (numRows + leaderboardStartRow + 1));
        
        // add listeners
        
        okButton.addActionListener(new SpawnMainMenu(playScreen, this));
        
        // spawn window
        
        GUIUtilities.spawnAwesomeWindow(this, cp);
		
	}
	
}
