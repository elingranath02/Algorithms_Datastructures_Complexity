import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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
        int start;
        int end;
        int c;
        int f;
        int cf;
        Edge edge2;

        private Edge(int start, int end, int c, int f, int cf){
            this.start = start;
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
            Edge edge = new Edge(a,b,c, 0, c);
            boolean reverse = false;
            for (int j = 0; j < grannListor[b].size(); j++) {
                if (grannListor[a].get(j).end == a){
                    grannListor[a].set(j, edge);
                    reverse = true;
                    break;
                }
            }
            if (!reverse){
                grannListor[a].add(edge);
                Edge edge2 = new Edge(b,a,c,0,0);
                grannListor[b].add(edge2);
                grannListor[a].getLast().addEdge(edge2);
                grannListor[b].getLast().addEdge(edge);
            }
    	}
        FlowGraph flowGraph = new FlowGraph(grannListor, v, s, t, e);
        return flowGraph;
    }

    int edmondKarp(FlowGraph graph){
		List<Edge> edges;
		int totFlow = 0;
		while((edges = bfs(graph)) != null){
            int min = Integer.MAX_VALUE;

            for(Edge edge:edges){
                if(edge.cf < min){
                    min = edge.cf;
                }
            }
			totFlow += min;
            for(Edge edge: edges){
                edge.f += min;
                edge.edge2.f -= min;
                edge.cf = edge.c - edge.f;
                edge.edge2.cf = edge.edge2.c - edge.edge2.f;
            }

        }
		return totFlow;
    }

    List<Edge> bfs(FlowGraph flowGraph){
        Queue<Integer> queue = new ArrayDeque<>();
        Edge[] parent = new Edge[flowGraph.v+1];
        boolean[] visited = new boolean[flowGraph.v+1];
        queue.add(flowGraph.s);
        visited[flowGraph.s] = true;
        while (queue.isEmpty()==false){
            int node = queue.remove();
            if (((int)node) == flowGraph.t){
                break;
            }
            for (Edge edge : flowGraph.grannListor[node]) {
                if(!visited[edge.end] && edge.cf > 0){
                    parent[edge.end] = edge;
                    visited[edge.end] = true;
                    queue.add(edge.end);
                }
            }
        }
        if (!visited[flowGraph.t]){
            return null;
        }
        List<Edge> path = new ArrayList<>();
        Edge edge = parent[flowGraph.t];
        while (edge!=null){
            path.add(edge);
            edge = parent[edge.start];
        }
        Collections.reverse(path);
        return path;
    }

	void writeMaxFlowSolution(FlowGraph graph, int totFlow) {

	List<Edge> posEdges = new ArrayList<>();

	for(int i = 0; i < graph.grannListor.length; i++){
		List<Edge> grannLista = graph.grannListor[i];
		for(int j = 0; j < grannLista.size(); j++){
			Edge e = grannLista.get(j);
			if(e.f > 0 && i < e.end){
				posEdges.add(e);
			}

		}

	}

	io.println(graph.v);
	io.println(graph.s + " " + graph.t + " " + totFlow);
	io.println(posEdges.size());

	for (int i = 0; i < posEdges.size(); ++i) {
	    int a = posEdges.get(i).edge2.end, b = posEdges.get(i).end;
	    io.println(a + " " + b + " " + posEdges.get(i).f);
	}
	
    }

    MaxFlow(){
        io = new Kattio(System.in, System.out);

        FlowGraph flowGraph = readFlowGraph();

        int totFlow = edmondKarp(flowGraph);
        
        writeMaxFlowSolution(flowGraph, totFlow);
        
        
    }

    public static void main(String[] args) {
        new MaxFlow();
    }
}
