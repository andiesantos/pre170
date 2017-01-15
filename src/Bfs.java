import java.util.ArrayList;

public class Bfs {
	private Integer[][] nodes; //Weighted adjacency matrix
	private int startNode; //Default is 0
	private int toq; //"Top of Queue"/start of queue
	private ArrayList<Integer> visited; //List of visited nodes
	private ArrayList<Integer> queue; //Stack data type for nodes that are not visited yet
	private static String traversal; //Stores the traversal of the visited nodes
	
	public Bfs() {
		startNode = 0;
		visited = new ArrayList<Integer>();
		queue = new ArrayList<Integer>();
		traversal = "Breadth-First Search traversal returned:\n";
		
		getNodes();
		doBFS(startNode);
	}
	
	public void getNodes() { //Gets the weighted adjacency matrix from Center class
		nodes = Center.getWam();
	}
	
	public void doBFS(int startNode) { //Simulates Breadth-First Search
		startNode = 0;
        queue.add(startNode);
        while (queue.size() != 0) {
            toq = queue.get(0);
            if (!visited.contains(toq)) {
                visited.add(toq);
            }
            //Check if each adjacent node has already been visited
            for (int i=0; i<nodes[toq].length; i++) {
                if (!visited.contains(i) && nodes[toq][i] != 0) {
                    queue.add(i);
                }
            }
            //Remove current node from the queue
            queue.remove(queue.get(0));
        }
		setTraversal(visited);
	}
	
	public void setTraversal(ArrayList<Integer> visited) { //Sets the outcome of the DFS into a String
		for (int i=0; i<visited.size(); i++) {
			traversal = traversal + " " + visited.get(i);
		}
	}
	
	public static String getTraversal() { //Gets the "traversal" String
		return traversal;
	}
}