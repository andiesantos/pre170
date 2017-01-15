//COOL DOGS = FOR TESTING/DEBUGGING PURPOSES
import javax.swing.*;
import java.awt.BorderLayout;
//for file reading
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Center extends JPanel {
	private JPanel mainPanel;
	private JLabel tableLabel;
	private JTable table;
	private JScrollPane scrollpane;
	private BufferedReader reader; //For getting the number of nodes
	private BufferedReader reader2;//For getting the weights of the graph
	private String line; //Line read in graph.in
	private String[] arr;//Contains numbers from the line, split by ", "
	private int[] temp; //Integer version of arr
	private static Integer[][] wam; //Weighted adjacency matrix
	private Integer[] colNames;
	private Object[][] whammy; //Object versions of "wam" and "colNames" for use in JTable
	private Object[] columnNames;
	private int nodeNum = 0; //Number of nodes there are, according to graph.in
	
	public Center() {
		super();
		getWAM();
		add(setupElements());
	}
	
	public void getWAM() {
		//File reading from graph.in
		try {
			reader = new BufferedReader(new FileReader("graph.in"));
            System.out.println("Getting number of nodes...");
            while((line = reader.readLine()) != null) {
                arr = line.split(", ");
                if (arr.length == 1) {
                    nodeNum = Integer.parseInt(arr[0]);
                } else {
                    continue;
                }
            }
            reader.close();

            System.out.println("Number of nodes: " + nodeNum);
            wam = new Integer[nodeNum][nodeNum];
            //Initialize values to 0
            for (int i=0; i<nodeNum; i++) {
            	for(int j=0; j<nodeNum; j++) {
            		wam[i][j] = 0;
            	}
            }

            System.out.println("Getting graph weights...");
            reader2 = new BufferedReader(new FileReader("graph.in"));
            while((line = reader2.readLine()) != null) {
                arr = line.split(", ");
                if (arr.length == 1) {
                    continue;
                } else {
                    temp = new int[arr.length];
                    for (int i=0; i<arr.length; i++) {
                        temp[i] = Integer.parseInt(arr[i]);
                    }
                    //Assignment to weighted adjacency matrix
                    wam[temp[0]][temp[1]] = temp[2];
                    wam[temp[1]][temp[0]] = temp[2];
                }
            }
            reader2.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("No graph input found!");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public JPanel setupElements() {
		mainPanel = new JPanel(new BorderLayout());
		tableLabel = new JLabel("Weighted Adjacency Matrix");
		//Set column names
		colNames = new Integer[nodeNum];
		for (int i=0; i<nodeNum; i++) {
			colNames[i] = i;
		}
		columnNames = colNames;
		whammy = wam;
		
		//COOL DOGS - Prints the weighted adjacency matrix in the console
		System.out.println("DATA:");
		for (int i=0; i<whammy.length; i++) {
            for (int j=0; j<whammy[0].length; j++) {
                System.out.print(whammy[i][j] + " ");
            }
            System.out.println("");
        }
		System.out.println("COLUMN AND ROW NAMES");
		for (int i=0; i<columnNames.length; i++) {
			System.out.print(columnNames[i] + " ");
		}
		System.out.println("");
		
		//Set up JTable that contains the weighted adjacency matrix
		table = new JTable(whammy, columnNames);
		scrollpane = new JScrollPane(table);
		mainPanel.add(tableLabel, BorderLayout.PAGE_START);
		mainPanel.add(scrollpane, BorderLayout.CENTER);
		return mainPanel;
	}
	
	public int getValidNodes() { //Get number of nodes in the graph
		return nodeNum;
	}
	
	public static Integer[][] getWam(){ //Get weighted adjacency matrix
		return wam;
	}
	
}