import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Maze {

	/**
	 * Construct a Maze object. It also initializes DisjSets object with size = rows * cols
	 * @param rows number of rows of the maze
	 * @param cols number of columns of the maze
	 */
	Maze(int rows, int cols) {
		rowNumber = rows;
		colNumber = cols;
		
		mazeCellArray = initializeMazeCellArray(rowNumber, colNumber);
				
		d = new DisjSets(rowNumber * colNumber);
	}
	
	/**
	 * Create a Maze array with all four walls initially, except the first and last cell which would have one open wall
	 * @param rows number of rows of the Maze array
	 * @param cols number of columns of the Maze array
	 * @return
	 */
	private MazeCell[][] initializeMazeCellArray(int rows, int cols) {
		MazeCell[][] cells = new MazeCell[rowNumber][colNumber];
		
		for (int i = 0; i < rowNumber; i++) {
			for (int j = 0; j < colNumber; j++) {
				cells[i][j] = new MazeCell();
				
				/*if (i == 0 && j == 0) {
					cells[i][j].leftWall = false;	//Entrance of maze
				}*/
				if (i == (rowNumber - 1) && j == (colNumber - 1)) {
					cells[i][j].rightWall = false;	//Exit of maze
				}
			}
		}
		return cells;
	}
	
	/**
	 * Method to create the maze. A maze is created when every cell is connected to the other.
	 */
	private void createMaze() {
		int unionCount = 0;
		
		while (unionCount < (rowNumber * colNumber) - 1) {
			Random ran = new Random();
			int rowCell1 = ran.nextInt(rowNumber);
			int colCell1 = ran.nextInt(colNumber);
			
			int cell1 = rowCell1 * colNumber + colCell1;
			
			int wall = ran.nextInt(4);	
			
			int cell2 = 0;
			int rowCell2 = 0;
			int colCell2 = 0;
			switch(wall) {
			case 0:		//0: Left direction
				if (colCell1 != 0) {
					cell2 = cell1 - 1;	//If cell1 is a first column cell, and we chose left wall then cell2 can't be chosen
					rowCell2 = rowCell1;
					colCell2 = colCell1 - 1;
				}
				break;
			case 1:		//1: Upward direction
				if (rowCell1 != 0) {	//If cell1 is in first row, then cell2 can't be chosen
					cell2 = cell1 - colNumber;
					rowCell2 = rowCell1 - 1;
					colCell2 = colCell1;
				}
				break;
			case 2:		//2: Right direction
				if (colCell1 != (colNumber - 1)) {	//If cell1 in last row, then cell2 can't be chosen 
					cell2 = cell1 + 1;
					rowCell2 = rowCell1;
					colCell2 = colCell1 + 1;
				}
				else {
					colCell2 = (colNumber - 1);
				}
				break;
			case 3:		//3: Downward direction
				if (rowCell1 != (rowNumber - 1)) {	//if cell1 in last column, then cell2 can't be chosen
					cell2 = cell1 + colNumber;
					rowCell2 = rowCell1 + 1;
					colCell2 = colCell1;
				}
				else {
					rowCell2 = (rowNumber - 1);
				}
				break;
			}
					
			//Boundary conditions. If wall is near to any of the boundaries of cell, then union can't happen 
			if (!((colCell1 == 0 && wall == 0) || (colCell1 == (colNumber-1) &&  wall == 2) || (rowCell1 == 0 && wall == 1) || (rowCell1 == (rowNumber-1) && wall == 3)))
			{
				int cell1Root = d.find(cell1);
				int cell2Root = d.find(cell2);
				if (cell1Root != cell2Root) {
					d.union(cell1Root, cell2Root);		//Add a path between two cells
					unionCount++;	//Increment count whenever we do a union. Otherwise don't
				
						//Remove corresponding walls after union of two cells
					switch(wall) {
						case 0:
							//mazeCellArray[rowCell1][colCell1].leftWall = false;
							mazeCellArray[rowCell2][colCell2].rightWall = false;
							break;
						case 1:
							//mazeCellArray[rowCell1][colCell1].topWall = false;
							mazeCellArray[rowCell2][colCell2].bottomWall = false;
							break;
						case 2:
							mazeCellArray[rowCell1][colCell1].rightWall = false;
							//mazeCellArray[rowCell2][colCell2].leftWall = false;
							break;
						case 3:
							mazeCellArray[rowCell1][colCell1].bottomWall = false;
							//mazeCellArray[rowCell2][colCell2].topWall = false;
							break;
					}
				}
			}	
		}
	}
	
	/**
	 * Method to print the Maze in console
	 */
	private void printMaze() {
		
		for (int i = 0; i < colNumber; i++) {
			if (i == 0)
				System.out.print(" _ ");
			else
				System.out.print("_ ");	//Top Row of Maze
		}
		System.out.println();
			
		for (int i = 0; i < rowNumber; i++) {
			for (int j = 0; j < colNumber; j++) {
				if (j == 0) {
					if (i != 0)
						System.out.print("|");	//Left wall for first element of each column
					else
						System.out.print(" ");
				}
				
				if (mazeCellArray[i][j].bottomWall)
					System.out.print("_");
				else if (i == (rowNumber - 1))
					System.out.print("_");
				else
					System.out.print(" ");
				
				if (mazeCellArray[i][j].rightWall)
					System.out.print("|");
				else if (j == (colNumber - 1) && i != (rowNumber - 1))
						System.out.print("|");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
	}
	
	private int rowNumber;
	private int colNumber;
	DisjSets d;
	MazeCell[][] mazeCellArray;
	
	/**
	 * 
	 * @author Gayatri
	 * A Class for each cell of the Maze
	 */
	private class MazeCell {
		//boolean leftWall = true;
		boolean rightWall = true;
		//boolean topWall = true;
		boolean bottomWall = true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			System.out.println("Please enter number of rows and columns for maze : ");
			int row = sc.nextInt();
			int col = sc.nextInt();
			
			if (row < 1 || col < 1)
				throw new InputMismatchException();
			
			//initialize a maze
			Maze myMaze = new Maze(row, col);
			
			//Create the maze
			myMaze.createMaze();
			
			//Print the maze
			myMaze.printMaze();
		}
		catch (InputMismatchException e) {
			System.out.println("Please enter only integers greater than 0 for the maze");
			e.printStackTrace();
		}
		finally {
			if (sc != null)
				sc.close();
		}
	}
}
