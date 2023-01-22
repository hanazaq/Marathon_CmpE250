import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
	public int node_num = 0;
	public int flag_num = 0;
	// store all graph's nodes. label: node
	public HashMap<String, Node> nodes;

	// used in the shortest path
	private Node min_flagged_unproccessed = null;
	private int min_costing_now = Integer.MAX_VALUE;
	private int pro_count = 0;
	private LinkedList<Node> pro_ones = new LinkedList<Node>(); // visited nodes suffered cost changes

	// constructor
	public Graph() {
		this.nodes = new HashMap<String, Node>();
	}

	public void addNode(Node newNode) {
		nodes.put(newNode.label, newNode); // add to the hashmap
	}

	// Dijkstra Algorithm
	public void computeShortestPath(Node sourceNode, Node targetNode) {
		// contains nodes to visit
		PriorityQueue<Node> heap = new PriorityQueue<>();
		// determine the source node
		sourceNode.setCost(0);
		heap.add(sourceNode);
		// as long as we still have unprocessed nodes
		while (this.pro_count < this.nodes.size()) {

			// shortest path not found. no more nodes to check
			if (heap.isEmpty()) {
				return;
			}
			// get access to the node with the min cost
			Node least_cost_node = heap.poll();

			// already processed
			if (least_cost_node.processed)
				continue;

			// if you reach here it is not processed yet
			least_cost_node.processed = true;
			this.pro_ones.addFirst(least_cost_node);
			if (least_cost_node.label.equals(targetNode.label)) {
				return;
			}
			this.pro_count++;
			Iterator<Edge> it = least_cost_node.adjacentNodes.iterator();
			// iterate over the adjacent nodes
			while (it.hasNext()) {
				Edge ed = it.next();
				Node adjacentNode = ed.target;
				Integer weight = ed.cost;
				if (!adjacentNode.processed) { // not visited earlier

					int cost_of_min_distance_node = least_cost_node.cost;
					// update the cost of the node with a less value
					if (cost_of_min_distance_node + weight < adjacentNode.cost) {
						adjacentNode.setCost(cost_of_min_distance_node + weight);
					}
					// will be checked in the upcoming iterations
					heap.add(adjacentNode);

				}
			}
		}
	}

	// Edited Dijkstra Algorithm: stops when find new flag
	public void computeShortestPathEdited(Node sourceNode) {
		// contains nodes to visit
		PriorityQueue<Node> heap = new PriorityQueue<>();
		// determine the source node
		sourceNode.setCost(0);
		heap.add(sourceNode);
		// as long as we still have unprocessed nodes
		while (this.pro_count < this.nodes.size()) {
			// shortest path not found. no more nodes to check
			if (heap.isEmpty()) {
				return;
			}
			// get access to the node with the min cost
			Node least_cost_node = heap.poll();
			// already processed
			if (least_cost_node.processed)
				continue;

			// if you reach here it is not processed yet
			least_cost_node.processed = true;
			this.pro_ones.addFirst(least_cost_node); // store them so we can clear the setup easier with
														// prepare_for_Dijkestra()

			// closest new flag on the graph
			if (least_cost_node.flaged && least_cost_node.done == false
					&& least_cost_node.cost < this.min_costing_now) {
				this.min_costing_now = least_cost_node.cost;
				this.min_flagged_unproccessed = least_cost_node;
				return;
			}
			this.pro_count++;
			Iterator<Edge> it = least_cost_node.adjacentNodes.iterator();
			// iterate over the adjacent nodes
			while (it.hasNext()) {
				Edge ed = it.next();
				Node adjacentNode = ed.target;
				Integer weight = ed.cost;
				if (!adjacentNode.processed) {

					int cost_of_min_distance_node = least_cost_node.cost;
					// update the cost of the node with a less value
					if (cost_of_min_distance_node + weight < adjacentNode.cost) {
						adjacentNode.setCost(cost_of_min_distance_node + weight);
					}
					// will be checked in the upcoming iterations
					heap.add(adjacentNode);

				}
			}
		}
	}

	private void prepare_for_Dijkestra() {
		// resent the setup
		this.min_flagged_unproccessed = null;
		this.min_costing_now = Integer.MAX_VALUE;
		this.pro_count = 0;
		while (!this.pro_ones.isEmpty()) {
			Node b = this.pro_ones.remove();
			b.cost = Integer.MAX_VALUE;
			b.processed = false;
		}
	}

	// idea: compute shortest path from each new flag we reach to the closest
	// unprocessed flag. link this flag to the
	// center flag with zero edges
	public int solveflags(String flags[]) {
		// final answer
		int answer = 0;
		// start

		int a = 0; // any random index as the starting point. does not matter thanks to zero edges
		Node random = this.nodes.get(flags[a]);
		this.prepare_for_Dijkestra();
		random.done = true; // helpful in the stopping condition in shortest path
		this.computeShortestPathEdited(random); // apply shortest path

		for (int f = 0; f < this.flag_num - 1; f++) {
			Node minn = this.min_flagged_unproccessed; // closest unprocessed flag to the prev processed flag
			// such a road does not exist
			if (minn == null) {
				answer = Integer.MAX_VALUE;
				break;
			}
			answer += minn.cost;
			minn.done = true;
			// add zero edges with the center node (random chosen earlier)
			// costless
			minn.add(random, 0);
			random.add(minn, 0);
			// preparation to call Dijkstra Algorithm
			this.prepare_for_Dijkestra();
			this.computeShortestPathEdited(minn);
		}

		return answer;
	}

}
