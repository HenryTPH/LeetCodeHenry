import java.util.Stack;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSorting {
    static void topologicalSort(List<List<Integer>> graph, int V){
        //Stack to store the result
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        //Call the recursive helper function to store
        //Topological Sort starting from all vertices one by one
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfs(i, graph, visited, stack);
            }
        }
        System.out.println("Topological sorting of the graph: ");
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
    static void dfs(int vertex, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack){
        //Mark the current node as visited
        visited[vertex] = true;
        //Recur for all adjacent vertices
        for(int i: adj.get(vertex)){
            if(!visited[i]){
                dfs(i, adj, visited, stack);
            }
        }
        stack.push(vertex);
    }

    /*
     * Topological Sorting using BFS - Kahn's Algorithm
     */
    public static int[] topologicalSortingBFS(List<List<Integer>> adj, int V){
        int[] indegree = new int[V];
        for(int i = 0; i < V; i++){
            for(int it: adj.get(i)){
                indegree[it]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < V; i++){
            if(indegree[i] == 0) queue.add(i);
        }
        int[] rs = new int[V];
        int index = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            rs[index++] = node;
            for(int it: adj.get(node)){
                if(--indegree[it] == 0) queue.add(it);
            }
        }
        if(index != V){
            System.out.println("Graph contains cycles!");
            return new int[0];
        }
        return rs;
    }
    public static void main(String[] args) {
        
    }
}
