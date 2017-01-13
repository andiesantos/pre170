import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
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
		
		//ACTION LISTENERS FOR BUTTONS IN SIDE PANEL
		//Dijkstra's Algo: ask for user input for end node (default start node: 0)
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
		
		//BFS and DFS: show the graph traversal in a popup
		ActionListener showDFS = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, Dfs.getTraversal());
			}
		};
		
		ActionListener showBFS = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, Bfs.getTraversal());
			}
		};
		
		east.getDijkstraButton().addActionListener(askUserInput);
		east.getDFSButton().addActionListener(showDFS);
		east.getBFSButton().addActionListener(showBFS);

	}
}