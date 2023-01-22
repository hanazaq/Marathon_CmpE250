import java.util.ArrayList;

public class Node implements Comparable<Node> {
	public String label;
	public int cost;
	public boolean flaged = false;
	public boolean processed = false;
	public ArrayList<Edge> adjacentNodes;
//	public LinkedList<Edge> adjacentNodes;
	public boolean done = false;

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Node(String label) {
		this.label = label;
		this.cost = Integer.MAX_VALUE;
		this.adjacentNodes = new ArrayList<Edge>();
	}

	// add edge to the adjacentNodes
	public void add(Node neighborNode, int cost) {
		Edge edge = new Edge(this, neighborNode, cost);
		adjacentNodes.add(edge);
	}

	// to use in the priority queue
	@Override
	public int compareTo(Node otherNode) {
		return Integer.compare(this.cost, otherNode.cost);
	}
}
