import java.util.Arrays;

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
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node){
        
    }
    public static void main(String[] args) {
        
    }
}
