package gui;

import info.clearthought.layout.TableLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class InvalidBoard extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public InvalidBoard(){
		super("Antibattleship - Invalid Board");
		// call System.exit() when user closes the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    
		Container cp = this.getContentPane();
    
		double size[][] = {{.05, .9, .05},{.05, .3, .6, .05}};
		cp.setLayout(new TableLayout(size));
		
		//may want to actually report exact problem
		cp.add(new JLabel("Invalid Board Configuration. Please try again."), "1,1");
		
		JButton ok = new JButton("Ok");
		cp.add(ok, "1,2");
		
		ok.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("you hit ok");
        		//todo, call actual code
        	}
        });
		
		//File bar
        JMenuBar myMenuBar = new JMenuBar();
        JMenu myFile = new JMenu("File");
        myMenuBar.add(myFile);
        setJMenuBar(myMenuBar);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		System.exit(0);
        	}
        });
        myFile.add(exit);
        
		
	}

}
