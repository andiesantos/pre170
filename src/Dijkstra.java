import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
	private Integer[][] nodes; //Weighted adjacency matrix
	private static int[][] weights; 
		/*
		Matrix showing if the node has been visited, its distance from the starting
		node, and the node visited before it
		*/
	private int startingNode;
	private static int endNode; //End node input by the user
	private Integer inf; //Infinity
	private static String returnString; //Always present in the popup
	private static String pathString; 
		//Holds "returnString" and the traversal of nodes in String form
	private static ArrayList<Integer> path; //Holds the traversal of nodes
	
	public Dijkstra() {
		startingNode = 0; //Default value is 0
		inf = Integer.MAX_VALUE;
		path = new ArrayList<Integer>();
		returnString = "Dijkstra's algorithm returned this shortest path:\n";
		
		getNodes();
		weights = new int[nodes.length][3];
		setWeights(inf, startingNode);
		
		for (int i=0; i<weights.length; i++) {
			startingNode = doDijkstra(startingNode, nodes, weights);
			printWeights(weights);
		}
	}
	
	public void getNodes() { //Get the weighted adjacency matrix
		nodes = Center.getWam();
	}
	
	//Sets the user input as the end node and finds its path from the starting vertex
	public static void setEndNode(String end) { 
		endNode = Integer.parseInt(end);
		path = findPath(endNode, weights);
		pathString = "";
		pathString = setPathString(path, returnString);
		
		//COOL DOGS - Prints the end node input by the user and 
		//	its path from the starting vertex
		System.out.println("End node in Dijkstra: " + endNode);
		for (int i=0; i<path.size(); i++) {
			System.out.print(path.get(i) + " ");
		}
	}
		
	//Sets the "weights" table's initial values
	public void setWeights(Integer inf, int s) {
		for (int i=0; i<weights.length; i++) {
			weights[i][0] = 0;	//If the node has been visited
			weights[i][1] = inf;//Its distance from the starting vertex
			weights[i][2] = -1;	//The node previously visited
		}
		weights[s][1] = 0;
	}
	
	//Simulates Dijkstra's Algorithm
	public int doDijkstra(int startNode, Integer[][] nodes, int[][] w) {
		int dv = 0, rep = 0;
		w[startNode][0] = 1; //"visited"
		//Set adjacent nodes' source and distance from starting point
		for (int i=0; i<nodes[startNode].length; i++) {
			dv = w[startNode][1] + nodes[startNode][i];
			if (nodes[startNode][i] != 0
				&& dv < w[i][1]
				&& i != startNode) {
				w[i][1] = dv; //Sets distance of current node
				w[i][2] = startNode; //Sets the source of current node
			}
			//Get largest possible distance from starting point
			if (dv >= rep) {
				rep = dv;
			}
		}
		//Get smallest distance from all points (unvisited points only)
		//	Choosing the next node to visit
		for (int j=0; j<w.length; j++) {
			if (w[j][0] == 0 //Node is unvisited
				&& w[j][1] <= rep
				&& j!=startNode) {
				rep = w[j][1]; //Set new smallest distance
				startNode = j;
			}
		}
		return startNode;
	}
	
	//COOL DOGS
	public void printWeights(int[][] weights) { //Prints the weights table.
		for (int i=0; i<weights.length; i++) {
            for (int j=0; j<weights[0].length; j++) {
                System.out.print(weights[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
	}
	
	//Finds the path of the starting node to the end node input by the user
	public static ArrayList<Integer> findPath(int endNode, int[][] weights) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(endNode);
		while (weights[endNode][2] != -1) {
			for (int i=0; i<weights.length; i++) {
				if (weights[endNode][2] == i) {
					endNode = i;
					path.add(endNode);
				}
			}
		}
		//Corrects the order of the nodes by reversing the path
		Collections.reverse(path); 
		return path;
	}
	
	//Sets the path String to be shown in a popup
	public static String setPathString(ArrayList<Integer> path, String returnString) {
		String ps = "";
		for (int i=0; i<path.size(); i++) {
			ps = ps + path.get(i) + " ";
		}
		ps = returnString + ps;
		return ps;
	}
	
	//Returns the path String
	public static String getPathString() {
		return pathString;
	}
}