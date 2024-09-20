import java.util.Stack;
import java.util.List;

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
    public static void main(String[] args) {
        
    }
}
