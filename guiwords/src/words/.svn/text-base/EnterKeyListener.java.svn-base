package words;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EnterKeyListener implements KeyListener 
{
	private final WordFinder wordFinder;
	
	public EnterKeyListener(WordFinder wFind)
	{
		this.wordFinder = wFind;
	}


	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER)
		{
			System.out.println("Enter key pressed!");
			this.wordFinder.doSearch();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
