/**
 * Class for keeping the seeds methods.
 * Each seed method define the initial state
 * of each cell.  
 * @author Paolo Boschini
 *
 */
public class Seeds {

	private Cell[] cells;
	private int cellsPerSize;
	
	public Seeds(Cell[] cells, int widthBoard) {
		this.cells = cells;
		this.cellsPerSize = widthBoard;
	}

	public void pulsar() {
		int i = cellsPerSize*(cellsPerSize/2) + cellsPerSize/2;
		cells[i-cellsPerSize*6-2].wakeup();    	
		cells[i-cellsPerSize*6-3].wakeup();    	
		cells[i-cellsPerSize*6-4].wakeup();    	

		cells[i-cellsPerSize*6+2].wakeup();    	
		cells[i-cellsPerSize*6+3].wakeup();    	
		cells[i-cellsPerSize*6+4].wakeup();    	

		cells[i-cellsPerSize*4-6].wakeup();    	
		cells[i-cellsPerSize*4+6].wakeup();    	
		cells[i-cellsPerSize*4-1].wakeup();    	
		cells[i-cellsPerSize*4+1].wakeup();    	

		cells[i-cellsPerSize*3-6].wakeup();    	
		cells[i-cellsPerSize*3+6].wakeup();    	
		cells[i-cellsPerSize*3-1].wakeup();    	
		cells[i-cellsPerSize*3+1].wakeup();    	

		cells[i-cellsPerSize*2-6].wakeup();    	
		cells[i-cellsPerSize*2+6].wakeup();    	
		cells[i-cellsPerSize*2-1].wakeup();    	
		cells[i-cellsPerSize*2+1].wakeup();    	

		cells[i-cellsPerSize*1-2].wakeup();    	
		cells[i-cellsPerSize*1-3].wakeup();    	
		cells[i-cellsPerSize*1-4].wakeup();    	

		cells[i-cellsPerSize*1+2].wakeup();    	
		cells[i-cellsPerSize*1+3].wakeup();    	
		cells[i-cellsPerSize*1+4].wakeup();

		// bottom two
		cells[i+cellsPerSize*6-2].wakeup();    	
		cells[i+cellsPerSize*6-3].wakeup();    	
		cells[i+cellsPerSize*6-4].wakeup();    	

		cells[i+cellsPerSize*6+2].wakeup();    	
		cells[i+cellsPerSize*6+3].wakeup();    	
		cells[i+cellsPerSize*6+4].wakeup();    	

		cells[i+cellsPerSize*4-6].wakeup();    	
		cells[i+cellsPerSize*4+6].wakeup();    	
		cells[i+cellsPerSize*4-1].wakeup();    	
		cells[i+cellsPerSize*4+1].wakeup();    	

		cells[i+cellsPerSize*3-6].wakeup();    	
		cells[i+cellsPerSize*3+6].wakeup();    	
		cells[i+cellsPerSize*3-1].wakeup();    	
		cells[i+cellsPerSize*3+1].wakeup();    	

		cells[i+cellsPerSize*2-6].wakeup();    	
		cells[i+cellsPerSize*2+6].wakeup();    	
		cells[i+cellsPerSize*2-1].wakeup();    	
		cells[i+cellsPerSize*2+1].wakeup();    	

		cells[i+cellsPerSize*1-2].wakeup();    	
		cells[i+cellsPerSize*1-3].wakeup();    	
		cells[i+cellsPerSize*1-4].wakeup();    	

		cells[i+cellsPerSize*1+2].wakeup();    	
		cells[i+cellsPerSize*1+3].wakeup();    	
		cells[i+cellsPerSize*1+4].wakeup();    	

	}

	public void tumbler() {
		int i = cellsPerSize*(cellsPerSize/2) + cellsPerSize/2;
		cells[i+1].wakeup();    	
		cells[i+2].wakeup();    	
		cells[i-1].wakeup();    	
		cells[i-2].wakeup();    	

		cells[i+cellsPerSize+1].wakeup();    	
		cells[i+cellsPerSize+2].wakeup();    	
		cells[i+cellsPerSize-1].wakeup();    	
		cells[i+cellsPerSize-2].wakeup();    	

		cells[i+(cellsPerSize*2)+1].wakeup();    	
		cells[i+(cellsPerSize*2)-1].wakeup();    	

		cells[i+(cellsPerSize*3)+1].wakeup();    	
		cells[i+(cellsPerSize*3)-1].wakeup();    	
		cells[i+(cellsPerSize*3)+3].wakeup();    	
		cells[i+(cellsPerSize*3)-3].wakeup();    	

		cells[i+(cellsPerSize*4)+1].wakeup();    	
		cells[i+(cellsPerSize*4)-1].wakeup();    	
		cells[i+(cellsPerSize*4)+3].wakeup();    	
		cells[i+(cellsPerSize*4)-3].wakeup();    	

		cells[i+(cellsPerSize*5)+2].wakeup();    	
		cells[i+(cellsPerSize*5)-2].wakeup();    	
		cells[i+(cellsPerSize*5)+3].wakeup();    	
		cells[i+(cellsPerSize*5)-3].wakeup();    	
	}

	public void tenCellsInARow() {
		int i = cellsPerSize*(cellsPerSize/2) + cellsPerSize/2;
		cells[i].wakeup();
		for (int j = 1; j < 5; j++) {
			cells[i+j].wakeup();
			cells[i-j].wakeup();
		}
		cells[i-5].wakeup();
	}

	public void exploder() {
		int i = cellsPerSize*(cellsPerSize/2) + cellsPerSize/2;
		cells[i].wakeup();
		cells[i+(cellsPerSize*4)].wakeup();
		cells[i-2].wakeup();
		cells[i+2].wakeup();
		cells[i+cellsPerSize-2].wakeup();
		cells[i+cellsPerSize+2].wakeup();
		cells[i+(cellsPerSize*2)-2].wakeup();
		cells[i+(cellsPerSize*2)+2].wakeup();
		cells[i+(cellsPerSize*3)-2].wakeup();
		cells[i+(cellsPerSize*3)+2].wakeup();
		cells[i+(cellsPerSize*4)-2].wakeup();
		cells[i+(cellsPerSize*4)+2].wakeup();
	}

	public void glider() {
		int i = cellsPerSize+5;
		cells[i].wakeup();
		cells[i+cellsPerSize+1].wakeup();
		cells[i+(cellsPerSize*2)-1].wakeup();
		cells[i+(cellsPerSize*2)].wakeup();
		cells[i+(cellsPerSize*2)+1].wakeup();
	}

	public void smallExploder() {
		int i = cellsPerSize*(cellsPerSize/2) + cellsPerSize/2;
		cells[i].wakeup();
		cells[i+cellsPerSize-1].wakeup();
		cells[i+cellsPerSize].wakeup();
		cells[i+cellsPerSize+1].wakeup();
		cells[i+(cellsPerSize*2)-1].wakeup();
		cells[i+(cellsPerSize*2)+1].wakeup();
		cells[i+(cellsPerSize*3)].wakeup();
	}
}