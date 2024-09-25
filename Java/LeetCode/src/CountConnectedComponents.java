import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CountConnectedComponents {
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
    public static int countConnectedComponents(int[][] edges, int n){
        List<List<Integer>> graph = createUndirectedGraph(edges, n);
        int count = 0;
        HashSet<Integer> visited = new HashSet<>();
        for(int i = 0; i < n; i++){
            if(!visited.contains(i)){                
                count = count + countHelper(graph, i, visited);
            }
        }        
        return count;
    }
    public static int countHelper(List<List<Integer>> graph, int node, HashSet<Integer> visited){
        int count = 0;
        if(visited.contains(node) || graph.get(node).isEmpty()){
            return 1;
        } 
        visited.add(node);
        for(int i: graph.get(node)){
            count = countHelper(graph, i, visited);
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] graph = {{0,1}, {3,5}, {4,5},{5,6},{5,7}};
        System.out.println(countConnectedComponents(graph, 8));
    }
}
