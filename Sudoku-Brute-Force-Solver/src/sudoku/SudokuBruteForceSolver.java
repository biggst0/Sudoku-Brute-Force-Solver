package sudoku;

import java.util.Arrays;

public class SudokuBruteForceSolver 
{
	private int width;
	private int height;
	private int magicNum;
	private int[][] testArray;
	private int cols;
	private int rows;
	private int numCells;
	
	public SudokuBruteForceSolver()
	{
	
		
	}
	
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
	//the colCheck test
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
