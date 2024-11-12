import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AllAncestorsOfANodeInDAG {
    /*
     * You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes are numbered from 0 to n - 1 (inclusive).

You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a unidirectional edge from fromi to toi in the graph.

Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.

A node u is an ancestor of another node v if u can reach v via a set of edges.
     */
    public static List<List<Integer>> getAncestors(int n, int[][] edges){
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            result.add(new ArrayList<>());
            graph.add(new ArrayList<>());
        }        
        for(int i = 0; i < edges.length; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        for(int i = 0 ; i < n; i++){
            List<Integer> list = new ArrayList<>();
            boolean[] visited = new boolean[n];
            dfs(result, graph, i, list, visited);          
        }
        for(List<Integer> list: result){
            Collections.sort(list);
        }
        return result;
    }
    private static void dfs(List<List<Integer>> result, List<List<Integer>> graph, int node, List<Integer> list, boolean[] visited){
        if(visited[node] || graph.get(node).isEmpty()){
            return;
        }
        visited[node] = true;
        if(!list.contains(node) && !graph.get(node).isEmpty()) list.add(node);
        for(int neighbor: graph.get(node)){
            for(int n: list){
                if(!result.get(neighbor).contains(n)){
                    result.get(neighbor).add(n);
                }
            } 
            dfs(result, graph, neighbor, new ArrayList<>(list), visited);
        }
    }
    public static void main(String[] args) {
        int[][] edges = {{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        System.out.println(getAncestors(8, edges));
    }
}
