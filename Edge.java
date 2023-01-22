public class Edge implements Comparable<Edge> {
	public Node source;
	public Node target;
	public int cost;

	public Edge(Node source, Node target, int cost) {
		this.source = source;
		this.target = target;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge otherEdge) {
		return Integer.compare(this.cost, otherEdge.cost);
	}
}
