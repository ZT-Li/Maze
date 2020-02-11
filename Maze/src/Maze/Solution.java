package Maze;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/*
 * This class glues the whole program together by solving and printing the solution
 */
public class Solution {
	int size;
	String path = "";

	public Solution(int size) {
		this.size = size;
	}
	

	/*
	 * solutionGenerator takes path as a parameter and prints the solution inside a txt file. 
	 */
	public void solutionGenerator(String path) throws FileNotFoundException {
		this.path=path;
		PrintStream fileOut = new PrintStream(path);
		PrintStream old = System.out;
		System.setOut(fileOut);
		Maze m = new Maze(size);  //creating a maze
		System.out.println("Graph Size: " + size);
		System.out.println("Maze: ");
		m.perfectMaze();  //converting the maze to a perfect maze
		m.draw(); //printing the perfect maze
		System.out.println();
		System.out.println("DFS: ");
		m.DFSsolution();  //solving the maze using DFS
		m.draw();  //printing the DFS solved maze
		System.out.println();
		m.trace(); //tracing the path with "#" on the DFS solved maze
		System.out.println("Path: " + m.getPath());
		System.out.println("Length of path: " + m.pathLength());
		System.out.println("Visited cells: " + m.getDFSVisited());
		System.out.println();

		System.out.println("BFS: ");
		m.BFSsolution();  //BFS solving the maze
		m.draw(); //printing the BFS solved maze
		System.out.println();
		m.trace(); //tracing the path with "#" on the BFS solved maze
		System.out.println("Path: " + m.getPath());
		System.out.println("Length of path: " + m.pathLength());
		System.out.println("Visited cells: " + m.getBFSVisited());
		System.out.flush();
		System.setOut(old);
	}

	
	
	/*
	 * solutionPrinter takes the txt file with solution and prints it on the console 
	 */
	public void solutionPrinter() throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null)
			System.out.println(st);
	}

}