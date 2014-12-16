import javax.swing.*;

import java.awt.*;


public class MinesweeperPanel extends JPanel
{
	int countMines=0;
	int setMaxMines;
	static int[][] surrMineArray;
	int flagCount;
	JFrame frame;
	public MinesweeperPanel(MineData data, JFrame frame1)
	{
		setMaxMines = data.setMaxMines;
		surrMineArray = new int[data.height][data.width];
		frame = frame1;
		setLayout(new GridLayout(data.width,data.height));
		JButton[][] buttArray = new JButton[data.width][data.height];
		
		//Set mines
		while(countMines<setMaxMines)
		{
			int rand1 = (int)(Math.random()*(data.width-1));
			int rand2 = (int)(Math.random()*(data.height-1));
			
			if(data.mineArray[rand1][rand2]!= 1)
			{
				data.mineArray[rand1][rand2] = 1; //1 equals mine, default 0 equals no mine
				countMines++;
			}
		}
		
		getSurrMines(data.mineArray);
		for(int r=data.width-1; r>=0; r--)
		{
			for(int c=data.height-1; c>=0; c--)
			{
				data.guiLabels[r][c] = new String("");
				data.guiLabels[r][c] = ""+surrMineArray[r][c];
			}
		}
		for(int r=data.width-1; r>=0; r--)
		{
			for(int c=data.height-1; c>=0; c--)
			{
				//buttArray[r][c] = new JButton(data.guiLabels[r][c]);
				buttArray[r][c] = new JButton();
				//buttArray[r][c] = new JButton("r:"+r+", c:"+c);
				buttArray[r][c].setOpaque(true);
				//1buttArray[r][c].addMouseListener(new CustomMouse(r,c,mineArray[r][c],data.guiLabels[r][c]));
				buttArray[r][c].addMouseListener(new CustomMouse(r,c,data.guiLabels[r][c],buttArray[r][c],setMaxMines,frame,data.mineArray, buttArray, data.revealArr));
				add(buttArray[r][c]);
			}
		}
	}
	
	public MinesweeperPanel(MineData data, JFrame frame1, boolean Load)
	{
		surrMineArray = new int[data.height][data.width];
		setMaxMines = data.setMaxMines;
		
		frame = frame1;
		setLayout(new GridLayout(data.width,data.height));
		JButton[][] buttArray = new JButton[data.width][data.height];
		
		getSurrMines(data.mineArray);
		for(int r=data.width-1; r>=0; r--)
		{
			for(int c=data.height-1; c>=0; c--)
			{
				data.guiLabels[r][c] = new String("");
				data.guiLabels[r][c] = ""+surrMineArray[r][c];
			}
		}
		
		for(int r=data.width-1; r>=0; r--)
		{
			for(int c=data.height-1; c>=0; c--)
			{
				//buttArray[r][c] = new JButton(data.guiLabels[r][c]);
				buttArray[r][c] = new JButton();
				//buttArray[r][c] = new JButton("r:"+r+", c:"+c);
				buttArray[r][c].setOpaque(true);
				//1buttArray[r][c].addMouseListener(new CustomMouse(r,c,mineArray[r][c],data.guiLabels[r][c]));
				buttArray[r][c].addMouseListener(new CustomMouse(r,c,data.guiLabels[r][c],buttArray[r][c],setMaxMines,frame,data.mineArray, buttArray, data.revealArr));
				add(buttArray[r][c]);
			}
		}
		RevealFromLoad revealLoad = new RevealFromLoad(buttArray, data.revealArr, data.guiLabels);
		revealLoad.revealFromLoad();
	}
	
	public void getSurrMines(int[][] mineArray)
	{
		for(int r=mineArray.length-1; r>=0; r--)
		{
			for(int c=mineArray[r].length-1; c>=0; c--)
			{
				try
				{
					//(r+1<=data.height-1 && r-1>=0 && c+1<=data.width-1 && c-1>=0) modify these conditions on each if statement
					//to avoid array errors and to avoid skipping some counts
					if(c+1<=mineArray[r].length-1 && mineArray[r][c+1]==1)//check right
					{
						surrMineArray[r][c]++;
					}
					if(r+1<=mineArray.length-1 && c+1<=mineArray[r].length-1 && mineArray[r+1][c+1]==1)//check top right
					{
						surrMineArray[r][c]++;
					}
					if(r+1<=mineArray.length-1 && mineArray[r+1][c]==1)//check top
					{
						surrMineArray[r][c]++;
					}
					if(r+1<=mineArray.length-1 && c-1>=0 && mineArray[r+1][c-1]==1)//check top left
					{
						surrMineArray[r][c]++;
					}
					if(c-1>=0 && mineArray[r][c-1]==1)//check left
					{
						surrMineArray[r][c]++;
					}
					if(r-1>=0 && c-1>=0 && mineArray[r-1][c-1]==1)//check bottom left
					{
						surrMineArray[r][c]++;
					}
					if(r-1>=0 && mineArray[r-1][c]==1)//check bottom
					{
						surrMineArray[r][c]++;
					}
					if(r-1>=0 && c+1<=mineArray[r].length-1 && mineArray[r-1][c+1]==1)//check bottom right
					{
						surrMineArray[r][c]++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("r:"+r+" c:"+c);
				}
				
				if(mineArray[r][c]==1)
				{
					surrMineArray[r][c] = 999; //death
				}
			}
		}
	}
}
