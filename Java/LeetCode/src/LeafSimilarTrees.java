import java.util.ArrayList;

public class LeafSimilarTrees {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static boolean leafSimilar(TreeNode root1, TreeNode root2){
        ArrayList<Integer> leafOfRoot1 = new ArrayList<>();
        ArrayList<Integer> leafOfRoot2 = new ArrayList<>();
        findLeafNodes(root1, leafOfRoot1);
        findLeafNodes(root2, leafOfRoot2);
        return leafOfRoot1.equals(leafOfRoot2);
    }
    public static void findLeafNodes(TreeNode root, ArrayList<Integer> leafNodes){
        if(root == null) return;
        if(root.left == null && root.right == null){
            leafNodes.add(root.val);
        }
        findLeafNodes(root.left, leafNodes);
        findLeafNodes(root.right, leafNodes);
    }
    public static void main(String[] args) {
        
    }
}
