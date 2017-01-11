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
	private BufferedReader reader; //for getting the number of nodes
	private BufferedReader reader2;//for getting the weights of the graph
	private String line; //line in graph.in
	private String[] arr;//contains numbers from the line, split by ", "
	private int[] temp; //Integer version of arr
	private Integer[][] wam;//weighted adjacency matrix
	private Object[][] whammy;
	private Integer[] colNames; //same as row names
	private Object[] columnNames;
	private int nodeNum = 0; //number of nodes there are, according to graph.in
	
	public Center() {
		super();
		getWAM();
		add(setupElements());
	}
	
	public void getWAM() {
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
            //initialize values to 0
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
                    //assignment to weighted adjacency matrix
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
		//set column and row names
		colNames = new Integer[nodeNum];
		for (int i=0; i<nodeNum; i++) {
			colNames[i] = i;
		}
		columnNames = colNames;
		whammy = wam;
		
		//COOL DOGS
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
		
		//set up JTable
		table = new JTable(whammy, columnNames);
		scrollpane = new JScrollPane(table);
		mainPanel.add(tableLabel, BorderLayout.PAGE_START);
		mainPanel.add(scrollpane, BorderLayout.CENTER);
		return mainPanel;
	}
	
	public int getValidNodes() {
		return nodeNum;
	}
	
}