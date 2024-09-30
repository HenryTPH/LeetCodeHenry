import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    static class Edge{
        int destination;
        int weight;
        public Edge(int dest, int weight){
            this.destination = dest;
            this.weight = weight;
        }
    }
    static class Graph{
        private int vertices; // Number of vertices in the graph
        private LinkedList<Edge>[] adjacencyList;
        public Graph(int vertices){
            this.vertices = vertices;
            this.adjacencyList = new LinkedList[vertices];
            for(int i = 0; i < vertices; i++){
                adjacencyList[i] = new LinkedList<>();
            }
        }
        public void addEdge(int source, int dest, int weight){
            adjacencyList[source].add(new Edge(dest, weight));
            adjacencyList[dest].add(new Edge(source, weight));
        }
        public void Dijkstra(int startNode){
            int[] distances = new int[vertices];
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(vertices, Comparator.comparingInt(edge -> edge.weight));
            boolean[] visited = new boolean[vertices];

            //Initialize distances as infinity and visited as false
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[startNode] = 0;
            priorityQueue.add(new Edge(startNode, 0));
            while(!priorityQueue.isEmpty()){
                Edge currEdge = priorityQueue.poll();
                int currentVertex = currEdge.destination;
                if(visited[currentVertex]) continue;
                visited[currentVertex] = true;
                for(Edge edge: adjacencyList[currentVertex]){
                    int neighbor = edge.destination;
                    int newDist = edge.weight + distances[currentVertex];
                    // If a shorted path to neighbor is found, update the distance and add to priority queue
                    if(newDist < distances[neighbor]){
                        distances[neighbor] = newDist;
                        priorityQueue.add(new Edge(neighbor, newDist));
                    }
                }
            }
            for(int i = 0; i < distances.length; i++){
                System.out.println(i + "\t\t" + distances[i]);
            }
        }
    }
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 5, 1);

        // Start Dijkstra from vertex 0
        graph.Dijkstra(0);
    }
}
