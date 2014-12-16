import java.io.Serializable;


public class MineData implements Serializable
{
	int width;
	int height;
	int countMines = 0;
	int setMaxMines;
	int[][] mineArray;
	String[][] guiLabels;
	int flagCount;
	int[][] revealArr;
	
	
	public MineData(int width1, int height1, int setMaxMines1)
	{
		width = width1;
		height = height1;
		setMaxMines = setMaxMines1;
		revealArr = new int[height][width];
		mineArray = new int[height][width]; //mine array
		guiLabels = new String[height][width];
	}
}
