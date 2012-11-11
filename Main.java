import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Game of life, see http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 * Entry point for the main frame and main thread
 * for updating the generations of the cells.
 * Click on a cell to change its state (wake up or kill it).
 * @author Paolo Boschini
 *
 */
public class Main implements Runnable {

	/** The main thread reference. */
	Thread t = new Thread(this);

	/** The frame for the universe. */  
	private JFrame frame;
	
	/** Panel for holding the cells. */  
	private JPanel board;

	/** Combo box for selecting the initial state. */
	private JComboBox comboBox;

	/** Check box for selecting whether to kill all the cells
	 * when a new seed is chosen.
	 */
	private JCheckBox checkBox;
	
	private JSlider slider;

	/** Number of cell per side in this universe. */
	final private int CELLS_PER_SIDE = 30;
	
	/** The cells of the game. */
	private Cell[] cells;
	
	/** 
	 * List of the same size as the cells list,
	 * where each element keeps track of the number
	 * of the alive neighbors.  
	 */
	private int[] neighbours;
	
	/**
	 * Reference to the seed object for calling the
	 * different seeds.
	 */
	private Seeds seeds;

	/**
	 * Default constructor.
	 */
	public Main() {

		frame = new JFrame();

		/*
		 * Reflection is used to get the methods from an
		 * instance of Seeds to populate the combo box.
		 * When an entry from the combo box is chosen,
		 * the method with the same name will be called.  
		 */
		comboBox = new JComboBox(getSeedMethods().toArray());
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				if (checkBox.isSelected())
					for (Cell c : cells)
						c.kill();					

				JComboBox cb = (JComboBox) e.getSource();
				String seedMethod = (String)cb.getSelectedItem();
				Method method = null;
				try {
					method = Seeds.class.getDeclaredMethod(seedMethod);
					method.setAccessible(true);
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				}

				try {
					method.invoke(seeds);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}

			}
		});

		/*
		 * If selected, it resets all the cells.
		 * Crazy stuff happens if you don't reset.
		 */
		checkBox = new JCheckBox("kill cells");
		
		slider = new JSlider(10, 500, 80);

		//Create the label table
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 10 ), new JLabel("Fast") );
		labelTable.put( new Integer( 500 ), new JLabel("Slow") );
		slider.setInverted(true);
		slider.setPaintLabels(true);
		slider.setLabelTable( labelTable );
		
		JPanel northPanel = new JPanel();
		northPanel.add(comboBox);
		northPanel.add(checkBox);
		northPanel.add(slider);

		board = new JPanel(new GridLayout(CELLS_PER_SIDE, CELLS_PER_SIDE));
		cells = new Cell[CELLS_PER_SIDE*CELLS_PER_SIDE];
		neighbours = new int[CELLS_PER_SIDE*CELLS_PER_SIDE];
		seeds = new Seeds(cells, CELLS_PER_SIDE);
		fillBoard();

		// choose the first seed
		seeds.tumbler();

		frame.getContentPane().add(board, BorderLayout.CENTER);
		frame.getContentPane().add(northPanel,BorderLayout.NORTH);

		frame.pack();
		frame.setVisible(true);
		t.start();
	}

	/**
	 * Returns the seed methods from the class Seeds.
	 * This method is used to populate the combo box
	 * for selecting a seed method.
	 * @return the seed methods
	 */
	private List<String> getSeedMethods() {
		List<String> methods = new ArrayList<String>();
		for (Method m : Seeds.class.getDeclaredMethods())
			methods.add(m.getName());
		return methods;
	}


	/**
	 * Counts how many alive neighbors each cell has.  
	 */
	private void countAliveNeighbours() {
		for (int i = 0; i < CELLS_PER_SIDE*CELLS_PER_SIDE; ++i) {
			if (i < CELLS_PER_SIDE || i+1 % 30 == 0 || i % CELLS_PER_SIDE == 0 || i > (CELLS_PER_SIDE*CELLS_PER_SIDE)-CELLS_PER_SIDE-2) {
				neighbours[i] = 0;				
			}
			else {
				neighbours[i] = cells[i-CELLS_PER_SIDE-1].isAlive() + 
						cells[i-CELLS_PER_SIDE].isAlive() +
						cells[i-CELLS_PER_SIDE+1].isAlive() +
						cells[i-1].isAlive() +
						cells[i+1].isAlive() +
						cells[i+CELLS_PER_SIDE-1].isAlive() + 
						cells[i+CELLS_PER_SIDE].isAlive() +
						cells[i+CELLS_PER_SIDE+1].isAlive();
			}
		}
	}

	/**
	 * Looping thread for updating the universe.
	 */
	public void run(){

		// Wait one second before starting. 
		try{
			Thread.sleep(1000);
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}

		while(true){
			try{
				countAliveNeighbours();
				updateBoard();
				board.repaint();
				Thread.sleep(slider.getValue());
			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}

	}

	/**
	 * Update board for next generation.
	 */
	private void updateBoard() {
		for (int i = 0; i < CELLS_PER_SIDE*CELLS_PER_SIDE; ++i) {
			if (cells[i].isAlive() == 1) {
				if (neighbours[i] < 2 || neighbours[i] > 3) {
					cells[i].kill();
				}
			}
			else {
				if (neighbours[i] == 3) {
					cells[i].wakeup();
				}            			
			}
		}
	}

	/**
	 * Creates cells, adds them to the universe, and fills the cells array.
	 */
	private void fillBoard() {
		for (int i = 0; i < CELLS_PER_SIDE*CELLS_PER_SIDE; i++) {
			Cell cell = new Cell();
			board.add(cell);
			cells[i] = cell;
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}