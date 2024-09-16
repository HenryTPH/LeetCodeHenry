import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReOrderRoutesToMakeAllPathsLeadToTheCiTyZero {
    /**
     * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.
     * @param n
     * @param connections
     * @return
     */
    public static int minReorder(int n, int[][] connections){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int conn[]: connections){
            int src = conn[0];
            int dest = conn[1];
            adj.get(src).add(dest);
            adj.get(dest).add(-src);
        }
        return dfs(adj, new boolean[n], 0);
    }
    private static int dfs(List<List<Integer>> adj, boolean[] visited, int src){
        int count = 0;
        visited[src] = true;
        for(int node: adj.get(src)){
            if(!visited[Math.abs(node)]){
                count += node > 0 ? 1 : 0;
                count += dfs(adj, visited, Math.abs(node));
            }
        }
        return count;
    }
    public static int minReorderBFS(int n, int[][] connections){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < connections.length; i++){
            int src = connections[i][0];
            int dest = connections[i][1];
            adj.get(src).add(dest);
            adj.get(dest).add(-src);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int count = 0;
        while(!queue.isEmpty()){
            int curr = queue.poll();
            visited[Math.abs(curr)] = true;
            for(int src: adj.get(Math.abs(curr))){
                if(!visited[Math.abs(src)]){
                    queue.add(src);
                    visited[Math.abs(src)] = true;
                    if(src > 0) count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] connections = {{0,1}, {1,3}, {2,3}, {4,0}, {4,5}};
        System.out.println(minReorder(6, connections));
    }
}
