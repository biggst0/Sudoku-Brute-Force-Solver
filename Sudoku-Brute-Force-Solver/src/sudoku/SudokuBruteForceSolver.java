package sudoku;

/**
 * This class provides the computation of solving a sudoku puzzle.
 * The solution is printed along with a solved statement and the time taken in milliseconds.
 * If the sudoku puzzle can not be solved the unsolved puzzle is printed along with a failed statement. 
 *
 * @author Travis Biggs
 * @author Larry Romano
 * @author Debra Lufadeju.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuBruteForceSolver 
{
	private int width;
	private int height;
	private int magicNum;
	private int[][] testArray;
	private int[][] refArray;
	private int[] sequence;
	private int sequenceLength;
	private int numCells;

	/**
	 * The solverControl method takes a String parameter of the fileName of a Sudoku puzzle.
	 * It then proceeds to initiate the sequence and check each test in a loop until the the puzzle is solved or determined unsolvable. 
	 * @param sudokuPuzzle, Name of the Sudoku puzzle being solved.
	 * @throws FileNotFoundException
	 */
	public void solverControl(String sudokuPuzzle)
	{
		try {
			readFile(sudokuPuzzle);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initSequence();
		boolean solved = false;
		int i = 0;
		while (solved != true && i < (Math.pow(magicNum, sequenceLength))) 
		{
			loadSequence();
			if (checkRows() == true && checkCols() == true && checkBoxes() == true) 
			{
				solved = true;
			}
			incrementSequence();
			i++;
		}
		printInformation(solved);
	}

	/**
	 * The initSequence method initializes sequence[] and sets all values to 1.
	 */
	public void initSequence() {
		sequence = new int[sequenceLength];
		for (int i = 0; i < sequenceLength; i++)
			sequence[i] = 1;
	}

	/**
	 * The incrementSequence method increments the current sequence left to right by 1.
	 * @return boolean, true as long as there is another possible sequence value and false otherwise.
	 */
	public boolean incrementSequence(){
		int carry = 1;
		int position = 0;
		while (carry != 0 && position <= sequenceLength - 1){
			if (sequence[position] == magicNum){
				sequence[position] = 1;
				carry = 1;
				position++;
			}
			else{
				sequence[position]++;
				carry = 0;
			}
		}
		if (position > sequenceLength - 1){
			return false;
		}
		return true;
	}
	
	
	/**
	 * This method takes a text file and scans it. It takes the width and length
	 * of the sudoku and creates a 2 dimension array. Then it loads the integers
	 * into the array.
	 * @param String of the location of the text file
	 * @throws IOException If the text file cannot be found
	 */
	public void readFile(String fileName) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(fileName));

		if ((!sc.hasNextInt()))
		{
			sc.nextLine();
		}	
		width = sc.nextInt();
		height = sc.nextInt();
	
		magicNum = width * height;
		testArray = new int[magicNum][magicNum];
		refArray = new int[magicNum][magicNum];

		int j = 0;
		int i = 0;

		int blankCounter = 0;
		while (sc.hasNextInt()) {
			int k = sc.nextInt();

			testArray[i][j] = k;
			refArray[i][j] = k;
			if (k == 0)
				blankCounter++;
			if (j == magicNum - 1){
				j = 0;
				i++;
			}
			else{
				j++;
			}
		}
		sequenceLength = blankCounter;
		sc.close();
	}

	/**
	 * The loadSequence method loads the current sequence into the testArray while using refArray as a template.
	 */
	public void loadSequence() {
		int sequencePos = 0;
		for (int rowCount = 0; rowCount < magicNum; rowCount++) {
			for (int colCount = 0; colCount < magicNum; colCount++) {
				if (refArray[rowCount][colCount] == 0) {
					testArray[rowCount][colCount] = sequence[sequencePos];
					sequencePos++;
				}
			}
		}
	}

	/**
	 * The checkCols method checks each row of the Sudoku puzzle. 
	 * @return boolean, Returns false if the test fails and true if the test passes.
	 */
	public boolean checkRows() {
		int currentRow = 0;
		int currentCol = 0;
		int currentCellVal = 0;
		for (int cellCount = 0; cellCount < numCells; cellCount++) {
			currentCellVal = testArray[currentRow][currentCol];
			System.out.println("Checked row " + currentRow + " column "
					+ currentCol);
			for (int checkCol = currentCol + 1; checkCol < magicNum; checkCol++) {
				if (testArray[currentRow][checkCol] == currentCellVal)
					return false;

			}
			if (currentCol < magicNum - 1)
				currentCol++;
			else {
				currentCol = 0;
				currentRow++;
			}
		}
		return true;
	}

	/**
	 * The checkCols method checks each column of the Sudoku puzzle. 
	 * @return boolean, Returns false if the test fails and true if the test passes.
	 */
	public boolean checkCols() {
		int currentRow = 0;
		int currentCol = 0;
		int currentCellVal = 0;
		for (int cellCount = 0; cellCount < numCells; cellCount++) {
			currentCellVal = testArray[currentRow][currentCol];
			System.out.println("Checked row " + currentRow + " column "
					+ currentCol);
			for (int checkRow = currentRow + 1; checkRow < magicNum; checkRow++) {
				if (testArray[checkRow][currentCol] == currentCellVal)
					return false;

			}
			if (currentRow < magicNum - 1)
				currentRow++;
			else {
				currentRow = 0;
				currentCol++;
			}
		}
		return true;
	}

	/**
	 * The checkBoxes method is a test that checks the individual boxes of the Sudoku.
	 * @return boolean, Returns false if the test fails and true if the test passes.
	 */
	public boolean checkBoxes() {
		boolean[] cellCompareArray = new boolean[magicNum + 1];
		int compareNum = 0;
		int currentRow = 0;
		int currentColumn = 0;
		int x = 0;
		int y = 0;
		int i = 0;
		while (i < magicNum) {
			Arrays.fill(cellCompareArray, false);
			int j = (x / width) * width;
			int k = (y / height) * height;

			for (currentRow = j; currentRow < j + width; currentRow++) {
				for (currentColumn = k; currentColumn < k + height; currentColumn++) {
					compareNum = testArray[currentColumn][currentRow];
					if (cellCompareArray[compareNum] == false) {
						cellCompareArray[compareNum] = true;
					} else {
						return false;
					}
				}
			}
			if (x + width < magicNum) {
				x = x + width;
			} else {
				x = 0;
				y = y + height;
			}
			i++;
		}
		return true;
	}

	public void setMagicNum(int magicNum) {
		this.magicNum = magicNum;
	}

	/**
	 * The printInformation method prints out the current puzzle, whether it was solved or not, and the time in milliseconds the puzzle takes to solve.
	 * @param solved - A boolean value which determines if the puzzle was solved
	 * 				 - If false, "Current Sudoku can not be solved." is printed along with the unsolved Sudoku and the taken time in milliseconds.
	 * 				 - If true, "Current Sudoku was solved!" is printed along with the solved Sudoku with its solution and the time taken in milliseconds.	
	 */
	public void printInformation(boolean solved) {
		int[][] finalArray;
		if (solved == false) {
			System.out.println("Current Sudoku can not be solved.");
			finalArray = refArray;
		} else {
			System.out.println("Current Sudoku was solved!");
			finalArray = testArray;
		}
		
		int i = 0;
		for (int rowCount = 0; rowCount < magicNum; rowCount++) {
			if ((i % height) == 0) 
			{
				printOutline();
			}
			i++;
			int k = 0;
			for (int colCount = 0; colCount < magicNum; colCount++) {
				if ((k % width) == 0) {
					System.out.print("| ");
				}
				System.out.print(finalArray[rowCount][colCount] + " ");
				k++;
			}
			System.out.print("| ");
			System.out.println();
		}
		printOutline();
	}

	/**
	 * The printOutline method sets up the outlines of the printed Sudoku in the printInformation method.
	 * 
	 */
	public void printOutline() {
		int i = 0;
		int j = 0;
		while (j < height) {
			System.out.print("+ ");
			while (i < width) {
				System.out.print("- ");
				i++;
			}
			i = 0;
			j++;
		}
		System.out.println("+");
	}
}
