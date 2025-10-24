/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */
import java.util.ArrayList;
import java.util.List;

public class BipRed {
    Kattio io;


	private class FlowGraph{
		private int [][] edges;
		private int v;

		private FlowGraph(int[][] edges, int v){
			this.edges = edges;
			this.v = v;

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
		edges[i+e][0] = 2+x+y;
		edges[i+e][1] = 2+x+i;
	}
	return new FlowGraph(edges, x+y+2);
    }
    
    
    void writeFlowGraph(FlowGraph f) {
	int v = f.v, e = 0, s = 1, t = 2;
	
	// Skriv ut antal hörn och kanter samt källa och sänka
	io.println(v);
	io.println(s + " " + t);
	io.println(e);
	for (int i = 0; i < e; ++i) {
	    int a = 1, b = 2, c = 17;
	    // Kant från a till b med kapacitet c
	    io.println(a + " " + b + " " + c);
	}
	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
	io.flush();
	
	// Debugutskrift
	System.err.println("Skickade iväg flödesgrafen");
    }
    
    
    void readMaxFlowSolution() {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int totflow = io.getInt();
	int e = io.getInt();
	
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int f = io.getInt();
	}
    }
    
    
    void writeBipMatchSolution() {
	int x = 17, y = 4711, maxMatch = 0;
	
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);
	
	for (int i = 0; i < maxMatch; ++i) {
	    int a = 5, b = 2323;
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}
	
    }
    
    BipRed() {
	io = new Kattio(System.in, System.out);
	
	readBipartiteGraph();
	
	writeFlowGraph();
	
	readMaxFlowSolution();
	
	writeBipMatchSolution();

	// debugutskrift
	System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }
    
    public static void main(String args[]) {
	BipRed bipRed = new BipRed();
	FlowGraph flowGraph = bipRed.readBipartiteGraph();
	bipRed.writeFlowGraph(flowGraph);
    }
}

