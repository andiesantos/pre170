import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sidebar extends JPanel{
	private JPanel mainPanel;
	private JButton bfsButton;
	private JButton dfsButton;
	private JButton dijkstraButton;
	private String endNode;
	private Dfs dfs;
	private Bfs bfs;
	private Dijkstra dijkstra;
	
	public Sidebar() {
		super();
		add(setup());
	}
	
	public JPanel setup() {
		this.mainPanel = new JPanel(new GridLayout(3,1));
		endNode = "0";
		bfsButton = new JButton("BFS");
		dfsButton = new JButton("DFS");
		dijkstraButton = new JButton("Dijkstra's");
		dfs = new Dfs();
		bfs = new Bfs();
		dijkstra = new Dijkstra();
		
		
		ActionListener askUserInput = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endNode = JOptionPane.showInputDialog("Input end node");
				Dijkstra.setEndNode(endNode);
				JOptionPane.showMessageDialog(mainPanel, Dijkstra.getPathString());
			}
		};
		
		//BFS and DFS: show the graph traversal in a popup
		ActionListener showDFS = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainPanel, Dfs.getTraversal());
			}
		};
		
		ActionListener showBFS = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainPanel, Bfs.getTraversal());
			}
		};
		
		dijkstraButton.addActionListener(askUserInput);
		dfsButton.addActionListener(showDFS);
		bfsButton.addActionListener(showBFS);
		
		mainPanel.add(bfsButton);
		mainPanel.add(dfsButton);
		mainPanel.add(dijkstraButton);
		
		return mainPanel;
	}
	
	public JButton getDijkstraButton() {
		return dijkstraButton;
	}
	
	public JButton getDFSButton() {
		return dfsButton;
	}
	
	public JButton getBFSButton() {
		return bfsButton;
	}
}