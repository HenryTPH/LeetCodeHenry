import java.util.Arrays;
import java.util.PriorityQueue;

import javax.management.NotificationBroadcasterSupport;

public class FindMinTimeToReachLastRoom {
    /*
     * There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.
     */
    public static int minTimeToReach(int[][] moveTime){
        int n = moveTime.length;
        int m = moveTime[0].length;
        // This will move right, left, down, up on a matrix
        int[] move_x = {0, 0, 1, -1};
        int[] move_y = {1, -1, 0, 0};
        boolean[][] visited = new boolean[n][m];
        int result = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[2] != b[2]){
                return Integer.compare(a[2], b[2]);
            } else if (a[0] != b[0]) {
                return Integer.compare(b[0], a[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });
        pq.add(new int[]{0, 0, 0});
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];
            if(visited[x][y]) continue;
            visited[x][y] = true;
            result = Math.max(result, time) + 1;
            if(x == n-1 && y == m-1) return result;
            int minTime = Integer.MAX_VALUE;
            int new_x = -1;
            int new_y = -1;
            for(int i = 0; i < move_x.length; i++){
                int next_x = x + move_x[i];
                int next_y = y + move_y[i];
                if(next_x >= 0 && next_y >= 0 && next_x < n && next_y < m && !visited[next_x][next_y]){
                    if(moveTime[next_x][next_y] < minTime){
                        minTime = moveTime[next_x][next_y];
                        new_x = next_x;
                        new_y = next_y;
                    }
                }
            }
            pq.add(new int[]{new_x, new_y, moveTime[new_x][new_y]});
        }
        return result;
    }

    // Dijkstra's algorithm
    static class Node{
        int i, j;
        long cost;

        public Node(int i, int j, long cost){
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
    public static int minTimeToReachDIJ(int[][] moveTime){
        PriorityQueue<Node> pq = new PriorityQueue<>((n, m) -> Long.compare(n.cost, m.cost));
        int n = moveTime.length;
        int m = moveTime[0].length;        
        boolean[][] visited = new boolean[n][m];
        long[][] distance = new long[n][m];
        for(int i = 0; i < n; i++){
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }
        pq.add(new Node(0, 0, 0));
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.i == n-1 && curr.j == m-1){
                return (int) distance[curr.i][curr.j];
            }
            if(visited[curr.i][curr.j]) continue;
            visited[curr.i][curr.j] = true;
            int directions[][] = {{1,0},{0,1},{-1,0},{0,-1}};
            for(int i = 0; i < directions.length; i++){
                int k = curr.i + directions[i][0];
                int l = curr.j + directions[i][1];
                if(k >= 0 && k < n && l >= 0 && l < m){
                    long costToNode = Math.min(Math.max(curr.cost, moveTime[k][l]) + 1, distance[k][l]);
                    distance[k][l] = costToNode;
                    pq.add(new Node(k, l, costToNode));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    public static void main(String[] args) {
        int[][] moveTime = {{0,0,0},{0,0,0}};
        System.out.println(minTimeToReach(moveTime));
    }
}
