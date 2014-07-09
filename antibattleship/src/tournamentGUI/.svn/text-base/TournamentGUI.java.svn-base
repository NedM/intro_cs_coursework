package tournamentGUI;

import gui.*;
import img.*;
import players.jasper.*;

import info.clearthought.layout.TableLayout;
import java.awt.*;
import javax.swing.*;

public class TournamentGUI extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private final JasperBot jasper;
	
	private Container cp;
	
	public TournamentGUI ( JasperBot jasper )
	{

		super("Antibattleship - Tournament GUI");
		
		// store jasper
		
		this.jasper = jasper;
		
		// get boards
		
		Container jasperBoard = new JasperBoard(jasper.getAttackBoard()).getContentPane();
		Container oppBoard = new OppBoard(jasper.getMarkBoard()).getContentPane();
		Container thoughtsPane = new ThoughtsPane(jasper).getContentPane();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cp = this.getContentPane();
        cp.setBackground(Color.BLACK);
        
        double spacerSize = GUIUtilities.SPACER_SIZE;
        
        double[][] layoutSize =
        {{spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize}
        ,
        {spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize,
        	TableLayout.PREFERRED,
        	spacerSize}};
        
        cp.setLayout(new TableLayout(layoutSize));
        
        // set banner
        
        JLabel banner = new JLabel(ImageLibrary.getTitle(), SwingConstants.CENTER);
        cp.add(banner, "1,1,5,1");
        
        // jasper label
        
        JLabel jasperLabel = new JLabel(ImageLibrary.getJasperImg(), SwingConstants.CENTER);
        cp.add(jasperLabel, "1,3");
        
        // jasper state label
        
        String color = "black";
        
        switch ( jasper.getGameResult() )
        {
        	case DONT_KNOW:
        		color = "orange";
        		break;
        	case WIN:
        		color = "green";
        		break;
        	case LOSS:
        		color = "red";
        		break;
        	case ERROR:
        		color = "black";
        		break;
        }
        
        JLabel jasperStateLabel = new JLabel(ImageLibrary.getJasperStateImg(color), SwingConstants.CENTER);
        cp.add(jasperStateLabel, "3,3");
        
        // opponent label
        
        JLabel opponentLabel = new JLabel(ImageLibrary.getOpponentImg(), SwingConstants.CENTER);
        cp.add(opponentLabel, "5,3");
        
        // jasper board
        
        cp.add(jasperBoard, "1,5");
        
        // thoughts
        
        cp.add(thoughtsPane, "3,5");
        
        // opponent board
        
        cp.add(oppBoard, "5,5");
        
        // jasper hits left
        
        JLabel jasperHitsLeftLabel = new JLabel("" + jasper.getNumMyHitsLeft(), SwingConstants.CENTER);
        jasperHitsLeftLabel.setFont(GUIUtilities.GIANT_FONT);
        cp.add(jasperHitsLeftLabel, "1,7");
        
        // num hits left
        
        JLabel numHitsLeftLabel = new JLabel(ImageLibrary.getNumHitsImg(), SwingConstants.CENTER);
        cp.add(numHitsLeftLabel, "3,7");
        
        // opponent hits left
        
        JLabel oppHitsLeftLabel = new JLabel("" + jasper.getNumOppHitsLeft(), SwingConstants.CENTER);
        oppHitsLeftLabel.setFont(GUIUtilities.GIANT_FONT);
        cp.add(oppHitsLeftLabel, "5,7");
        
        // set stuff to white, center
        
        for ( Component c : cp.getComponents() )
        	c.setForeground(Color.WHITE);
        
        // spawn window
        
        GUIUtilities.makeAwesomeWindow(this, cp);
		
	}
	
	public void refresh()
	{
		this.setContentPane((new TournamentGUI(jasper)).cp);
		//this.repaint();
	}
	
}
