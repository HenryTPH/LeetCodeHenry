import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Similar problem can be solve by using Dijkstra's Algorithm:
 * 787. Cheapest Flights Within K Stops
778. Swim in Rising Water
815. Bus Routes
1091. Shortest Path in Binary Matrix
1631. Path With Minimum Effort
2812. Find the Safest Path in a Grid
2642. Design Graph With Shortest Path Calculator
Solve those problem below: https://leetcode.com/problems/network-delay-time/solutions/2310813/dijkstra-s-algorithm-template-list-of-problems
1514. Path with Maximum Probability
787. Cheapest Flights Within K Stops
1584. Min Cost to Connect All Points
1976. Number of Ways to Arrive at Destination
1334. Find the City with the Smallest Number of Neighbors at THreshold Distance

 */

public class NetworkDelayTime {
    /**
     * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k){
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for(int i = 1; i <= n; i++){
            graph.put(i, new HashMap<>());
        }
        for(int[] item: times){
            graph.get(item[0]).put(item[1], item[2]);
        }

        int[] distances = new int[n+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{k, 0});

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int currNode = curr[0];
            int currWeight = curr[1];
            for(Map.Entry<Integer, Integer> neighbor: graph.get(currNode).entrySet()){
                int nextNode = neighbor.getKey();
                int newDist = currWeight + neighbor.getValue();
                if(newDist < distances[nextNode]){
                    distances[nextNode] = newDist;
                    queue.add(new int[]{nextNode, newDist});
                }
            }
        }

        int count = 0;
        for(int i = 1; i <= n; i++){
            if(distances[i] > count){
                count = Math.max(count, distances[i]);
            }
        }
        return count == Integer.MAX_VALUE ? -1:count;
    }
    public static int networkDelayTimeBFS(int[][] times, int n, int k){
        int[] result = new int[n+1];
        int max = Integer.MAX_VALUE;
        Arrays.fill(result, max);
        result[k] = 0;
        for(int i = 0; i < n-1; i++){
            boolean found = false;
            for(int[] time: times){
                int src = time[0];
                int dest = time[1];
                int weight = time[2];
                if(result[src] == max) continue;
                if(result[src] + weight < result[dest]){
                    found = true;
                    result[dest] = result[src] + weight;
                }
            }
            if(!found) break;
        }
        int answer = -1;
        for(int i = 1; i < n+1; i++){
            if(result[i] == max) return -1;
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }
    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(networkDelayTimeBFS(times, 4, 2));
    }
}
