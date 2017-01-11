import java.util.ArrayList;
public class Dfs {
	private Integer[][] nodes;
	private int startNode; //default is 0
	private int tos;
	private int check;
	private ArrayList<Integer> visited;
	private ArrayList<Integer> stack;
	private static String traversal;
	
	public Dfs() {
		startNode = 0;
		check = 0;
		visited = new ArrayList<Integer>();
		stack = new ArrayList<Integer>();
		traversal = "Depth-First Search traversal returned:\n";
		
		getNodes();
		doDFS(startNode);
	}
	
	public void getNodes() {
		nodes = Center.getWam();
	}
	
	public void doDFS(int startNode) {
		stack.add(startNode);
		while (stack.size() != 0) {
            //check if the last node in the stack is already visited
            tos = stack.get(stack.size() - 1);
            if (!visited.contains(tos)) {
                visited.add(tos);
            }
            //check if each adjacent node has already been visited
            for (int i=0; i<nodes[tos].length; i++) {
                if (!visited.contains(i) && nodes[tos][i] != 0) {
                    stack.add(i);
                    check = 0;
                    break;
                } else if (visited.contains(i) && nodes[tos][i] != 0){
                    check = 1;
                }
                
            }
            if (check == 1) {
                stack.remove(stack.size() - 1);
            }
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