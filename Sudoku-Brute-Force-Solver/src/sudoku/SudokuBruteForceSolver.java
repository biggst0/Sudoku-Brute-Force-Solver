package sudoku;

import java.util.Arrays;

public class SudokuBruteForceSolver 
{
	private int width;
	private int height;
	private int magicNum;
	private int[][] testArray;
	
	public SudokuBruteForceSolver()
	{
	
		
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
