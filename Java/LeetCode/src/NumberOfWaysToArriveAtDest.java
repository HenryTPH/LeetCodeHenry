import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/* Save Solution
 class Solution {
    public int countPaths(int numIntersections, int[][] roads) {
        ArrayList<ArrayList<Node>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numIntersections; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int intersection1 = road[0], intersection2 = road[1], travelTime = road[2];
            adjacencyList.get(intersection1).add(new Node(travelTime, intersection2));
            adjacencyList.get(intersection2).add(new Node(travelTime, intersection1));
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<>((x, y) -> Long.compare(x.distanceFromSource, y.distanceFromSource));
        long[] distanceFromSource = new long[numIntersections];
        int[] numWaysToReach = new int[numIntersections];
        for (int i = 0; i < numIntersections; i++) {
            distanceFromSource[i] = Long.MAX_VALUE;
            numWaysToReach[i] = 0;
        }
        distanceFromSource[0] = 0;
        numWaysToReach[0] = 1;
        minHeap.add(new Node(0, 0));
        int modulo = (int) (1e9 + 7);
        while (!minHeap.isEmpty()) {
            long currentDistance = minHeap.peek().distanceFromSource;
            int currentIntersection = minHeap.peek().intersection;
            minHeap.remove();
            for (Node neighbor : adjacencyList.get(currentIntersection)) {
                long neighborIntersection = neighbor.intersection;
                long travelTimeToNeighbor = neighbor.distanceFromSource;
                if (currentDistance + travelTimeToNeighbor < distanceFromSource[(int) neighborIntersection]) {
                    distanceFromSource[(int) neighborIntersection] = currentDistance + travelTimeToNeighbor;
                    minHeap.add(new Node(currentDistance + travelTimeToNeighbor, (int) neighborIntersection));
                    numWaysToReach[(int) neighborIntersection] = numWaysToReach[currentIntersection];
                } else if (currentDistance + travelTimeToNeighbor == distanceFromSource[(int) neighborIntersection]) {
                    numWaysToReach[(int) neighborIntersection] = (numWaysToReach[(int) neighborIntersection] + 
                                                                 numWaysToReach[currentIntersection]) % modulo;
                }
            }
        }
        return numWaysToReach[numIntersections - 1] % modulo;
    }
    class Node {
        long distanceFromSource;
        int intersection;
        Node(long distanceFromSource, int intersection) {
            this.distanceFromSource = distanceFromSource;
            this.intersection = intersection;
        }
    }
}
 */

public class NumberOfWaysToArriveAtDest {
    /**
     * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
     * @param n
     * @param roads
     * @return
     */
    public static int countPaths(int n, int[][] roads){
        List<List<Node>> graph =new ArrayList<>();
        for(int i = 0; i < n; i ++){
            graph.add(new ArrayList<>());
        }
        for(int[] r: roads){
            graph.get(r[0]).add(new Node(r[1], (long) r[2]));
            graph.get(r[1]).add(new Node(r[0], r[2]));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Long.compare(a.distance, b.distance));
        pq.add(new Node(0, 0));
        long[] distanceFromSource = new long[n];
        Arrays.fill(distanceFromSource, Long.MAX_VALUE);
        distanceFromSource[0] = 0;

        //Store the number of ways to reach each node
        long[] ways = new long[n];
        ways[0] = 1;

        long mod = 1_000_000_007;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int dest = current.destination;
            long distance = current.distance;

            // If this is the longer path, skip processing
            if(distance > distanceFromSource[dest]) continue;
            
            for(Node node: graph.get(dest)){
                int destination = node.destination;
                long newDistance = distance + node.distance;
                if(newDistance < distanceFromSource[destination]){
                    distanceFromSource[destination] = newDistance;
                    pq.add(new Node(destination, newDistance));
                    // Inherit the ways from the current node
                    ways[destination] = ways[dest];
                } else if(newDistance == distanceFromSource[destination]){
                    // This is an alternate shortest path
                    ways[destination] = (ways[destination] + ways[dest]) % mod;
                }
            }
        }
        return (int) ways[n - 1];
    }
    static class Node{
        long distance;
        int destination;
        Node(int destination, long distance){
            this.destination = destination;
            this.distance = distance;
        }
    }
    public static void main(String[] args) {
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        System.out.println(countPaths(7, roads));
    }
}
