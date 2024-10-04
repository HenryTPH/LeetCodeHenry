import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStops {
    /**
     * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public static int findCheapestPriceMySolution(int n, int[][] flights, int src, int dst, int k){
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] flight: flights){
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            graph.get(from).add(new int[]{to, cost});
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 0}); // Node, cost, countNode
        ArrayList<Integer> totalCost = new ArrayList<>();
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int node = current[0];
            int cost = current[1];
            int count = current[2];
            if(count == k + 1){
                continue;
            }
            for(int i = 0; i < graph.get(node).size(); i++){
                int[] destination = graph.get(node).get(i);
                int dest = destination[0];
                int destCost = destination[1];
                int newCost = destCost + cost;
                if(dest == dst){
                    totalCost.add(newCost);
                    continue;
                } 
                queue.add(new int[]{dest, newCost, count + 1});
            }
        }
        return totalCost.isEmpty() ? -1 : Collections.min(totalCost);
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            graph.get(from).add(new int[]{to, cost});
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{src, 0, 0});
        
        int[][] minCost = new int[n][k + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minCost[i], Integer.MAX_VALUE);
        }
        minCost[src][0] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int cost = current[1];
            int count = current[2];
            if (node == dst) {
                return cost;
            }
            if (count > k) {
                continue;
            }
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int nextCost = neighbor[1];
                int totalCost = cost + nextCost;

                if (totalCost < minCost[nextNode][count + 1]) {
                    minCost[nextNode][count + 1] = totalCost;
                    queue.add(new int[]{nextNode, totalCost, count + 1});
                }
            }
        }
        
        return -1;
    }
    public static void main(String[] args) {
        int[][] case1 = {{0,1,100}, {1,2,100}, {2,0,100}, {1,3,600}, {2,3,200}};
        int[][] case2 = {{0,1,100}, {1,2,100}, {0,2,500}};
        int[][] case3 = {{0,1,100}, {1,2,100}, {0,2,500}};
        int[][] case4 = {{0,1,1}, {0,2,5}, {1,2,1}, {2,3,1}};
        int[][] case5 = {{10,14,43},{1,12,62},{4,2,62},{14,10,49},{9,5,29},{13,7,53},{4,12,90},{14,9,38},{11,2,64},{2,13,92},{11,5,42},{10,1,89},{14,0,32},{9,4,81},{3,6,97},{7,13,35},{11,9,63},{5,7,82},{13,6,57},{4,5,100},{2,9,34},{11,13,1},{14,8,1},{12,10,42},{2,4,41},{0,6,55},{5,12,1},{13,3,67},{3,13,36},{3,12,73},{7,5,72},{5,6,100},{7,6,52},{4,7,43},{6,3,67},{3,1,66},{8,12,30},{8,3,42},{9,3,57},{12,6,31},{2,7,10},{14,4,91},{2,3,29},{8,9,29},{2,11,65},{3,8,49},{6,14,22},{4,6,38},{13,0,78},{1,10,97},{8,14,40},{7,9,3},{14,6,4},{4,8,75},{1,6,56}};
        System.out.println(findCheapestPrice(15, case5, 1, 4, 10));
    }
}
