import java.util.ArrayList;
import java.util.List;

public class MaxFlow {

    Kattio io;


	private class FlowGraph{
		private Edge[] edges;
		private int x;
		private int y;

		private FlowGraph(Edge[] edges, int x, int y){
			this.edges = edges;
			this.x = x;
			this.y = y;

		}
	}


    private class Edge{
        int end;
        int c;
        int f;
        int cf;
        Edge edge2;

        private Edge(int end, int c, int f, int cf){
            this.end = end;
            this.c = c;
            this.f = f;
            this.cf = cf;
        }

        private void addEdge(Edge edge){
            this.edge2 = edge;
        }
    }

    


    List<Edge>[] readFlowGraph() {
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int e = io.getInt();
    List<Edge>[] grannListor = (List<Edge>[]) new ArrayList[v];

    for (int i = 0; i < v; i++) {
    grannListor[i] = new ArrayList<>();
    }
    
	int index = 0;
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int c = io.getInt();
        Edge edge = new Edge(b,c, 0, c);
        grannListor[a].add(edge);
        Edge edge2 = new Edge(a,c,0,c);
        grannListor[b].add(edge2);
        grannListor[a].getLast().addEdge(edge2);
        grannListor[b].getLast().addEdge(edge);
	}
    return grannListor;
    }

    void edmondKarp(List<Edge>[] c){    
        
    }

    MaxFlow(){
        io = new Kattio(System.in, System.out);

        List<Edge>[] grannListor = readFlowGraph();
        
    }

}
