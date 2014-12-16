import javax.swing.JButton;


public class RevealFromLoad
{
	JButton[][] buttArr; int[][] revealArr; String[][] guiLabels;
	public RevealFromLoad(JButton[][] buttArr1, int[][] revealArr1, String[][] guiLabels1)
	{
		buttArr = buttArr1;
		revealArr = revealArr1;
		guiLabels = guiLabels1;
		
	}
	public void revealFromLoad()
	{
		for(int row = 0; row<revealArr.length; row++)
		{
			for(int col = 0; col<revealArr[row].length;col++)
			{
				if(revealArr[row][col] == 0)
				{
					
				}
			    else if(revealArr[row][col] == 1)
				{
					buttArr[row][col].setText(guiLabels[row][col]);
				}
				else if(revealArr[row][col] == 2)
				{
					buttArr[row][col].setText("FLAG");
				}
				
			}
		}
	}
}
