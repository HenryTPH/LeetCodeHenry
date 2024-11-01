import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathWithAlternatingCorlors {
    /*
     * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges){
        int[] result = new int[n];
        Arrays.fill(result, -1);
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] red: redEdges){
            graph.get(red[0]).add(new Edge(red[1], 1));
        }
        for(int[] blue: blueEdges){
            graph.get(blue[0]).add(new Edge(blue[1], -1));
        }
        Queue<Edge> queue = new LinkedList<>();
        queue.add(new Edge(0, 0));
        int step = 0;
        while(!queue.isEmpty()){
            ++step;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int currentNode = queue.peek().destination;
                int currentColor = queue.poll().color;
                result[currentNode] = result[currentNode] == -1 ? step : result[currentNode];
                for(int j = 0; j < graph.get(currentNode).size(); j++){
                    Edge e = graph.get(currentNode).get(j);
                    int dest = e.destination;
                    int destColor = e.color;
                    if(dest == -1 || currentColor == destColor) continue;
                    queue.add(e);
                    graph.get(currentNode).set(j, new Edge(-1, destColor));
                }
            }
        }
        return result;
    }
    static class Edge{
        int destination;
        int color; // 0 - none, -1 - blue; 1 - red
        public Edge(int destination, int color){
            this.destination = destination;
            this.color = color;
        }
    }
    public static void main(String[] args) {
        
    }
}
