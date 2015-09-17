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
			if (sequence[position] == magicNumber){
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
	
	public SudokuBruteForceSolver()
	{
	
		
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
		int[][] currentTestArray = testArray;
		boolean[] cellCompareArray = new boolean[magicNum + 1];
		int blockCount = width * height;
		int compareNum = 0;
		int x = 0;
		int y = 0;
		int i = 0;
		while(i < blockCount)
		{ 
			Arrays.fill(cellCompareArray, false);
		    int j = (x / width) * width;
		    int k = (y / height) * height;
		 
		    for(int currentRow = j; currentRow < j + width; currentRow++) 
		    {
		        for(int currentColumn = k; currentColumn < k + 3; currentColumn++) 
		        {
		        	compareNum = currentTestArray[j][k];
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
		    
		    if(x + 1 < magicNum)
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
	
	public void printInformation()
	{
		
		
	}
}
