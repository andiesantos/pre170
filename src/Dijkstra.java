import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
	private Integer[][] nodes;
	private static int[][] weights;
	private int startingNode;
	private static int endNode;
	private Integer inf;
	private static String pathString;
	private static ArrayList<Integer> path;
	
	public Dijkstra() {
		startingNode = 0; //by default
		inf = Integer.MAX_VALUE;
		path = new ArrayList<Integer>();
		pathString = "Dijkstra's algorithm returned this shortest path:\n";
		
		getNodes();
		weights = new int[nodes.length][3];
		setWeights(inf, startingNode);
		
		for (int i=0; i<weights.length; i++) {
			startingNode = doDijkstra(startingNode, nodes, weights);
			printWeights(weights);
		}
	}
	
	public void getNodes() {
		nodes = Center.getWam();
	}
	
	public static void setEndNode(int end) {
		endNode = end;
		System.out.println("End node in Dijkstra: " + endNode);
		path = findPath(endNode, weights);
		for (int i=0; i<path.size(); i++) {
			System.out.print(path.get(i) + " ");
		}
		pathString = setPathString(path, pathString);
	}
	
	public void setWeights(Integer inf, int s) {
		for (int i=0; i<weights.length; i++) {
			weights[i][0] = 0;
			weights[i][1] = inf;
			weights[i][2] = -1;
		}
		weights[s][1] = 0;
	}
	
	public int doDijkstra(int startNode, Integer[][] nodes, int[][] w) {
		int dv = 0, rep = 0;
		w[startNode][0] = 1; //"visited"
		//set adjacent nodes' source and distance from starting point
		for (int i=0; i<nodes[startNode].length; i++) {
			dv = w[startNode][1] + nodes[startNode][i];
			if (nodes[startNode][i] != 0
				&& dv < w[i][1]
				&& i != startNode) {
				w[i][1] = dv; //sets distance of current node
				w[i][2] = startNode; //sets the source of current node
			}
			//get largest possible distance from starting point
			if (dv >= rep) {
				rep = dv;
			}
		}
		//get smallest distance from all points ("unvisited" only)
		//choosing the next node to "visit"
		for (int j=0; j<w.length; j++) {
			if (w[j][0] == 0 //unvisited
				&& w[j][1] <= rep
				&& j!=startNode) {
				rep = w[j][1]; //set new smallest distance
				startNode = j;
			}
		}
		return startNode;
	}
	
	public void printWeights(int[][] weights) {
		for (int i=0; i<weights.length; i++) {
            for (int j=0; j<weights[0].length; j++) {
                System.out.print(weights[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
	}
	
	public static ArrayList<Integer> findPath(int endNode, int[][] weights) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		System.out.println("doing");
		path.add(endNode);
		while (weights[endNode][2] != -1) {
			for (int i=0; i<weights.length; i++) {
				if (weights[endNode][2] == i) {
					endNode = i;
					path.add(endNode);
				}
			}
		}
		Collections.reverse(path);
		return path;
	}
	
	public static String setPathString(ArrayList<Integer> path, String pathString) {
		for (int i=0; i<path.size(); i++) {
			pathString = pathString + path.get(i) + " ";
		}
		return pathString;
	}
	
	public static String getPathString() {
		return pathString;
	}
}