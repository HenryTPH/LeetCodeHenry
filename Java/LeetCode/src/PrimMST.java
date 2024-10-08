import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST {
    private static final int V = 5;

    //Function to find the vertex with minimum key value, from the set of
    //vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int v = 0; v < V; v++){
            if(!mstSet[v] && key[v] < min){
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // A utility function to print the constructed MST
    // stored in parent[]
    void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t"
                               + graph[i][parent[i]]);
    }

    /*
     * Function to construct and print MST for a graph
     * represented using adjacent matrix representation
     */
    void primMST(int graph[][]){
        // Store MST
        int[] parent = new int[V];
        //Key values used to pick minimum weight edge in cut
        int[] key = new int[V];
        //Set of vertices included in MST
        Boolean[] mstSet = new Boolean[V];
        //Set the keys as Infinite
        for(int i = 0; i < V; i++){
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        /*
         * Always include first  vertex in MST, Make key 0 so that this vertex is picked
         */
        key[0] = 0;
        // First node is always root of MST
        parent[0] = -1;
        // The MST will have V vertices
        for(int count = 0; count < V - 1; count++){
            // Pick the min key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet);
            //Add the picked vertex to the MST Set
            mstSet[u] = true;
            /*
             * Update key value and parent index of the
             * adjacent vertices of the picked vertex.
             * Consider only those vertices which are not
             * yet included in MST
             */
            for(int v = 0; v < V; v++){
                //graph[u][v] is non zero only for adjacent vertices of m mstSet[v] is false for vertices not yet included in MST Update
                //the key only if graph[u][v] is smaller than key[v]
                if(graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]){
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }            
        }
        printMST(parent, graph);
    }
    /*
     * Optimization using PQ
     */
    class iPair{
        int first, second;
        public iPair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    class Graph{
        int V;
        List<List<iPair>> adj;
        public Graph(int V){
            this.V = V;
            adj = new ArrayList<>();
            for(int i = 0; i < V; i++){
                adj.add(new ArrayList<>());
            }
        }
        void addEdge(int u, int v, int weight){
            adj.get(u).add(new iPair(v, weight));
            adj.get(v).add(new iPair(u, weight));
        }
        void primMST(){
            // A priority queue to store vertices that are being primMST
            PriorityQueue<iPair> pq = new PriorityQueue<>(V, new Comparator<iPair>() {
                public int compare(iPair a, iPair b){
                    return a.second - b.second;
                }
            });
            int src = 0;
            // Create a vector for keys and initialize all keys as infinite
            int INF = Integer.MAX_VALUE;
            int[] key = new int[V];
            Arrays.fill(key, INF);
            // Store parent array which in turn store MST
            int[] parent = new int[V];
            Arrays.fill(parent, -1);
            // Keep track of vertices included in MST
            boolean[] inMST = new boolean[V];
            // Insert source itself in priority queue and initialize its key as 0
            pq.offer(new iPair(0, src));
            key[src] = 0;
            while(!pq.isEmpty()){
                /*
                 * The first vertex in pair is the minimum key vertex, extract it from pq
                 * Vertex label is stored in second pf pair (it has to be done this way to
                 * keep the vertices sorted key - key must be first item in pair)
                 */
                int u = pq.peek().second;
                pq.poll();
                /*
                 * Different key values for same vertex may exist in the priority queue. The one
                 * with the least key value is always processed first.
                 * Therefore, ignore the rest.
                 */
                if(inMST[u]) continue;
                inMST[u] = true;
                for(iPair i: adj.get(u)){
                    int v = i.first;
                    int weight = i.second;
                    /*
                     * If v is not in MST and weight of (u,v) is smaller than the current key of V
                     */
                    if(!inMST[v] && weight < key[v]){
                        // updating key of V
                        key[v] = weight;
                        pq.offer(new iPair(key[v], v));
                        parent[v] = u;
                    }
                }
            }
            for(int i = 1; i < V; i++){
                System.out.println(parent[i] + " - " + i);
            }
        }
    }
    public static void main(String[] args) {
        PrimMST t = new PrimMST();
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                                      { 2, 0, 3, 8, 5 },
                                      { 0, 3, 0, 0, 7 },
                                      { 6, 8, 0, 0, 9 },
                                      { 0, 5, 7, 9, 0 } };

        // Print the solution
        t.primMST(graph);
    }
}
