import java.util.Arrays;

public class BellmanFord {
    static class Edge{
        int src, dest, weight;
        public Edge(){src = dest = weight = 0;}
        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    static void BellmanFord(Edge[] edges, int V, int src){
        // Step 1: Initialize distances from src to all other vertices as Infinite
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        /*
         * Step 2: Relax all edges 1 times/ A simple shortest path from src to any other vertex can have at-most 1 edges
         */
        for(int i = 1; i < V; ++i){
            for(int j = 0; j < edges.length; ++j){
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if(dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){
                    dist[v] = dist[u] + weight;
                }
            }
        }
        /*
         * Step 3: Check for negative-weight cycles. The above step quarantees shortest distances if graph doesn't contain negative weight cycle. If we get a shorter path, then there is a cycle.
         */
        for(int j = 0; j < edges.length; ++j){
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;
            if(dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
