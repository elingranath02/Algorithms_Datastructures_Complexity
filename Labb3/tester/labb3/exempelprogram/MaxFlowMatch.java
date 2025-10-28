import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class MaxFlowMatch {


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

    public static class FlowGraphMatch{
        public List<Edge> edges;
        public int v;
		public int s;
        public int t;
        public int e;
        public int totFlow;

        public FlowGraphMatch(List<Edge> edges, int v, int s, int t, int e, int totflow){
            this.edges = edges;
            this.v = v;
            this.s = s;
            this.t = t;
            this.e = e;
            this.totFlow = totflow;

        }
    }


    public static class Edge{
        public int start;
        public int end;
        public int c;
        public int f;
        public int cf;
        public Edge edge2;

        public Edge(int start, int end, int c, int f, int cf){
            this.start = start;
            this.end = end;
            this.c = c;
            this.f = f;
            this.cf = cf;
        }

        public void addEdge(Edge edge){
            this.edge2 = edge;
        }
    }

    FlowGraph readFlowGraph(int v, int s, int t, int e, int [][] edges) {
        int c = 1;
        List<Edge>[] grannListor = (List<Edge>[]) new ArrayList[v+1];
        for (int i = 0; i < v+1; i++) {
        grannListor[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; ++i) {
	        int a = edges[i][0], b = edges[i][1];
            Edge edge = new Edge(a,b,c, 0, c);
			Edge reverse = new Edge(b,a,0,0,0);
			edge.addEdge(reverse);
			reverse.addEdge(edge);

			grannListor[a].add(edge);
			grannListor[b].add(reverse);
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

	FlowGraphMatch writeMaxFlowSolution(FlowGraph graph, int totFlow) {

	List<Edge> posEdges = new ArrayList<>();

	for(int i = 0; i < graph.grannListor.length; i++){
		List<Edge> grannLista = graph.grannListor[i];
		for(int j = 0; j < grannLista.size(); j++){
			Edge e = grannLista.get(j);
			if(e.f > 0){
				posEdges.add(e);
			}

		}

	}

    FlowGraphMatch f = new FlowGraphMatch(posEdges, graph.v, graph.s, graph.t, posEdges.size(), totFlow);

    return f;
	
    }

    FlowGraphMatch maxFlowMatch(int v, int s, int t, int e, int [][] edges){

        FlowGraph flowGraph = readFlowGraph(v,s,t,e, edges);

        int totFlow = edmondKarp(flowGraph);
        
        return writeMaxFlowSolution(flowGraph, totFlow);
        
    }

    
}
