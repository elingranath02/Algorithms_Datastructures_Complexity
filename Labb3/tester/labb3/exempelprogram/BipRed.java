/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
    Kattio io;


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
		private Edge[] edges;

		private Matching(int x, int y, Edge[] edges){
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
    
    
    void writeFlowGraph(FlowGraph f) {
	int v = f.x + f.y + 2;
	int e = f.edges.length;
	int [][] edges = f.edges;
	int s = 1, t = v;
	int c = 1;
	// Skriv ut antal hörn och kanter samt källa och sänka
	io.println(v);
	io.println(s + " " + t);
	io.println(e);
	for (int i = 0; i < e; ++i) {
	    int a = edges[i][0], b = edges[i][1];

	    // Kant från a till b med kapacitet c
	    io.println(a + " " + b + " " + c);
	}
	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
	io.flush();
	
	// Debugutskrift
	System.err.println("Skickade iväg flödesgrafen");

    }
    
    
    Matching readMaxFlowSolution() {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int totflow = io.getInt();
	int e = io.getInt();
	Edge[] edges = new Edge[totflow];
	int index = 0;
	int x = 0;
	int y = 0;
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int f = io.getInt();
		if (f==1 && a != s && a != t && b != s && b != t){
			Edge edge = new Edge(a-1,b-1);
			edges[index] = edge;
			index++;
		} 

	}
	Matching m = new Matching(0,0, edges);
	return m;
    }
    
    
    void writeBipMatchSolution(Matching m) {
	int x = m.x, y = m.y, maxMatch = m.edges.length;
	
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);
	
	for (int i = 0; i < maxMatch; ++i) {
	    int a = m.edges[i].start, b = m.edges[i].end;
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}
	
    }
    
    BipRed() {
	io = new Kattio(System.in, System.out);
	
	FlowGraph flowGraph = readBipartiteGraph();
	
	writeFlowGraph(flowGraph);
	
	Matching matching = readMaxFlowSolution();
	matching.x = flowGraph.x;
	matching.y = flowGraph.y;
	writeBipMatchSolution(matching);

	// debugutskrift
	System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }
    
    public static void main(String args[]) {
	new BipRed();
    }
}
