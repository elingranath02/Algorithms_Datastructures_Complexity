import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MaxFlow {

    Kattio io;


	private class FlowGraph{
        private List<Edge>[] grannListor;
        private int v;
		private int s;
        private int t;
        private int e;

		private FlowGraph(List<Edge>[] grannListor, int v, int s, int t, int e){
			this.grannListor = grannListor;
			this.v = v;
			this.s = s;
            this.t = t;
            this.e = e;

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

    


    FlowGraph readFlowGraph() {
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
        FlowGraph flowGraph = new FlowGraph(grannListor, v, s, t, e);
        return flowGraph;
    }

    void edmondKarp(List<Edge>[] c){    
        
    }

    List<Edge> BFS(FlowGraph flowGraph){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[flowGraph.v];
        visited[flowGraph.s] = true;
        queue.add(flowGraph.s);
        while (queue.isEmpty()==false){
            int node = queue.remove();
        } 
    }

    MaxFlow(){
        io = new Kattio(System.in, System.out);

        FlowGraph flowGraph = readFlowGraph();
        
    }

}
