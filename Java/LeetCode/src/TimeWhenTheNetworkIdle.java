import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeWhenTheNetworkIdle {
    /*
     * There is a network of n servers, labeled from 0 to n - 1. You are given a 2D integer array edges, where edges[i] = [ui, vi] indicates there is a message channel between servers ui and vi, and they can pass any number of messages to each other directly in one second. You are also given a 0-indexed integer array patience of length n.

All servers are connected, i.e., a message can be passed from one server to any other server(s) directly or indirectly through the message channels.

The server labeled 0 is the master server. The rest are data servers. Each data server needs to send its message to the master server for processing and wait for a reply. Messages move between servers optimally, so every message takes the least amount of time to arrive at the master server. The master server will process all newly arrived messages instantly and send a reply to the originating server via the reversed path the message had gone through.

At the beginning of second 0, each data server sends its message to be processed. Starting from second 1, at the beginning of every second, each data server will check if it has received a reply to the message it sent (including any newly arrived replies) from the master server:

If it has not, it will resend the message periodically. The data server i will resend the message every patience[i] second(s), i.e., the data server i will resend the message if patience[i] second(s) have elapsed since the last time the message was sent from this server.
Otherwise, no more resending will occur from this server.
The network becomes idle when there are no messages passing between servers or arriving at servers.

Return the earliest second starting from which the network becomes idle.
     */
    public static int networkBecomesIdle(int[][] edges, int[] patience){
        int n = patience.length;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int[] shortest = new int[n];
        Arrays.fill(shortest, 0);
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            while(size-- > 0){
                int curr = queue.poll();
                for(int i: graph.get(curr)){
                    if(visited[i]) continue;
                    queue.add(i);
                    visited[i] = true;
                    if(shortest[i] == 0) shortest[i] = level;
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++){
            int totalTime = shortest[i]*2;
            if(totalTime <= patience[i]){
                ans = Math.max(ans, totalTime + 1);
                continue;
            }            
            int lastMessSent = (totalTime - 1) / patience[i] * patience[i];
            ans = Math.max(ans, lastMessSent + totalTime + 1);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        // int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5}};
        // int[] patience = {0,2,3,2,1,3};
        int[][] edges = {{0,1},{1,2}};
        int[] patience = {0,2,1};
        System.out.println(networkBecomesIdle(edges, patience));
    }
}
