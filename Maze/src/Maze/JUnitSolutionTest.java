package Maze;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

/*
 * Junit test of Maze Solver
 */
public class JUnitSolutionTest {

	@Test
	public void test() throws IOException {
		Solution a = new Solution(5);	//creating a 5*5 maze
		a.solutionGenerator("Key.txt");//location where you want to store the key file;
		PrintStream fileOut = new PrintStream("Solution.txt"); //making a txt file of a solution 
		PrintStream old = System.out;
        System.setOut(fileOut);
        a.solutionPrinter();//solving and storing in the solution txt file 
        System.out.flush();
        System.setOut(old);
        
        //a.solutionPrinter(); //use this if you also want to print the solution on the console :)
        
        
		BufferedReader In = new BufferedReader(new FileReader("Key.txt"));//path of the Key file
		BufferedReader Out = new BufferedReader(new FileReader("Solution.txt"));//path of the solution file
		String expectedLine;
		while((expectedLine = In.readLine())!=null) {   //compares the two txt files
			String actualLine = Out.readLine();
			assertEquals(expectedLine, actualLine);
		}
	}
	
	@Test
	public void test2() throws IOException {
		Solution a = new Solution(16);	//creating a 16*16 maze
		a.solutionGenerator("Key1.txt");//location where you want to store the key file;
		PrintStream fileOut = new PrintStream("Solution1.txt"); //making a txt file of a solution 
		PrintStream old = System.out;
        System.setOut(fileOut);
        a.solutionPrinter();//solving and storing in the solution txt file 
        System.out.flush();
        System.setOut(old);
        
        //a.solutionPrinter(); //use this if you also want to print the solution on the console :)
        
        
		BufferedReader In = new BufferedReader(new FileReader("Key1.txt"));//path of the Key file
		BufferedReader Out = new BufferedReader(new FileReader("Solution1.txt"));//path of the solution file
		String expectedLine;
		while((expectedLine = In.readLine())!=null) {   //compares the 2 txt files
			String actualLine = Out.readLine();
			assertEquals(expectedLine, actualLine);
		}
	}
	
	
	
	}
