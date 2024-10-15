import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PathWithMaximumProbability {
    /*
     * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
     */
    public double maxProbabilityFloydWarshall(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        double[][] distances = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], Float.MIN_VALUE);
        }       
        for(int i = 0; i < edges.length; i++){
            int src = edges[i][0];
            int dest = edges[i][1];
            distances[src][dest] = succProb[i];
            distances[dest][src] = succProb[i];
        }
        for(int k = 0; k < n; k++){
            if(Double.compare(distances[start_node][k] * distances[k][end_node], distances[start_node][end_node]) > 0){
                distances[start_node][end_node] = distances[start_node][k] * distances[k][end_node];
            }
                
        }
        return distances[start_node][end_node];
    }
    static class Edge {
        int node;
        double prob;
        public Edge(int node, double prob){
            this.node = node;
            this.prob = prob;
        }        
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        LinkedList<Edge>[] list = new LinkedList[n];
        for(int i = 0; i < n; i++){
            list[i] = new LinkedList<>();
        }
        for(int e = 0; e < edges.length; e++){
            int src = edges[e][0];
            int dest = edges[e][1];
            double prob = succProb[e];
            list[src].add(new Edge(dest, prob));
            list[dest].add(new Edge(src, prob));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(n, Comparator.comparingDouble(e -> e.prob));
        // boolean[] visited = new boolean[n];
        double[] distances = new double[n];
        Arrays.fill(distances, 0);
        distances[start_node] = 1;
        pq.add(new Edge(start_node, 0));
        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int currentVertex = curr.node;
            // if(visited[currentVertex]) continue;
            // visited[currentVertex] = true;
            for(Edge e: list[currentVertex]){
                int neighbor = e.node;
                double newProb = e.prob * distances[currentVertex];
                if(Double.compare(newProb,distances[neighbor]) > 0){
                    distances[neighbor] = newProb;
                    pq.add(new Edge(neighbor, newProb));
                }
            }
        }
        return distances[end_node];
    }
    public static void main(String[] args) {
        
    }
}
