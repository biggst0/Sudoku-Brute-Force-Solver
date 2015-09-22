package sudoku;

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
	 * This method takes a text file and scans it. It takes the width and length
	 * of the sudoku and creates a 2 dimension array. Then it loads the integers
	 * into the array.
	 * 
	 * @param String
	 *            of the location of the text file
	 * @return
	 * @return
	 * @throws IOException
	 *             If the text file cannot be found
	 */
	public void readFile(String fileName) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(fileName)); // starts the scanner

		if ((!sc.hasNextInt()))// if the next input is not an int
		{
			sc.next().charAt(0); // remove the first character at the first
									// position
		}						
		width = sc.nextInt();// Assigns next int to width
		height = sc.nextInt();// Assigns next int to height
	
		magicNum = width * height;// assign width * height to the magicNum
		testArray = new int[magicNum][magicNum];// Declare a 2Dimension array
		refArray = new int[magicNum][magicNum];// Declare a 2Dimension array

		int j = 0;// declare and initialize int j to 0
		int i = 0;// declare and initialize int i to 0

		int blankCounter = 0;
		while (sc.hasNextInt()) {// while scanner still has a next int
			int k = sc.nextInt();// assigned next int to variable k

			testArray[i][j] = k;// insert variable k in the test array
			refArray[i][j] = k;// insert variable k in the ref array
			if (k == 0)// if variable k == 0
				blankCounter++;// increase counter by 1
			if (j == magicNum - 1)// if magicNum - 1 is equal to j
			{
				j = 0;// set j equal to j equal to zero
				i++;// increase i by 1
			} else// else
			{
				j++;// increase j by 1.
			}

		}
		sequenceLength = blankCounter;
		sc.close();// close scanner.

	}

	public void solverControl() throws FileNotFoundException 
	{
		
		readFile("C:/Users/Travis/git/Sudoku-Brute-Force-Solver/Sudoku-Brute-Force-Solver/src/soduku.txt");
		
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

	// initializes sequence[] and sets all values to 1.
	public void initSequence() {
		sequence = new int[sequenceLength];
		for (int i = 0; i < sequenceLength; i++)
			sequence[i] = 1;
	}

	// increments sequence[] from left to right..
	// returns true as long as there is another possible sequence value.
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

	// loads sequence[] values into testArray[][] using refArray[][] as a
	// reference.
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

	// checks each cell for a matching value in that cell's row.
	// returns true if a match is found and false if a match is not.
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

	// checks each cell for a matching value in that cell's column.
	// returns true if a match is found and false if no match is found.
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
