import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinCostConnectPoints {
    static class Edge{
        int src;
        int dest;
        int weight;
        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    private static int findRoot(int[] parent, int i){
        if(parent[i] != i){
            parent[i] = findRoot(parent, parent[i]);
        }
        return parent[i];
    }
    private static void union(int[] parent, int[] rank, int i, int j){
        int rooti = findRoot(parent, i);
        int rootj = findRoot(parent, j);
        if(rooti == rootj) return;
        if(rank[rooti] < rank[rootj]){
            parent[rooti] = rootj;
        } else if(rank[rooti] >  rank[rootj]){
            parent[rooti] = rootj;
        } else {
            parent[rootj] = rooti;
            rank[rooti]++;
        }
    }
    public static int minCostConnectPoints(int[][] points){
        int nodes = points.length;
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < points.length-1; i++){
            for (int j = i+1; j < points.length; j++){
                int w = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, w));
            }
        }
        Collections.sort(edges, (a, b) -> Integer.compare(a.weight, b.weight));
        int[] parent = new int[nodes];
        int[] rank = new int[nodes];
        for(int i = 0; i < nodes; i++){
            parent[i] = i;
            rank[i] = 0;
        }
        int noOfEdges = 0;
        int current = 0;
        int result = 0;
        while(noOfEdges < nodes - 1){
            Edge nextEdge = edges.get(current);
            int left = findRoot(parent, nextEdge.src);
            int right = findRoot(parent, nextEdge.dest);
            if(left != right){
                result += nextEdge.weight;
                union(parent, rank, left, right);
                noOfEdges++;
            }
            current++;
        }
        return result;
    }
    /*
     * Faster way on Leetcode
     */
    private static int distance(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
    public int minCostConnectPoints1(int[][] points){
        int res = 0;
        int nodes = points.length;
        int[] distance = new int[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[nodes];
        int current = 0;
        distance[current] = 0;
        for(int i = 0; i < nodes; i++){
            int min = Integer.MAX_VALUE;
            int bestPoint = -1;
            for(int j = 0; j < nodes; j++){
                if(!visited[j] && distance[j] < min){
                    min = distance[j];
                    bestPoint = j;
                }
            }
            visited[bestPoint] = true;
            res += min;
            for(int k = 0; k < nodes; k++){
                if(!visited[k]){
                    int dist = distance(points[bestPoint], points[k]);
                    distance[k] = Math.min(distance[k], dist);
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int[][] points1 = {{3,12},{-2,5},{-4,1}};
        System.out.println(minCostConnectPoints(points));
    }
}
