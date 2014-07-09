package words;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {

	private final WordFinder wordFind;
	public MenuListener(WordFinder wFind)	
	{
		this.wordFind = wFind;		
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		if(e.getActionCommand().equals("Open"))
		{
			System.out.println("Launching open dialog...");
			this.wordFind.doOpen();
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.out.println("Exiting...");
			this.wordFind.doExit();
		}
	}
}
