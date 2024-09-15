import java.util.LinkedList;
import java.util.Queue;

public class SearchInBinarySearchTree {
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
    public static TreeNode searchBST(TreeNode root, int val){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curr = null;
        while(!queue.isEmpty()){
            curr = queue.poll();
            if(curr.val == val) {
                break;
            }
            if(curr.val > val && curr.left != null) queue.add(curr.left);
            if(curr.val < val && curr.right != null) queue.add(curr.right);
        }
        if(curr.val != val) return null;
        return curr;
    }
    public static void main(String[] args) {
        TreeNode nodes = new TreeNode(4);
        nodes.left = new TreeNode(2);
        nodes.right = new TreeNode(7);
        nodes.left.left = new TreeNode(1);
        nodes.left.right = new TreeNode(3);
        System.out.println(searchBST(nodes, 5).val);
    }
}
