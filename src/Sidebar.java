import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sidebar extends JPanel{
	private JPanel mainPanel;
	private JButton bfs;
	private JButton dfs;
	private JButton dijkstra;
	private int endNode;
	private Dfs dfs_mismo;
	
	public Sidebar() {
		super();
		add(setup());
	}
	
	public JPanel setup() {
		this.mainPanel = new JPanel(new GridLayout(3,1));
		bfs = new JButton("BFS");
		dfs = new JButton("DFS");
		dijkstra = new JButton("Dijkstra's");
		dfs_mismo = new Dfs();
		/*
		ActionListener askUserInput = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endNode = Integer.parseInt(JOptionPane.showInputDialog("Input end node"));
				//CHECKERS
			}
		};
		dijkstra.addActionListener(askUserInput);
		*/
		mainPanel.add(bfs);
		mainPanel.add(dfs);
		mainPanel.add(dijkstra);
		
		return mainPanel;
	}
	
	public JButton getDijkstraButton() {
		return dijkstra;
	}
	
	public JButton getDFSButton() {
		return dfs;
	}
	
	public JButton getBFSButton() {
		return bfs;
	}
}