public class Dijkstra {
	private Integer[][] nodes;
	private int[][] weights;
	private int startingNode;
	private Integer inf;
	
	public Dijkstra() {
		startingNode = 0; //by default
		inf = Integer.MAX_VALUE;
		
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
}