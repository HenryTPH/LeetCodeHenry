import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;

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
    public double maxProbabilityDijkstraAlgorithm(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        ArrayList<ArrayList<Edge>> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
        }
        for(int e = 0; e < edges.length; e++){
            int src = edges[e][0];
            int dest = edges[e][1];
            double prob = succProb[e];
            list.get(src).add(new Edge(dest, prob));
            list.get(dest).add(new Edge(src, prob));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(n, Comparator.comparingDouble(e -> e.prob));
        double[] distances = new double[n];
        Arrays.fill(distances, 0);
        distances[start_node] = 1;
        pq.add(new Edge(start_node, 1.0));
        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int currentVertex = curr.node;
            for(Edge e: list.get(currentVertex)){
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
    /*
     * Bellman-Ford algorithm
     * 1. Initialize dist[] holds the max prob to reach node i from the start node. Set dist[start] = 1
     * 2. Perform up to n-1 iterations, where n is the number of nodes. Check each edge and update the probability of reaching the neighboring nodes.
     * 3. For each edge (u, v), if the probability of reaching v through u is greater than the current known probability to reach v, update dist[v].Similarly, update dist[u] if the probability of reaching u through v is greater
     * 4. After completing the iterations, dist[end] will contain the maximum probability of reaching the end node from the start node. If there's no path, it will remain 0.
     */
    public double maxProbabilityWithBellmanFord(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;
        for(int i = 0; i < n-1; i++){
            boolean updated = false;
            for(int j = 0; j < edges.length; j++){
                int u = edges[j][0];
                int v = edges[j][1];
                double prob = succProb[j];
                if(maxProb[u] * prob > maxProb[v]){
                    maxProb[v] = maxProb[u] * prob;
                    updated = true;
                }
                if(maxProb[v] * prob > maxProb[u]){
                    maxProb[u] = maxProb[v] * prob;
                    updated = true;
                }
            }
            if(!updated) break;
        }
        return maxProb[end_node];
    }
    public double maxProbabilityWithKijkstraAlgorithm(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i< succProb.length; i++){
            graph.get(edges[i][0]).add(new Edge(edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Edge(edges[i][0], succProb[i]));
        }
        double[] dist = new double[n];
        dist[start_node] = 1.0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        pq.add(new Edge(start_node, 1.0));
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            for(Edge neighbor: graph.get(e.node)){
                if(dist[neighbor.node] < dist[e.node] * neighbor.prob){
                    dist[neighbor.node] = dist[e.node] * neighbor.prob;
                    pq.add(new Edge(neighbor.node, dist[e.node]));
                }
            }
        }
        return dist[end_node];
    }
    public static void main(String[] args) {
        
    }
}
