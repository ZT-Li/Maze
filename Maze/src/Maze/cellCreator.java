package Maze;

import java.util.*;

/*
 * This class creates a n*n matrix like 
 *  +-+-+ (2*2 matrix for example (doesn't print it just creates it)
    |a| | 
    +-+-+
    | | |
    +-+-+
  * and assigns each box a Cell class property with neighbors
  * After it creates the matrix, it uses DFS to create a perfect maze
 */
public class cellCreator {
	private static LinkedList<Integer> cells[]; // LinkedList that holds all index the neighbor of a cells
	private Cell[] cellArray; // an array of cells that holds all the cells on that matrix
	int matrixSize; // size of the matrix

	/*
	 * it creates a n*n matrix and assigns each class with its neighbors it uses
	 * adjacency list to assign all its neighbors and stores the row and columns of
	 * each cell.
	 * 
	 * @param matrixSize size of the matrix that your want to create
	 */
	public cellCreator(int matrixSize) {
		this.matrixSize = matrixSize;
		int row = 1;
		int column = 1;
		int total = matrixSize * matrixSize;
		cells = new LinkedList[total];
		cellArray = new Cell[total];

		for (int i = 0; i < total; i++) {
			cells[i] = new LinkedList();
			if (i + matrixSize < total) {
				cells[i].add(i + matrixSize);
			}
			if (i - matrixSize >= 0) {
				cells[i].add(i - matrixSize);
			}
			if ((i + 1) % matrixSize != 0) {
				cells[i].add(i + 1);
			}
			if (i % matrixSize != 0) {
				cells[i].add(i - 1);
			}
			cellArray[i] = new Cell(row, column, i, cells[i]);
			column += 2;
			if ((i + 1) % matrixSize == 0) {
				row += 2;
				column = 1;
			}
		}
	}

	/*
	 * Using DFS, we create a perfect maze and with all the cells and their
	 * neighbors.
	 */
	public void cellDFS() {
		Stack<Cell> cStack = new Stack<Cell>();
		for (Cell c : cellArray) {
			cStack.add(c);
		}
		int total = cellArray.length;
		Cell currentCell = cellArray[0];
		int visited = 1;
		while (visited < total) {
			ArrayList<Cell> whiteList = new ArrayList<Cell>();
			for (int i : currentCell.getNeighbor()) {
				if (!cellArray[i].hasParent() && i != 0)
					whiteList.add(cellArray[i]);
			}
			if (whiteList.size() > 0) {
				Random r = new Random();
				int index = r.nextInt(whiteList.size());
				Cell temp = whiteList.get(index);
				temp.setParent(currentCell);
				currentCell.addChild(temp);
				cStack.push(currentCell);
				currentCell = whiteList.get(index);
				visited++;
			} else {
				currentCell = cStack.pop();
			}
		}
	}

	/*
	 * Returns the array of cell with all the cells inside it
	 */
	public Cell[] getCellList() {
		return cellArray;
	}
}