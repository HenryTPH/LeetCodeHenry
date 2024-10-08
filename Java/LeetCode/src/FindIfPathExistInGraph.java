import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class FindIfPathExistInGraph {
    /**
     * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean validPath(int n, int[][] edges, int source, int destination){
        if(edges.length == 0) return true;
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(i, new HashSet<>());
        }
        for(int i = 0; i < edges.length; i++){
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(source);
        while(!stack.isEmpty()){
            int curr = stack.pop();
            visited.add(curr);
            for(int item: map.get(curr)){
                if(item == destination) return true;
                if(!visited.contains(item)){
                    visited.add(item);
                    stack.add(item);
                }
                if(item == destination) return true;
            }
        }
        return false;
    }
    public boolean validPathBFS(int n, int[][] edges, int source, int destination){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] e: edges){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[source] = true;
        queue.add(source);
        while(!queue.isEmpty()){
            int curr = queue.poll();
            if(curr == destination) return true;
            for(int link: graph.get(curr)){
                if(!visited[link]){
                    visited[link] = true;
                    queue.add(link);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        
    }
}
