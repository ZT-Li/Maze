package Maze;

/*
 * This class is for keeping track of the coordinates of cell on a matrix
 */
public class Coordinate {
	private int x; //x-coordinates or ith position on a matrix
	private int y; //y-coordinates or jth position on a matrix
	
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
}