package Maze;

import java.util.*;

/*
 * This class is loaded. This class solves the maze using BFS, DFS and prints the maze 
 */
public class Maze {
	private String[][] maze; // a 2d array for creating the maze for printing ( "|", "+", "-")
	private Cell[] list; // an array of cells
	private ArrayList<Cell> path; // for printing the path
	private int BFSvisited; // stores the number of cells visited during BFS
	private int DFSvisited; // stores the number of cells visited during DFS

	/*
	 * creates an n*n 2d matrix and stores "|", "+", "-" in its respective place
	 */
	public Maze(int n) {
		if (n > 0) {
			int size = n * 2 + 1;
			maze = new String[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i % 2 == 0) {
						if (j % 2 == 0)
							maze[i][j] = "+";
						else
							maze[i][j] = "-";
					} else {
						if (j % 2 == 0)
							maze[i][j] = "|";
						else
							maze[i][j] = " ";
					}
				}
			}
			maze[0][1] = " ";
			maze[size - 1][size - 2] = " ";
		}
	}

	/*
	 * prints the maze with the help of the 2d matrix
	 */
	public void draw() {
		try {
			int size = maze.length;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++)
					System.out.print(maze[i][j]);
				System.out.println();
			}
		} catch (NullPointerException e) {
			System.out.println("input maze size is illegal");
		}
	}

	public int pathLength() { // returns the length of the path
		return path.size();
	}

	/*
	 * An arraylist of coordinates which stores respective co-ordinates of the cell
	 */
	public ArrayList<Coordinate> getPath() {
		ArrayList<Coordinate> cd = new ArrayList<Coordinate>();
		for (int i = pathLength() - 1; i > -1; i--) {
			Cell c = path.get(i);
			cd.add(new Coordinate((c.getRow() - 1) / 2, (c.getCol() - 1) / 2));
		}
		return cd;
	}

	/*
	 * removes the wall between a parent and its visited children
	 */
	public void wallRemove(Cell c) {
		if (c.hasParent()) {
			int row = c.getRow();
			int column = c.getCol();
			int row2 = c.getParent().getRow();
			int column2 = c.getParent().getCol();
			if (row == row2)
				maze[row][(column + column2) / 2] = " ";
			if (column == column2) {
				maze[(row + row2) / 2][column] = " ";
			}
		}
	}

	/*
	 * using the cellCreator class, it creates a perfect maze using the n*n matrix
	 */
	public void perfectMaze() {
		cellCreator c = new cellCreator(maze.length / 2);
		c.cellDFS();
		list = c.getCellList();
		for (Cell cell : list) {
			wallRemove(cell);
		}
		Cell exit = list[list.length - 1];
		path = new ArrayList<Cell>();
		while (exit.hasParent()) {
			path.add(exit);
			exit = exit.getParent();
		}
		path.add(list[0]);
	}

	/*
	 * We take the perfect maze and solve it using DFS
	 */
	public void DFSsolution() {
		Cell[] DFSlist = list;
		for (Cell c : DFSlist)
			c.setOrder(Integer.MAX_VALUE);
		DFSlist[0].setOrder(0);
		int order = 1;
		Cell target = DFSlist[0];
		int count = 1;
		while (target != DFSlist[DFSlist.length - 1]) {
			if (target.hasChild()) {
				target = target.getChild().get(0);
				target.setOrder(order);
				order++;
				count++;
				order %= 10;
			} else {
				target = target.getParent();
				target.removeChild();
			}
		}
		for (Cell c : DFSlist) {
			if (c.getOrder() != Integer.MAX_VALUE)
				maze[c.getRow()][c.getCol()] = "" + c.getOrder();
		}
		DFSvisited = count;
	}

	/*
	 * WE take the perfect maze and solve it using BFS
	 */
	public void BFSsolution() {
		Cell[] BFSlist = list;
		for (Cell c : BFSlist)
			c.toWhite();
		for (Cell c : BFSlist)
			c.setOrder(Integer.MAX_VALUE);
		BFSlist[0].setOrder(0);
		int order = 1;
		Cell target = BFSlist[0];
		int count = 0;
		target.toGrey();
		ArrayList<Cell> queue = new ArrayList<Cell>();
		queue.add(target);
		while (!queue.isEmpty() && target != BFSlist[BFSlist.length - 1]) {
			Cell temp = queue.remove(0);
			for (Cell child : temp.getChild()) {
				if (child.isWhite()) {
					child.toGrey();
					child.setOrder(order);
					order++;
					order %= 10;
					queue.add(child);
					if (child == BFSlist[BFSlist.length - 1]) {
						target = child;
						break;
					}
				}
			}
			temp.toBlack();
		}
		for (Cell c : BFSlist) {
			if (c.getOrder() != Integer.MAX_VALUE) {
				maze[c.getRow()][c.getCol()] = "" + c.getOrder();
				count++;
			}
		}
		BFSvisited = count;
	}

	/*
	 * Once the maze is solved using either BFS or DFS, it prints "#" as the path
	 * tracing to exit
	 */
	public void trace() {
		for (Cell c : list)
			maze[c.getRow()][c.getCol()] = " ";
		Cell endPoint = list[list.length - 1];
		while (endPoint.hasParent()) {
			int row = endPoint.getRow();
			int column = endPoint.getCol();
			maze[row][column] = "#";
			endPoint = endPoint.getParent();
		}
		maze[1][1] = "#";
		int size = maze.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				System.out.print(maze[i][j]);
			System.out.println();
		}
	}

	public Cell[] getCellList() { // returns the list of all the cells
		return list;
	}

	public int getDFSVisited() { // returns the number of cells visited during BFS
		return DFSvisited;
	}

	public int getBFSVisited() { // returns the number of cells visited during DFS
		return BFSvisited;
	}
}