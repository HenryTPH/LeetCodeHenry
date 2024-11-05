import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MaxNetworkRank {
    public static int maximalNetworkRank(int n, int[][] roads){
        int[] degree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        Arrays.fill(degree, 0);
        for(int[] r: roads){
            degree[r[0]] ++;
            degree[r[1]] ++;
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        int[][] pair = new int[2][2];
        pair[0][0] = -1;
        pair[1][0] = -1;
        pair[0][1] = Integer.MIN_VALUE;
        pair[1][1] = Integer.MIN_VALUE;
        int rs = Integer.MIN_VALUE;
        for(int i = 0; i < n ; i++){
            if(degree[i] > pair[0][1]){
                pair[1][1] = pair[0][1];
                pair[0][1] = degree[i];
                pair[1][0] = pair[0][0];
                pair[0][0] = i;
            }
            if(degree[i] == pair[0][1] && pair[0][0] != -1 && i != pair[0][0]){
                pair[1][1] = degree[i];
                pair[1][0] = i;
            }
            if(degree[i] >= pair[1][1] && degree[i] < pair[0][1]){
                pair[1][1] = degree[i];
                pair[1][0] = i;
            }
            boolean checked = graph.get(pair[0][0]).contains(pair[1][0]);
            if(checked){
                rs = Math.max(rs, pair[0][1] + pair[1][1] - 1);
                continue;
            }
            rs = Math.max(rs, pair[0][1] + pair[1][1]);
        }
        return rs;
    }
    public static void main(String[] args) {
        int n = 8; 
        int[][] roads = {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        // int n = 4; 
        // int[][] roads = {{0,1},{0,3},{1,2},{1,3}};
        System.out.println(maximalNetworkRank(n, roads));
    }
}
