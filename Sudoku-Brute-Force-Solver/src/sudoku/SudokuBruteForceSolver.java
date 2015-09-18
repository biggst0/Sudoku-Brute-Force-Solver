package sudoku;

import java.util.Arrays;

public class SudokuBruteForceSolver 
{
	private int width;
	private int height;
	private int magicNum;
	private int[][] testArray;
	private int[][] refArray;
	private int[] sequence;
	private int sequenceLength;
	private int cols;
	private int rows;
	private int numCells;
	
	public SudokuBruteForceSolver()
	{
		
	}
	
	public void solverControl()
	{
		boolean solved = false;
		int i = sequenceLength^2;
		while(i > 0 || solved == true)
		{	
			if(checkRows() || checkCols() || checkBoxes() == false)
			{
				incrementSequence();
				loadSequence();
				i--;
				
			}
			else if(checkRows() && checkCols() && checkBoxes() == true)
			{
				solved = true;	
			}
		}
		printInformation(solved);
	}
	
	//initializes sequence[] and sets all values to 1.
	public void initSequence(){
		sequence = new int[sequenceLength];
		for(int i=0; i < sequenceLength; i++)
			sequence[i] = 1;
	}
	
	//increments sequence[] from right to left.
	//returns true as long as there is another possible sequence value.
	public boolean incrementSequence(){
		int carry = 1;
		int position = sequenceLength - 1;
		while (carry != 0 && position >= 0){
			if (sequence[position] == magicNum){
				sequence[position] = 1;
				carry = 1;			
				position--;
			}
			else{
				sequence[position]++;
				carry = 0;
			}
		}
		if (position < 0){
			return false;
		}
		return true;
	}
	
	//loads sequence[] values into testArray[][] using refArray[][] as a reference.
	public void loadSequence(){
		int sequencePos = 0;
		for (int rowCount = 0; rowCount < rows; rowCount++){
			for (int colCount = 0; colCount < cols; colCount++){
				if(refArray[rowCount][colCount] == 0){
					testArray[rowCount][colCount] = sequence[sequencePos];
					sequencePos++;
				}
			}
		}
	}
	
	//checks each cell for a matching value in that cell's row.
	//returns true if a match is found and false if a match is not.
	public boolean checkRows(){
		int currentRow = 0;
		int currentCol = 0;
		int currentCellVal = 0;
		for (int cellCount = 0; cellCount < numCells; cellCount++){
			currentCellVal = testArray[currentRow][currentCol];
			System.out.println("Checked row "+currentRow + " column " + currentCol);
			for (int checkCol = currentCol + 1; checkCol < cols; checkCol++){
				if  (testArray[currentRow][checkCol] == currentCellVal)
					return false;
			
			}
			if (currentCol < cols - 1) 
				currentCol++;
			else{
				currentCol = 0;
				currentRow++;
			}
		}
		return true;	
	}
	
	//checks each cell for a matching value in that cell's column.
	//returns true if a match is found and false if no match is found.
	public boolean checkCols(){
		int currentRow = 0;
		int currentCol = 0;
		int currentCellVal = 0;
		for (int cellCount = 0; cellCount < numCells; cellCount++){
			currentCellVal = testArray[currentRow][currentCol];
			System.out.println("Checked row "+currentRow + " column " + currentCol);
			for (int checkRow = currentRow + 1; checkRow < rows; checkRow++){
				if  (testArray[checkRow][currentCol] == currentCellVal)
					return false;
			
			}
			if (currentRow < rows - 1) 
				currentRow++;
			else{
				currentRow = 0;
				currentCol++;
			}
		}
		return true;
	}
	
	public boolean checkBoxes() 
	{
		boolean[] cellCompareArray = new boolean[magicNum + 1];
		int compareNum = 0;
		int currentRow = 0;
		int currentColumn = 0;
		int x = 0;
		int y = 0;
		int i = 0;
		while(i < magicNum)
		{ 
			Arrays.fill(cellCompareArray, false);
		    int j = (x / width) * width;
		    int k = (y / height) * height;
		 
		    for(currentRow = j; currentRow < j + width; currentRow++) 
		    {
		        for(currentColumn = k; currentColumn < k + height; currentColumn++) 
		        {
		        	System.out.println(testArray[currentColumn][currentRow]); //// For testing
		        	compareNum = testArray[currentColumn][currentRow];
					if(cellCompareArray[compareNum] == false)
					{
						cellCompareArray[compareNum] = true;
					}
					else
					{
						return false;
					}
		        }
		    }
		    
		    if(x + width < magicNum)
		    {
		    	x = x + width;
		    }
		    else
		    {
		    	x = 0;
		    	y = y + height;
		    }
		    
		    i++;
		}
	    return true;
	}
	
	public void printInformation(boolean solved)
	{
		if(solved == false)
		{
			System.out.println(" Sudoku can not be solved");
		}
		else
		{
			System.out.println(solved + " test print");
		}
		
	}
