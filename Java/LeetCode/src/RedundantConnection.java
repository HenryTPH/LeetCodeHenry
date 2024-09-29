public class RedundantConnection {
    /**
     * In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges){
        int[] parent = new int[edges.length+1];
        for(int i = 0; i < parent.length; i++) parent[i] = i;
        for(int[] edge: edges){
            if(find(edge[0], parent) == find(edge[1], parent)) return edge;
            union(edge[0], edge[1], parent);
        }
        return null;
    }
    public int find(int node, int[] parent){
        while(parent[node] != node){
            node = parent[node];
        }
        return node;
    }
    public void union(int nodeA, int nodeB, int[] parent){
        int rootA = find(nodeA, parent);
        int rootB = find(nodeB, parent);
        if(rootA != rootB){
            parent[rootB] = rootA;
        }
    }
    public static void main(String[] args) {
        
    }
}
