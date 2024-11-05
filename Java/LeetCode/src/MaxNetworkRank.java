import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MaxNetworkRank {
    static class Pair implements Comparable<Pair> {
        int position;
        int degree;
    
        public Pair() {
            this.position = -1;
            this.degree = 0;
        }
    
        @Override
        public int compareTo(Pair o) {
            // Sort in descending order of degree
            return o.degree - this.degree;
        }
    }
    public static int maximalNetworkRank(int n, int[][] roads){
        List<Pair> degree = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
            degree.add(new Pair());
            degree.get(i).position = i;
        }
        for(int[] r: roads){
            degree.get(r[0]).degree++;
            degree.get(r[1]).degree++;
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        Collections.sort(degree);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < degree.size(); i++){
            Pair a = degree.get(i);
            for(int j = i+1; j < degree.size(); j++){
                Pair b = degree.get(j);
                boolean connected = graph.get(a.position).contains(b.position);
                if(connected){
                    max = Math.max(max, a.degree + b.degree - 1);
                    continue;
                }
                max = Math.max(max, a.degree + b.degree);
            }
        }
        return max;
        // int[][] pair = new int[2][2];
        // pair[0][0] = -1;
        // pair[1][0] = -1;
        // pair[0][1] = Integer.MIN_VALUE;
        // pair[1][1] = Integer.MIN_VALUE;
        // int rs = Integer.MIN_VALUE;
        // for(int i = 0; i < n ; i++){
        //     if(degree[i] > pair[0][1]){
        //         pair[1][1] = pair[0][1];
        //         pair[0][1] = degree[i];
        //         pair[1][0] = pair[0][0];
        //         pair[0][0] = i;
        //     }
        //     if(degree[i] == pair[0][1] && pair[0][0] != -1 && i != pair[0][0]){
        //         pair[1][1] = degree[i];
        //         pair[1][0] = i;
        //     }
        //     if(degree[i] >= pair[1][1] && degree[i] < pair[0][1]){
        //         pair[1][1] = degree[i];
        //         pair[1][0] = i;
        //     }
        //     boolean checked = graph.get(pair[0][0]).contains(pair[1][0]);
        //     if(checked){
        //         rs = Math.max(rs, pair[0][1] + pair[1][1] - 1);
        //         continue;
        //     }
        //     rs = Math.max(rs, pair[0][1] + pair[1][1]);
        // }
        // return rs;
    }
    public static void main(String[] args) {
        int n = 8; 
        int[][] roads = {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        // int n = 4; 
        // int[][] roads = {{0,1},{0,3},{1,2},{1,3}};
        System.out.println(maximalNetworkRank(n, roads));
    }
}
