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

    private class GrannLista{
        int start;
        Edge [] edges;
        List<Edge>[] lista;
    }


    private class Edge{
        int end;
        int capacity;
    }


    	private class Edge{
		private int start;
		private int end;
        private int capacity;


		private Edge(int start, int end, int capacity){
			this.start = start;
			this.end = end;
            this.capacity = capacity;
		}
	}
    


    void readFlowGraph() {
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int e = io.getInt();

    Edge[] edges = new Edge[e];
	int index = 0;
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int c = io.getInt();
        Edge edge = new Edge(a,b,c);
        edges[i] = edge;

	}

    FlowGraph flowGraph = new FlowGraph(edges, )
    }


    MaxFlow(){
        io = new Kattio(System.in, System.out);

    }

}
