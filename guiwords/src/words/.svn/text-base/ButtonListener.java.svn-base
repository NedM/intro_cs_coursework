package words;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener 
{
	private final WordFinder wordFinder;
	
	public ButtonListener(WordFinder wFind)
	{
		this.wordFinder = wFind;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Search"))
		{
			System.out.println("Search Button pressed!");
			this.wordFinder.doSearch();
		}
		else if(e.getActionCommand().equals("Clear"))
		{
			System.out.println("Clear Button pressed!");
			this.wordFinder.doClear();
		}
	}
}
