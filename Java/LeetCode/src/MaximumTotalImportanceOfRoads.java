import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumTotalImportanceOfRoads {
    /*
     * You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.

You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

You need to assign each city with an integer value from 1 to n, where each value can only be used once. The importance of a road is then defined as the sum of the values of the two cities it connects.

Return the maximum total importance of all roads possible after assigning the values optimally.
     */
    public static long maximumImportance(int n, int[][] roads){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) graph.add(new ArrayList<>());
        int[] degrees = new int[n];
        int[] result = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < roads.length; i++){
            int v1 = roads[i][0];
            int v2 = roads[i][1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
            degrees[v1]++;
            degrees[v2]++;
        }
        int maxDegree = -1;
        for(int i = 0; i < n; i++){
            if(degrees[i] > maxDegree) maxDegree = degrees[i];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for(int i = 0; i < n; i++){
            pq.add(new int[]{i, degrees[i]});
        }
        int assignValue = n;
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(visited[current[0]]) continue;
            visited[current[0]] = true;
            result[current[0]] = assignValue--;
        }
        long total = 0;
        for(int i = 0; i < roads.length; i++){
            int v1 = roads[i][0];
            int v2 = roads[i][1];
            total += result[v1] + result[v2];
        }
        return total;
    }
    /*
     * More efficient solution
     */
    public long maxImportance(int n, int[][] roads){
        int[] degrees = new int[n];
        for(int[] road: roads){
            degrees[road[0]]++;
            degrees[road[1]]++;
        }
        int[] degreeCount = new int[n];
        for(int degree: degrees){
            degreeCount[degree]++;
        }
        long totalImportance = 0;
        long value = 1;
        for(long i = 0; i < n; i++){
            for(int j = 0; j < degreeCount[(int) i]; j++){
                totalImportance += i*value--;
            }
        }
        return totalImportance;
    }
    public static void main(String[] args) {
        int[][] roads = {{0,1}};
        System.out.println(maximumImportance(5, roads));
    }
}
