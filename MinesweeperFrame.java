import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFrame;

public class MinesweeperFrame extends JFrame
{
	int defaultWidth = 7;
	int defaultHeight = 7;
	int setMaxMines;
	JFrame local = this;
	
	public MinesweeperFrame() //default constructor
	{
		new JFrame("Minesweeper By Rahul Chidurala");
		setSize(500,500);
		setMaxMines = (int)(defaultWidth*defaultHeight*0.27);
		final MineData data = new MineData(defaultWidth, defaultHeight, setMaxMines);
		final MinesweeperPanel pancakes = new MinesweeperPanel(data, this);
		add(pancakes); //and syrup
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		JMenuBar bar = new JMenuBar();
		JMenu File = new JMenu("File");
		final JMenuItem New = new JMenuItem("New");
		New.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				
				if(source == New)
				{
					JFrame difficulty = new JFrame("Choose Difficulty");
					difficulty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					difficulty.setSize(200,200);
					
					JButton easy = new JButton("Easy");
					easy.addMouseListener(new CustomMouse2(6,6,difficulty));
					JButton norm = new JButton("Normal");
					norm.addMouseListener(new CustomMouse2(7,7,difficulty));
					JButton hard = new JButton("Hard");
					hard.addMouseListener(new CustomMouse2(8,8,difficulty));
					JButton vet = new JButton("Veteran");
					vet.addMouseListener(new CustomMouse2(10,10,difficulty));
					JPanel panel = new JPanel();
					panel.add(easy);
					panel.add(norm);
					panel.add(hard);
					panel.add(vet);
					difficulty.add(panel);
					difficulty.setVisible(true);
					
				}
				
			}

			
		});
		final JMenuItem Save = new JMenuItem("Save");
		Save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				String savePath = "";
				
				if(source == Save)
				{
					JFileChooser saveSelect = new JFileChooser();
					saveSelect.setDialogTitle("Choose the directory for the save file");
					int select = saveSelect.showSaveDialog(null);
					if(select == JFileChooser.APPROVE_OPTION)
					{
						savePath = saveSelect.getSelectedFile()+"";
						
						try 
						{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(savePath));
							out.writeObject(data);
							out.close();
						} 
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						System.out.println("Successfully saved game to " + savePath);
					}
					else
					{
						
					}
				}
			}
		});
		final JMenuItem Load = new JMenuItem("Load");
		Load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				String openPath = "";
				
				if(source == Load)
				{
					JFileChooser openSelect = new JFileChooser();
					openSelect.setDialogTitle("Choose the directory for the save file");
					int select = openSelect.showOpenDialog(null);
					if(select == JFileChooser.APPROVE_OPTION)
					{
						openPath = openSelect.getSelectedFile()+"";
						
						try 
						{
							ObjectInputStream in = new ObjectInputStream(new FileInputStream(openPath));
							MineData dataLoaded = (MineData)(in.readObject());
							MinesweeperFrame frmLoad = new MinesweeperFrame(dataLoaded, true);
							frmLoad.setVisible(true);
						} 
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						System.out.println("Successfully opened game from file at " + openPath);
					}
					else
					{
						
					}
				}
			}
		});
		final JMenuItem Quit = new JMenuItem("Quit");
		Quit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				
				if(source == Quit)
				{
					local.dispose();
				}
				
			}

			
		});
		
		File.add(New);
		File.addSeparator();
		File.add(Save);
		File.addSeparator();
		File.add(Load);
		File.addSeparator();
		File.add(Quit);
		bar.add(File);
		
		this.setJMenuBar(bar);
	}
	
	public MinesweeperFrame(int width, int height) //New game constructor
	{
		new JFrame("Minesweeper By Rahul Chidurala");
		setSize(500,500);
		setMaxMines = (int)(width*height*0.27);
		final MineData data = new MineData(width, height, setMaxMines);
		final MinesweeperPanel pancakes = new MinesweeperPanel(data, this);
		add(pancakes); //and syrup
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		JMenuBar bar = new JMenuBar();
		JMenu File = new JMenu("File");
		final JMenuItem New = new JMenuItem("New");
		New.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				
				if(source == New)
				{
					JFrame difficulty = new JFrame("Choose Difficulty");
					difficulty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					difficulty.setSize(200,200);
					
					JButton easy = new JButton("Easy");
					easy.addMouseListener(new CustomMouse2(6,6,difficulty));
					JButton norm = new JButton("Normal");
					norm.addMouseListener(new CustomMouse2(7,7,difficulty));
					JButton hard = new JButton("Hard");
					hard.addMouseListener(new CustomMouse2(8,8,difficulty));
					JButton vet = new JButton("Veteran");
					vet.addMouseListener(new CustomMouse2(10,10,difficulty));
					JPanel panel = new JPanel();
					panel.add(easy);
					panel.add(norm);
					panel.add(hard);
					panel.add(vet);
					difficulty.add(panel);
					difficulty.setSize(100,200);
					difficulty.setVisible(true);
					
				}
				
			}

			
		});
		final JMenuItem Save = new JMenuItem("Save");
		Save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				String savePath = "";
				
				if(source == Save)
				{
					JFileChooser saveSelect = new JFileChooser();
					saveSelect.setDialogTitle("Choose the directory for the save file");
					int select = saveSelect.showSaveDialog(null);
					if(select == JFileChooser.APPROVE_OPTION)
					{
						savePath = saveSelect.getSelectedFile()+"";
						
						try 
						{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(savePath));
							out.writeObject(data);
							out.close();
						} 
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						System.out.println("Successfully saved game to " + savePath);
					}
					else
					{
						
					}
				}
			}
		});
		final JMenuItem Load = new JMenuItem("Load");
		Load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				String openPath = "";
				
				if(source == Load)
				{
					JFileChooser openSelect = new JFileChooser();
					openSelect.setDialogTitle("Choose the directory for the save file");
					int select = openSelect.showOpenDialog(null);
					if(select == JFileChooser.APPROVE_OPTION)
					{
						openPath = openSelect.getSelectedFile()+"";
						
						try 
						{
							ObjectInputStream in = new ObjectInputStream(new FileInputStream(openPath));
							MineData dataLoaded = (MineData)(in.readObject());
							MinesweeperFrame frmLoad = new MinesweeperFrame(dataLoaded, true);
							frmLoad.setVisible(true);
						} 
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						System.out.println("Successfully opened game from file at " + openPath);
					}
					else
					{
						
					}
				}
			}
		});
		final JMenuItem Quit = new JMenuItem("Quit");
		Quit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				
				if(source == Quit)
				{
					local.dispose();
				}
				
			}

			
		});
		
		File.add(New);
		File.addSeparator();
		File.add(Save);
		File.addSeparator();
		File.add(Load);
		File.addSeparator();
		File.add(Quit);
		bar.add(File);
		
		this.setJMenuBar(bar);
	}
	
	public MinesweeperFrame(MineData dataLoaded, boolean load)
	{
		new JFrame("Minesweeper By Rahul Chidurala");
		setSize(500,500);
		setMaxMines = (int)(defaultWidth*defaultHeight*0.27);
		final MineData data = dataLoaded;
		final MinesweeperPanel pancakes = new MinesweeperPanel(data, this, load);
		add(pancakes); //and syrup
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		JMenuBar bar = new JMenuBar();
		JMenu File = new JMenu("File");
		final JMenuItem New = new JMenuItem("New");
		New.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				
				if(source == New)
				{
					JFrame difficulty = new JFrame("Choose Difficulty");
					difficulty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					difficulty.setSize(200,200);
					
					JButton easy = new JButton("Easy");
					easy.addMouseListener(new CustomMouse2(6,6,difficulty));
					JButton norm = new JButton("Normal");
					norm.addMouseListener(new CustomMouse2(7,7,difficulty));
					JButton hard = new JButton("Hard");
					hard.addMouseListener(new CustomMouse2(8,8,difficulty));
					JButton vet = new JButton("Veteran");
					vet.addMouseListener(new CustomMouse2(10,10,difficulty));
					JPanel panel = new JPanel();
					panel.add(easy);
					panel.add(norm);
					panel.add(hard);
					panel.add(vet);
					difficulty.add(panel);
					difficulty.setVisible(true);
					
				}
				
			}

			
		});
		final JMenuItem Save = new JMenuItem("Save");
		Save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				String savePath = "";
				
				if(source == Save)
				{
					JFileChooser saveSelect = new JFileChooser();
					saveSelect.setDialogTitle("Choose the directory for the save file");
					int select = saveSelect.showSaveDialog(null);
					if(select == JFileChooser.APPROVE_OPTION)
					{
						savePath = saveSelect.getSelectedFile()+"";
						
						try 
						{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(savePath));
							out.writeObject(data);
							out.close();
						} 
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						System.out.println("Successfully saved game to " + savePath);
					}
					else
					{
						
					}
				}
			}
		});
		final JMenuItem Load = new JMenuItem("Load");
		Load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				String openPath = "";
				
				if(source == Load)
				{
					JFileChooser openSelect = new JFileChooser();
					openSelect.setDialogTitle("Choose the directory for the save file");
					int select = openSelect.showOpenDialog(null);
					if(select == JFileChooser.APPROVE_OPTION)
					{
						openPath = openSelect.getSelectedFile()+"";
						
						try 
						{
							ObjectInputStream in = new ObjectInputStream(new FileInputStream(openPath));
							MineData dataLoaded = (MineData)(in.readObject());
							MinesweeperFrame frmLoad = new MinesweeperFrame(dataLoaded, true);
							frmLoad.setVisible(true);
						} 
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						System.out.println("Successfully opened game from file at " + openPath);
					}
					else
					{
						
					}
				}
			}
		});
		final JMenuItem Quit = new JMenuItem("Quit");
		Quit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				Object source = e.getSource();
				
				if(source == Quit)
				{
					local.dispose();
				}
				
			}
		});
		
		File.add(New);
		File.addSeparator();
		File.add(Save);
		File.addSeparator();
		File.add(Load);
		File.addSeparator();
		File.add(Quit);
		bar.add(File);
		
		this.setJMenuBar(bar);
	}
}
