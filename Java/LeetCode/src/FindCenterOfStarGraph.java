import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class FindCenterOfStarGraph {
    /**
     * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
     * @param edges
     * @return
     */
    public static int findCenter(int[][] edges){
        int rs = -1;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int i = 1; i <= edges.length+1; i++){
            map.put(i, new HashSet<>());
        }
        for(int i = 0; i < edges.length; i++){
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        for(Map.Entry<Integer, HashSet<Integer>> entry: map.entrySet()){
            if(entry.getValue().size() == edges.length) rs = entry.getKey();
        }
        return rs;
    }
    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{4,2}};
        System.out.println(findCenter(edges));
    }
}
