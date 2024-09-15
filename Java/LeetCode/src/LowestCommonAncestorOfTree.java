import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class LowestCommonAncestorOfTree {
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
    /**
     * Given a biinary tree, find the lowest common ancestor of two given nodes in the tree
     * The lowest common ancestor is definedd between 2 nodes p and q as the lowest node in T
     * that has both p and q as descendants (where we allow a node to be a descendant of itself)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        if(root == p || root == q) return root;    
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
    
    public static void main(String[] args) {
        
    }
}
