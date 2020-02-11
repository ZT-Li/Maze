package Maze;

import java.util.*;

/*
 * This is a cell class. A cell represents a single room/block on the maze. 
 */
public class Cell {
	private int color; // keeps track of the color of the cell
	private int order; // keeps track of the position or order of the cell
	private int row; // keeps track of the row of the cell
	private int column; // keeps track of the column of the cell
	private int cellIndex; // numbering of the cell
	private Cell parent; // stores the parent of the cell(if any)
	private ArrayList<Cell> child; // stores all the children in an arraylist
	private LinkedList<Integer> neighbor; // stores all the neighbors

	public Cell(int row, int column, int cellIndex, LinkedList<Integer> neighbor) {
		this.row = row;
		this.column = column;
		this.cellIndex = cellIndex;
		this.neighbor = neighbor;
		color = 0;
		order = Integer.MAX_VALUE;
		parent = null;
		child = new ArrayList<Cell>();
	}

	public void toWhite() { // sets color to white
		color = 0;
	}

	public void toGrey() { // sets color to grey
		color = 1;
	}

	public void toBlack() { // sets color to black
		color = 2;
	}

	public boolean isWhite() { // checks if the color is white
		return color == 0;
	}

	public boolean isBlack() {// checks if the color is black
		return color == 2;
	}

	public void setOrder(int n) {
		order = n; // sets the order to n
	}

	public int getOrder() {
		return order; // returns the order
	}

	public void setParent(Cell c) {
		parent = c; // sets the parent of a cell class to cell "c"
	}

	public Cell getParent() {
		return parent; // returns the parent
	}

	public boolean hasParent() { // checks if a cell has a parent or not
		if (parent != null)
			return true;
		else
			return false;
	}

	public void addChild(Cell c) { // adds a children to a cell
		child.add(c);
	}

	public void removeChild() { // removes a children from cell
		child.remove(0);
	}

	public ArrayList<Cell> getChild() { // returns a children from a cell
		return child;
	}

	public boolean hasChild() { // checks if a cell has a children or not
		if (child.size() != 0)
			return true;
		else
			return false;
	}

	public int getRow() { // returns the row of the cell
		return row;
	}

	public int getCol() { // returns a column of a cell
		return column;
	}

	public int neighborSize() { // returns the number of neighbor of this cell
		return neighbor.size();
	}

	public LinkedList<Integer> getNeighbor() { // returns all the neighbor of this cell
		return neighbor;
	}

	public String toString() {
		return "row " + row + " colunm " + column + " Index " + cellIndex + " color " + color + " parent " + parent;
	}
}