import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
            Set<Integer> set = new HashSet<>();
            boolean[] visited = new boolean[n];
            dfs(result, graph, i, set, visited);          
        }
        for(List<Integer> list: result){
            Collections.sort(list);
        }
        return result;
    }
    private static void dfs(List<List<Integer>> result, List<List<Integer>> graph, int node, Set<Integer> set, boolean[] visited){
        if(visited[node]){
            return;
        }
        visited[node] = true;
        for(int rs: set){
            if(!result.get(node).contains(rs))result.get(node).add(rs);
        }
        set.add(node);
        for(int neighbor: graph.get(node)){
            dfs(result, graph, neighbor, set, visited);
        }
        set.remove(node);
    }
    /*
     * DFS but for efficient
     */
    public static List<List<Integer>> getAncestors(int n, int[][] edges){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++){
            result.add(new ArrayList<>());
            graph[i] = new ArrayList<>();
        }
        for(int[] e:edges){
            graph[e[0]].add(e[1]);
        }
        for(int i = 0; i < n; i++){
            dfsE(graph, i, i, new boolean[n], result);
        }
        return result;
    }

    private static void dfsE(List<Integer>[] graph, int u, int ancestor, boolean[] visited, List<List<Integer>> result){
        visited[u] = true;
        for(int dest: graph[u]){
            if(visited[dest]) continue;
            result.get(dest).add(ancestor);
            dfsE(graph, dest, ancestor, visited, result);
        }
    }

    /*
     * Using BFS
     */
    public List<List<Integer>> getAncestorsBFS(int n, int[][] edges){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++){
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            bfs(adj, i, ans, n);
        }
        return ans;
    }
    void bfs(List<List<Integer>> adj, int start, List<List<Integer>> ans, int n){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(start);
        visited[start] = true;
        List<Integer> temp = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            for(int i: adj.get(node)){
                if(!visited[i]){
                    visited[i] = true;
                    q.add(i);
                    temp.add(i);
                }
            }
        }
        Collections.sort(temp);
        ans.add(temp);
    }
    
    public static void main(String[] args) {
        int[][] edges = {{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        System.out.println(getAncestors(8, edges));
    }
}
