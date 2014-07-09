package maze;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MazePanel extends Container {

	private static final long serialVersionUID = 1L;
	private final MazeGame mazeGame;
	private Dimension dim;
	
	public MazePanel(MazeGame mg, int room_size, int margin) { 
		mazeGame = mg; 
		if (mazeGame != null) {
			mazeGame.setView(this); 
			Dimension d = mazeGame.getDimension();
			if (d != null) { 
				dim = new Dimension(d.width * room_size + 2 * margin, 
						d.height * room_size + 2 * margin);
			}
			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					mazeGame.step();
				}
			});
		}
	}

	public void paint(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height); 
		if (mazeGame != null) { 
			mazeGame.draw(g); 
		}
		requestFocus();
	}

	public boolean isFocusable() {
		return true; 
	}

	public Dimension getPreferredSize() { 
		return dim; 
	}

	public Dimension getMinimumSize() { 
		return dim;
	}

}
