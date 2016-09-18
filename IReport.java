/**
 * I Report
 * 
 * @author Aries
 * This is our final project for AP Computer Science
 * 
 * This class is  is responsible for making the Window 
 * that the I Report will appear in
 */
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

public class IReport extends JFrame
{
		// attributes of this class
		private DrawingPanel dp; // where we will draw our images
		/** the constructor */
		public IReport()
		{
			super();
			dp = new DrawingPanel();
			Container c = getContentPane();
			c.add(dp);
			
		}
		

		
		
		
		
		/**the main method
		 *  this is the method used to create the game window and start the game
		 * @param args
		 */
		public static void main(String[] args) 
		{
			JFrame frame = new JFrame("I-Report Maker");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			DrawingPanel dp = new DrawingPanel();
			frame.add(dp);
			frame.setSize(600, 360);
			frame.setVisible(true);
		}

	
	
}
