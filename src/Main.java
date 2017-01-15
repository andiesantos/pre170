/*
 * SANTOS, ANDREA MARIE M.
 * ALLIANCE OF COMPUTER SCIENCE STUDENTS
 * PRE-CMSC170 REVIEW
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
		//Set up the frame
		JFrame frame = new JFrame("Java Review Exer");
		Container container = frame.getContentPane();
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 500));
		
		Center center = new Center();
		Sidebar east = new Sidebar();
		
		mainPanel.add(east, BorderLayout.LINE_END);
		mainPanel.add(center, BorderLayout.CENTER);
		
		container.add(mainPanel);
		
		frame.pack();
		frame.setVisible(true);

	}
}