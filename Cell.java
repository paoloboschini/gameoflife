import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A cell, can be dead or alive.
 * A cell responds to clicks, changing its state.
 * @author Paolo Boschini
 *
 */
public class Cell extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private boolean isAlive;
	
	/**
	 * Default constructor.
	 */
	public Cell() {
		setPreferredSize(new Dimension(20,20));
		setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.LIGHT_GRAY));
		setBackground(isAlive ? Color.BLACK : Color.WHITE);
		addMouseListener(this);
	}
	
	/**
	 * Returns the state of this cell.
	 * @return the state (alive of dead)
	 */
	public int isAlive() {
		return isAlive ? 1 : 0;
	}
	
	/**
	 * Wakes up the cell.
	 */
	public void wakeup() {
		isAlive = true;
		setBackground(Color.BLACK);
	}

	/**
	 * Kills the cell.
	 */
	public void kill() {
		isAlive = false;
		setBackground(Color.WHITE);
	}

	/**
	 * Change the state of this cell when clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		isAlive = !isAlive;
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}