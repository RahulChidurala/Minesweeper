import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CustomMouse2 extends MouseAdapter 
{
	int width, height; JFrame frame1;
	public CustomMouse2(int w, int h, JFrame frame)
	{
		width = w;
		height = h;
		frame1 = frame;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			MinesweeperFrame frm = new MinesweeperFrame(width,height);
			frm.setVisible(true);
			frame1.dispose();
		}
	}
}
