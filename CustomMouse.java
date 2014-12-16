import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class CustomMouse extends MouseAdapter
{
	int r,c; String guiLabels; JButton buttArray; int count=0; JFrame frame; int[][] mineArray; JButton[][] buttArr; int[][] revealArr;
	
	public CustomMouse(int r1, int c1, String guiLabels1,JButton buttArray1, int setMaxMines1, 
			JFrame frame1, int[][] mineArray1, JButton[][] entireButtArray, int[][] revealArr1)
	{
		r = r1;
		c = c1;
		guiLabels = guiLabels1;
		buttArray = buttArray1;
		count = setMaxMines1;
		frame = frame1;
		mineArray = mineArray1;
		buttArr = entireButtArray;
		revealArr = revealArr1;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(guiLabels.equals("999"))
			{
				JFrame f = new JFrame();
				JLabel lose = new JLabel("You Lose!");
				f.setSize(50,50);
				f.add(lose);
				f.setVisible(true);
				
				for(int r2=0; r2<buttArr.length; r2++)
				{
					for(int c2=0; c2<buttArr[r2].length; c2++)
					{
						if(mineArray[r2][c2] == 0)
						{
							buttArr[r2][c2].setText(" ");
						}
						if(mineArray[r2][c2] == 1)
						{
							buttArr[r2][c2].setText("1");
						}
						for(int r3=0; r3<buttArr.length; r3++)
						{
							for(int c3=0; c3<buttArr[r3].length; c3++)
							{
								buttArr[r3][c3].setEnabled(false);
								buttArr[r3][c3].removeActionListener(null); //doesn't remove CustomMouse still
							}
						}
					}
				}
				
			}
			else
			{
				buttArray.setText(guiLabels);
				revealArr[r][c] = 1;
			}
		}
		else if(SwingUtilities.isRightMouseButton(e))
		{
			buttArray.setText("FLAG");
			revealArr[r][c] = 2;
			if(count>0)
			{
				count--;
			}
		}
	}
	
	public int getFlagCount()
	{
		return count;
	}
}
