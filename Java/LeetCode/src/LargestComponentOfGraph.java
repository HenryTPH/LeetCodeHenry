import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LargestComponentOfGraph {
    public static List<List<Integer>> createUndirectedGraph(int[][] edges, int numOfNodes){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numOfNodes; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
    public static int largestComponentOfGraph(int[][] edges, int nodes){
        List<List<Integer>> graph = createUndirectedGraph(edges, nodes);
        int rs = 0;
        HashSet<Integer> visited = new HashSet<>();
        for(int i = 0; i < nodes; i++){
            if(!visited.contains(i)){
                rs = Math.max(rs, nodeCountHelper(graph, visited, i));
            }
        }
        return rs;
    }
    public static int nodeCountHelper(List<List<Integer>> graph, HashSet<Integer> visited, int node){
        int count = 0;
        if(visited.contains(node)) return count;
        visited.add(node);
        count++;
        for(int neighbor: graph.get(node)){
            count += nodeCountHelper(graph, visited, neighbor);
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,5},{0,6},{2,4},{2,3},{3,4},{0,7}};
        System.out.println(largestComponentOfGraph(edges, 9));
    }
}
