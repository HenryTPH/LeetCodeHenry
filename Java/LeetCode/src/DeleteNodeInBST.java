import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeleteNodeInBST {
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
     * REturn the root node reference of the BST
     * 1. Search for a node to remove
     * 2. If the node is found, delete node
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null) return root;
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        } else if(root.val > key){
            root.left = deleteNode(root.left, key);
        } else {
            if(root.left == null && root.right == null) return null;
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            TreeNode parentOfMaxNode = findParentOfMaxNode(root.left);
            root.val = parentOfMaxNode.val;
            root.left = deleteNode(root.left, parentOfMaxNode.val);
        }
        return root;
    }    
    public TreeNode findParentOfMaxNode(TreeNode root){
        while(root.right != null){
            root = root.right;
        }
        return root;
    }
    public static void main(String[] args) {
        
    }
    
}
