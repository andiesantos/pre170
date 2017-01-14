import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sidebar extends JPanel{
	private JPanel mainPanel;
	private JButton bfsButton;
	private JButton dfsButton;
	private JButton dijkstraButton;
	private int endNode;
	private Dfs dfs;
	private Bfs bfs;
	private Dijkstra dijkstra;
	
	public Sidebar() {
		super();
		add(setup());
	}
	
	public JPanel setup() {
		this.mainPanel = new JPanel(new GridLayout(3,1));
		endNode = 0;
		bfsButton = new JButton("BFS");
		dfsButton = new JButton("DFS");
		dijkstraButton = new JButton("Dijkstra's");
		dfs = new Dfs();
		bfs = new Bfs();
		dijkstra = new Dijkstra();
		
		
		ActionListener askUserInput = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endNode = Integer.parseInt(JOptionPane.showInputDialog("Input end node"));
				//CHECKERS
				if (endNode >= 0 && endNode < Center.getWam().length) {
					System.out.println("End node: " + endNode);
				} else {
					System.out.println("Invalid!");
				}
				Dijkstra.setEndNode(endNode);
				JOptionPane.showMessageDialog(mainPanel, Dijkstra.getPathString());
			}
		};
		
		dijkstraButton.addActionListener(askUserInput);
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