import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    /**
     * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
     * @param node
     * @return
     */
    public static Node cloneGraph(Node node){        
        if(node == null) return null;
        HashMap<Node, Node> cloned = new HashMap<>();
        return copyNode(node, cloned);
    }
    private static Node copyNode(Node current, HashMap<Node, Node> cloned){
        if(cloned.containsKey(current)){
            return cloned.get(current);
        }
        Node clone = new Node(current.val, new ArrayList<>());
        cloned.put(current, clone);
        for(Node neighbor: current.neighbors){
            clone.neighbors.add(copyNode(neighbor, cloned));
        }
        return clone;
    }
    public Node cloneGraphBFS(Node node){
        if(node == null) return null;
        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        Node clone = new Node(node.val, new ArrayList<>());
        visited.put(node, clone);
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            for(Node neighbor: curr.neighbors){
                if(!visited.containsKey(neighbor)){
                    Node cloneNeighbor = new Node(neighbor.val, new ArrayList<>());
                    visited.put(neighbor, cloneNeighbor);
                    queue.add(neighbor);
                }
                visited.get(curr).neighbors.add(visited.get(neighbor));
            }
        }
        return clone;
    }
    public static void main(String[] args) {
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        Node n4 = new Node(4, new ArrayList<>());
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);
        System.out.println(cloneGraph(n1));
    }
}
