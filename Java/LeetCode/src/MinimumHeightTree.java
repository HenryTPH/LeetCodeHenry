import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class MinimumHeightTree {
    /**
     * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
     * @param n
     * @param edges
     * @return
     */
    public static List<List<Integer>> createUndirectedGraph(int[][] edges, int numOfNodes){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numOfNodes; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
    private static int countHeight(int node, List<List<Integer>> graph, List<Integer> visited){
        int count = 0;
        visited.add(node);
        if(graph.get(node).isEmpty()) return 0;
        for(int i: graph.get(node)){
            if(!visited.contains(i)){
                visited.add(i);
                if(graph.get(i).size() != 0){
                    count = Math.max(count, 1 + countHeight(i, graph, new ArrayList<>(visited)));                
                }
            }            
        }
        return count;
    }
    public static List<Integer> findMinHeightTrees(int n, int[][] edges){
        List<List<Integer>> graph = createUndirectedGraph(edges, n);
        List<Integer> heightOfNodes = new ArrayList<>();
        List<Integer> rs = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(graph.get(i).size() < 1){
                heightOfNodes.add(Integer.MAX_VALUE);
            } else {
                heightOfNodes.add(countHeight(i, graph, new ArrayList<>()));
            }            
        }
        int minHeight = Collections.min(heightOfNodes);
        for(int i = 0; i < heightOfNodes.size(); i++){
            if(heightOfNodes.get(i) == minHeight){
                rs.add(i);
            }
        }
        return rs;
    }
    public static List<Integer> findMinHeightTreesBFS(int n, int[][] edges){
        if(n == 1) return Arrays.asList(0);
        List<List<Integer>> graph = createUndirectedGraph(edges, n);
        List<Integer> edgeCount = new ArrayList<>();
        Deque<Integer> leaves = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            edgeCount.add(graph.get(i).size());
            if (edgeCount.get(i) == 1) {
                leaves.add(i);
            }
        }
        while(!leaves.isEmpty()){
            if(n <= 2) return new ArrayList<>(leaves);
            int leavesSize = leaves.size();
            n -= leavesSize;
            for(int i = 0; i < leavesSize; i++){
                int node = leaves.pollFirst();
                for(int j: graph.get(node)){
                    edgeCount.set(j,edgeCount.get(j) - 1);
                    if(edgeCount.get(j) == 1){
                        leaves.add(j);
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) {
        int[][] arr = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        System.out.println(findMinHeightTreesBFS(6, arr));
    }
}
