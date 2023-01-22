import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class project4 {

	public static void main(String[] args) {
//		long startTime = System.nanoTime();
		int req1 = 0, req2 = 0;
		Graph graph = new Graph();

		try {

			File myObj = new File(args[0]);
			Scanner myReader = new Scanner(myObj);

			int node_num = Integer.parseInt(myReader.nextLine());
			int flag_num = Integer.parseInt(myReader.nextLine());
			graph.node_num = node_num;
			graph.flag_num = flag_num;
			String[] terminal = myReader.nextLine().split(" ");
			String[] flags = myReader.nextLine().split(" ");
			String[][] lines = new String[node_num][];
			// egde info
			int i = 0;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] adj_info = data.split(" ");
				Node my_node = new Node(adj_info[0]);
				graph.addNode(my_node);
				lines[i] = adj_info;
				i++;
			}

			myReader.close();
			// determine flagged nodes
			for (int f = 0; f < flag_num; f++) {
				graph.nodes.get(flags[f]).flaged = true;
			}
			// create edges using stored data in lines
			for (int x = 0; x < node_num; x++) {
				Node curr_mst = graph.nodes.get(lines[x][0]);
				for (int y = 1; y < lines[x].length; y += 2) {
					Node neighbour_mst = graph.nodes.get(lines[x][y]);
					int neighbour_cost = Integer.parseInt(lines[x][y + 1]);
					curr_mst.add(neighbour_mst, neighbour_cost);
					neighbour_mst.add(curr_mst, neighbour_cost); // undirected graph
				}
			}

			try {
				FileWriter myWriter = new FileWriter(args[1]);
				Node a = graph.nodes.get(terminal[0]);
				Node b = graph.nodes.get(terminal[1]);

				graph.computeShortestPath(a, b);

				req1 = graph.nodes.get(terminal[1]).cost;
				if (req1 == Integer.MAX_VALUE) {
					myWriter.write(-1 + "\n");
				} else {
					myWriter.write(req1 + "\n");
				}
				req2 = graph.solveflags(flags);
				if (req2 == Integer.MAX_VALUE) {
					myWriter.write(-1 + "\n");
				} else {
					myWriter.write(req2 + "\n");
				}
				myWriter.close();
//				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
//		System.out.println("Time takes: " + (System.nanoTime() - startTime) / 1000000);
	}
	}

