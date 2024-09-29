import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ShortestPath {
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
    public static int shortestPath(int[][] edges, int Nodes, int start, int end){
        List<List<Integer>> graph = createUndirectedGraph(edges, Nodes);
        Set<Integer> visited = new HashSet<>(start);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start,0});
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == end) return current[1];
            for(int i: graph.get(current[0])){
                if(!visited.contains(i)){
                    visited.add(i);
                    queue.add(new int[]{i, current[1]+1});
                } 
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,0}};
        System.out.println(shortestPath(edges, 5, 0, 3));
    }
}
