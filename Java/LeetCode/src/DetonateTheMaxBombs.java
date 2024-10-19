import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetonateTheMaxBombs {
    static class Edge{
        int dest;
        double length;
        public Edge(int dest, double length){
            this.dest = dest;
            this.length = length;
        }
    }
    public static int maximumDetonation(int[][] bombs){
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0 ; i < bombs.length; i++){
            graph.add(new ArrayList<>());
            int range = bombs[i][2];
            for(int j = 0; j < bombs.length; j++){
                if(i != j){
                    double length = Math.sqrt(Math.pow(Math.abs(bombs[i][0] - bombs[j][0]), 2) + Math.pow(Math.abs(bombs[i][1] - bombs[j][1]), 2));
                    if(Double.compare(range, length) >= 0){
                        graph.get(i).add(new Edge(j, length));
                    }
                }
            }
        }
        int[] bombArr = new int[bombs.length];
        for(int i = 0; i < bombs.length; i++){
            bombArr[i] = DFSHelper(graph, i, new boolean[bombs.length]);
        }
        
        return Arrays.stream(bombArr).max().getAsInt();
    }
    public static int DFSHelper(List<List<Edge>> graph, int node, boolean[] visited){
        if(visited[node]) return 0;
        int count = 1;
        visited[node] = true;
        for(Edge e: graph.get(node)){
            int dest = e.dest;
            if(!visited[dest]) {                
                count += DFSHelper(graph, dest, visited);
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] bombs = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        int[][] bombs1 = {{2,1,3},{6,1,4}};
        System.out.println(maximumDetonation(bombs));
    }
}
