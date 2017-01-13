import java.util.ArrayList;
public class Bfs {
	private Integer[][] nodes;
	private int startNode; //default is 0
	private int toq;
	private ArrayList<Integer> visited;
	private ArrayList<Integer> queue;
	private static String traversal;
	
	public Bfs() {
		startNode = 0;
		visited = new ArrayList<Integer>();
		queue = new ArrayList<Integer>();
		traversal = "Breadth-First Search traversal returned:\n";
		
		getNodes();
		doBFS(startNode);
	}
	
	public void getNodes() {
		nodes = Center.getWam();
	}
	
	public void doBFS(int startNode) {
		startNode = 0;
        queue.add(startNode);
        while (queue.size() != 0) {
            toq = queue.get(0);
            if (!visited.contains(toq)) {
                visited.add(toq);
            }
            //check if each adjacent node has already been visited
            for (int i=0; i<nodes[toq].length; i++) {
                if (!visited.contains(i) && nodes[toq][i] != 0) {
                    queue.add(i);
                }
            }
            //remove current node from the queue
            queue.remove(queue.get(0));
        }
		setTraversal(visited);
	}
	
	public void setTraversal(ArrayList<Integer> visited) {
		for (int i=0; i<visited.size(); i++) {
			traversal = traversal + " " + visited.get(i);
		}
	}
	
	public static String getTraversal() {
		return traversal;
	}
}