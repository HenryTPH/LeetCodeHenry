import java.util.HashSet;
import java.util.Set;

public class NumberOfProvinces {
    /**
     * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }
    private void dfs(int[][] isConnected, boolean[] visited, int city){
        visited[city] = true;
        for(int i = 0; i < isConnected.length; i++){
            if(isConnected[city][i] == 1 && !visited[i]){
                dfs(isConnected, visited, i);
            }
        }
    }
    public static void main(String[] args) {
        
    }

    static class UnionFind {
        int[] p; // Parent of node i
        int[] height; // The height of the subtree with root node i
        UnionFind(int n){
            p = new int[n];
            height = new int[n];
            for(int i = 0; i < n; i++){
                p[i] = i;
                height[i] = 1;
            }
        }
        int find(int u){
            if(p[u] == u) return u;
            return find(p[u]);
        }
        void merge(int root1, int root2){
            if(root1 == root2) return;
            if(height[root1] < height[root2]){
                int temp = root1;
                root1 = root2;
                root2 = temp;
            }
            p[root2] = root1;
            height[root1] = Math.max(height[root1], 1 + height[root2]);
        }
    }
    public int findCircleNumUsingUnionFind(int[][] isConnected){
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isConnected[i][j] == 1){
                    int root1 = uf.find(i);
                    int root2 = uf.find(j);
                    uf.merge(root1, root2);
                }
            }
        }
        Set<Integer> components = new HashSet<>();
        for(int i = 0; i < n ; i++){
            components.add(uf.find(i));
        }
        return components.size();
    }
}
