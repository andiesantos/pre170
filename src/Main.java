import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Java Review Exer");
		Container container = frame.getContentPane();
		JPanel mainPanel = new JPanel(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		
		Sidebar east = new Sidebar();
		Center center = new Center();
		mainPanel.add(east, BorderLayout.LINE_END);
		mainPanel.add(center, BorderLayout.CENTER);
		
		container.add(mainPanel);
		
		frame.pack();
		frame.setVisible(true);
		
		ActionListener askUserInput = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int endNode = Integer.parseInt(JOptionPane.showInputDialog("Input end node"));
					//CHECKERS
					if (endNode > 0 && endNode < center.getValidNodes()) {
						System.out.println("End node for Dijkstra's Algo: " + endNode);
					} else {
						System.out.println("Not a valid node");
					}
				} catch(Exception c) {
					System.out.println(c.getMessage());
				}
				
			}
		};
		east.getDijkstraButton().addActionListener(askUserInput);
	}
}