import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

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
    }
    /*
     * Degree Counting with Direct Connection Check
To solve the "Maximal Network Rank" problem in a more optimized manner, we focus on reducing the redundant work by using the degree counting method. Instead of iterating through all pairs of cities, which is inefficient, we first identify cities with the highest and second-highest degrees. By doing so, we can restrict our checks to a smaller subset of cities, thus saving time.

Key Data Structures:
Degree List: A list that stores the number of roads connected to each city.
Road Set: A set to store the roads for quick O(1) lookup.
Enhanced Breakdown:
Degree Calculation:

Initialize a list called degrees of size n with zeros.
For every road in the given list of roads, increment the degree of both connected cities.
Identifying Key Nodes:

Sort the unique degrees in descending order.
Identify the highest and second-highest degrees.
Count the number of cities with the highest and second-highest degrees.
Maximal Rank Calculation:

If there are multiple cities with the highest degree, check the direct connections among them. The rank is based on these connections.
If there's only one city with the highest degree, check its direct connections with cities having the second-highest degree. The rank is based on these connections.
Wrap-up:

Return the computed maximal network rank.
     */
    public static int maximalNetworkRank(int n, int[][] roads){
        int[] degrees = new int[n];
        for(int[] road: roads){
            degrees[road[0]]++;
            degrees[road[1]]++;
        }
        Set<Integer> uniqueDegrees = new HashSet<>();
        for(int degree: degrees){
            uniqueDegrees.add(degree);
        }
        List<Integer> sortedDegrees = new ArrayList<>(uniqueDegrees);
        Collections.sort(sortedDegrees, Collections.reverseOrder());
        int maxDeg = sortedDegrees.get(0);
        int secondMaxDeg = sortedDegrees.size() > 1 ? sortedDegrees.get(1) : 0;
        int maxCount = 0;
        for(int degree: degrees){
            if(degree == maxCount) maxCount++;
        }
        int secondMaxCount = 0;
        for(int degree: degrees){
            if(degree == secondMaxCount) secondMaxCount++;
        }
        if(maxCount > 1){
            int directlyConnected = 0;
            for(int[] road: roads){
                if(degrees[road[0]] == maxDeg && degrees[road[1]] == maxDeg){
                    directlyConnected++;
                }
                int possibleConnections = maxCount * (maxCount - 1)/2;
                return possibleConnections == directlyConnected ? 2 * maxDeg - 1 : 2 * maxDeg;
            }
        }
        int directConnectionsToSecond = 0;
        for(int[] road: roads){
            if((degrees[road[0]] == maxDeg && degrees[road[1]] == secondMaxDeg) || (degrees[road[0]] == secondMaxDeg && degrees[road[1]] == maxDeg)){
                directConnectionsToSecond++;
            }
        }
        return secondMaxCount == directConnectionsToSecond ? maxDeg + secondMaxDeg - 1 : maxDeg + secondMaxDeg;
    }
    public static void main(String[] args) {
        int n = 8; 
        int[][] roads = {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        // int n = 4; 
        // int[][] roads = {{0,1},{0,3},{1,2},{1,3}};
        System.out.println(maximalNetworkRank(n, roads));
    }
}
