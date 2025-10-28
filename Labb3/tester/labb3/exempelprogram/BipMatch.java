import java.util.ArrayList;
import java.util.List;

public class BipMatch {

    Kattio io;
    MaxFlowMatch flowMatch;


	private class FlowGraph{
		private int [][] edges;
		private int x;
		private int y;

		private FlowGraph(int[][] edges, int x, int y){
			this.edges = edges;
			this.x = x;
			this.y = y;

		}
	}

	private class Matching{
		private int x;
		private int y;
		List<Edge> edges;

		private Matching(int x, int y, List<Edge> edges){
			this.x = x;
			this.y = y;
			this.edges = edges;
		}
	}

	private class Edge{
		private int start;
		private int end;


		private Edge(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
    
    FlowGraph readBipartiteGraph() {
	// Läs antal hörn och kanter
	int x = io.getInt();
	int y = io.getInt();
	int e = io.getInt();
	
	int[][] edges = new int[e+x+y][2];
	// Läs in kanterna
	for (int i = 0; i < e; ++i) {
	    int a = io.getInt();
	    int b = io.getInt();
		edges[i][0] = a+1;
		edges[i][1] = b+1;
	}
	for (int i = 0; i < x; ++i){
		edges[i+e][0] = 1;
		edges[i+e][1] = 2+i;
	}

	for (int i = 0; i < y; ++i){
		edges[i+e+x][0] = 2+x+i;
		edges[i+e+x][1] = 2+x+y;
	}
	return new FlowGraph(edges, x, y);
    }
    
    
    MaxFlowMatch.FlowGraphMatch writeFlowGraph(FlowGraph f) {
	int v = f.x + f.y + 2;
	int e = f.edges.length;
	int [][] edges = f.edges;
	int s = 1, t = v;

    System.err.println("Skickade iväg flödesgrafen");

    return flowMatch.maxFlowMatch(v, s, t, e, edges);
    
    }
    
    
    Matching readMaxFlowSolution(MaxFlowMatch.FlowGraphMatch maxFlowGraph) {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int s = maxFlowGraph.s;
	int t = maxFlowGraph.t;
	int e = maxFlowGraph.e;
    List<MaxFlowMatch.Edge> edgesList = maxFlowGraph.edges;
    List<Edge> edges = new ArrayList<>();

	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = edgesList.get(i).start;
	    int b = edgesList.get(i).end;
	    int f = edgesList.get(i).f;
		if (f==1 && a != s && a != t && b != s && b != t && a < b){
            edges.add(new Edge(a - 1, b - 1));
		} 

	}
	Matching m = new Matching(0,0, edges);
	return m;
    }
    
    
    void writeBipMatchSolution(Matching m) {
	int x = m.x, y = m.y, maxMatch = m.edges.size();
	
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);
	
	for (int i = 0; i < maxMatch; ++i) {
	    int a = m.edges.get(i).start, b = m.edges.get(i).end;
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}
	
    }
    
    BipMatch() {
	io = new Kattio(System.in, System.out);
    flowMatch = new MaxFlowMatch();
	
	FlowGraph flowGraph = readBipartiteGraph();
	
	MaxFlowMatch.FlowGraphMatch result = writeFlowGraph(flowGraph);
	
	Matching matching = readMaxFlowSolution(result);
	matching.x = flowGraph.x;
	matching.y = flowGraph.y;
	writeBipMatchSolution(matching);

	// debugutskrift
	System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }
    
    public static void main(String args[]) {
	new BipMatch();
    }
    
}
