package Maze;

import java.io.IOException;

/**
 * Just a normal tester to test the code
 */
public class tester {
	public static void main(String[] args) throws IOException {
		Solution maze = new Solution(3); // Creates a maze of n*n matrix
		maze.solutionGenerator("test.txt"); // creates a txt file of the solution
		maze.solutionPrinter(); // prints the solution from the txt file

	}
}