import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;


/**
 * @author Gayatri
 * Pseudocode for kruskal() taken from Slide 102 of chap 9
 *
 * This class is used to find the minimum spanning tree of a graph available in the form a adjacency list in a csv file.
 * It prints every edge of the spanning tree and the distance between two edges
 */
public class Kruskals {
	
	/**
	 * FInds the mininum spanning tree using Kruskal's algorithm
	 * @return list of Edge which are present in the spanning tree
	 */
	public ArrayList<Edge> kruskal()
	{
	    int edgesAccepted = 0;
	    DisjSets ds = new DisjSets(NUM_VERTICES);
	    PriorityQueue<Edge> pq = new PriorityQueue<Edge>( getEdges() );
	    Edge e; 
	    String u, v;

	    while (edgesAccepted < NUM_VERTICES - 1)
	    {
	        e = pq.poll();  // get minimum edge = (u,v)
	        u = e.vertexOne;
	        v = e.vertexTwo;
	        if (e != null){
	        	int uset = ds.find(vertexMap.get(u)); // find set vertex u is in.
		        int vset = ds.find(vertexMap.get(v)); // find set vertex v is in.
		        if (uset != vset)    // if not same set (not yet connected)
		        {
		            // accept the edge
		        	spanningTree.add(e);
		            edgesAccepted++;
		            ds.union(uset, vset); // connect them
		         }   
	        } 
	   }
	    return spanningTree;
	} 
	
	/**
	 * This method prints the cities names which are part of the minimum spanning tree and the distance between these cities
	 * @param tree - the minimum spanning tree
	 * @return the sum of total distances in the minimum spanning tree
	 */
	public int printTree(ArrayList<Edge> tree) {
		int totalDistance = 0;
		for(Edge e : tree) {
			System.out.println(e.vertexOne + "\t\t" + e.vertexTwo + "\t\t" + e.weight);
			totalDistance += e.weight;
		}
		return totalDistance;
	}
	
	/**
	 * This method is used to set the edges of the input tree without repeating any edge. 
	 * It also inserts all vertices in a Map with key as city name and value as 0,1,2,etc in the natural order. This Map will be useful
	 * when DisjSets is used to find whether adding an edge results in a cycle or not
	 * 
	 * @param edgeDetail - Every single line in the form of String as it is read from the CSV file
	 */
	public void setEdges(String edgeDetail) {
		String[] graph = edgeDetail.split(",");
		if (graph.length != 0) {
			int i = 1;
			if (!vertexMap.containsKey(graph[0]))
				vertexMap.put(graph[0], NUM_VERTICES++);
			while (i < graph.length) {	//TODO: edges are repeating twice. A to B and B to A
				String vetexTwo = graph[i++];
				Edge e = new Edge(graph[0], vetexTwo, Integer.parseInt(graph[i++]));
				if (!edges.contains(e)) {
					if (!vertexMap.containsKey(vetexTwo))
						vertexMap.put(vetexTwo, NUM_VERTICES++);
					edges.add(e);
				}
			}
		}
	}

	/**
	 * 
	 * @return All edges of the input tree
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	private int NUM_VERTICES = 0;
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Map<String, Integer> vertexMap = new TreeMap<String, Integer>();
	ArrayList<Edge> spanningTree = new ArrayList<Edge>();
	
	
	class Edge implements Comparable<Edge> {
		/**
		 * Constructor to build an Edge object
		 * @param vertexU one of the vertices of the edge
		 * @param vertexV second vertex of the edge
		 * @param edgeWeight distance between the two vertices or the edge weight
		 */
		Edge(String vertexU, String vertexV, int edgeWeight) {
			weight = edgeWeight;
			vertexOne = vertexU;
			vertexTwo = vertexV;
		}
		
		/**
		 * Method to compare two Edge objects
		 */
		@Override
		public int compareTo(Edge edge) {
			return ((Integer)this.weight).compareTo(edge.weight);
		}
		
		/**
		 * Method to check if two Edge objects are the same or not
		 */
		@Override
		public boolean equals(Object e) {
			if (e != null) {
				Edge edgeCompare = (Edge) e;
				if ((this.vertexOne.equalsIgnoreCase(edgeCompare.vertexOne) && this.vertexTwo.equalsIgnoreCase(edgeCompare.vertexTwo)) ||
						(this.vertexOne.equalsIgnoreCase(edgeCompare.vertexTwo) && this.vertexTwo.equalsIgnoreCase(edgeCompare.vertexOne)))
				{
					return true;
				}
				else
					return false;
			}
			else
				return true;
		}
		
		private int weight;
		private String vertexOne;
		private String vertexTwo;
	}
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			Kruskals algo = new Kruskals();
			reader = new BufferedReader(new FileReader("src\\assn9_data.csv"));
			String line = reader.readLine();
			while (line != null) {
				algo.setEdges(line);
				
				line = reader.readLine(); 
			}
			
			ArrayList<Edge> spanningTreeEdges = algo.kruskal();
			
			int distancesSum = algo.printTree(spanningTreeEdges);
			System.out.println("Sum of all distances in the tree: " + distancesSum);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try { 
				if (reader != null)
					reader.close();
				}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
