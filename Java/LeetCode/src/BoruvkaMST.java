import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoruvkaMST {
    class Graph{
        private int V;
        private List<List<Integer>> graph;
        Graph(int vertices){
            V = vertices;
            graph = new ArrayList<>();
        }
        void addEdge(int u, int v, int w){
            graph.add(Arrays.asList(u, v, w));
        }
        // Function to find set of an element i (uses path compression technique)
        private int find(List<Integer> parent, int i){
            if(parent.get(i) == i) return i;
            return find(parent, parent.get(i));
        }
        // Function to union of two sets of x and y (uses union by rank)
        private void unionSet(List<Integer> parent, List<Integer> rank, int x, int y){
            int rootX = find(parent, x);
            int rootY = find(parent, y);
            if(rank.get(rootX) < rank.get(rootY)){
                parent.set(rootX, rootY);
            } else if (rank.get(rootX) > rank.get(rootY)){
                parent.set(rootY, rootX);
            } else {
                parent.set(rootY, rootX);
                rank.set(rootX, rank.get(rootX) + 1);
            }
        }
        // Function to build MST using Kruskal's algorithm
        void boruvkaMST(){
            List<Integer> parent = new ArrayList<>();
            // Array to store index of the cheasest edge of subset, it sotre[u, v, w] for each component
            List<Integer> rank = new ArrayList<>();
            List<List<Integer>> cheapest = new ArrayList<>();
            // Initially there are V different threes.
            // Finally there will be one tree that will be MST
            int numTrees = V;
            int MSTweight = 0;
            // Create V subsets with single elements
            for(int node = 0; node < V; node++){
                parent.add(node);
                rank.add(0);
                cheapest.add(Arrays.asList(-1,-1,-1));
            }
            // Keep combining components (or sets) until all components are not combined into single MST
            while(numTrees > 1){
                //Traverse through all edges and update cheapest of every component
                for(List<Integer> edge: graph){
                    // Find components (or sets) of two corners of current edge
                    int u = edge.get(0);
                    int v = edge.get(1);
                    int w = edge.get(2);
                    int set1 = find(parent, u);
                    int set2 = find(parent, v);
                    // If two corners of current edge belong to same set, ignore current edge, else check
                    // if current edge is closer to previous cheapest edges of set1 and set2
                    if(set1 != set2){
                        if(cheapest.get(set1).get(2) == -1 || cheapest.get(set1).get(2) > w){
                            cheapest.set(set1, Arrays.asList(u, v, w));
                        }
                        if(cheapest.get(set2).get(2) == -1 || cheapest.get(set2).get(2) > w){
                            cheapest.set(set2, Arrays.asList(u, v, w));
                        }
                    }
                }
                // Consider the above picked cheapest edges and add them to MST
                for(int node = 0; node < V; node++){
                    // Check if cheapest for current set exists
                    if(cheapest.get(node).get(2) != -1){
                        int u = cheapest.get(node).get(0);
                        int v = cheapest.get(node).get(1);
                        int w = cheapest.get(node).get(2);
                        int set1 = find(parent, u);
                        int set2 = find(parent, v);
                        if(set1 != set2){
                            MSTweight += w;
                            unionSet(parent, rank, set1, set2);
                            System.out.printf("Edge %d-%d with weight %d included in MST\n", u, v, w);
                            numTrees--;
                        }
                    }
                }
                for(List<Integer> list: cheapest){
                    // Reset cheapest array
                    list.set(2, -1);
                }
            }
            System.out.printf("Weight of MST is %d\n",
                    MSTweight);
        }
    }
}
