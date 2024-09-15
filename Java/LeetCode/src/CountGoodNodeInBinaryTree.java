import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class CountGoodNodeInBinaryTree {
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
    public static int goodNodes(TreeNode root){
        if(root == null) return 0;
        int count = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(root.val);
        countHelper(root, queue, count);
        return count;
    }
    public static void countHelper(TreeNode root, PriorityQueue<Integer> queue, int count){
        if(root == null) return;
        if (root.val >= queue.peek()){
            count++;
        }
        queue.add(root.val);
        countHelper(root.left, queue, count);
        countHelper(root.right, queue, count);
        queue.remove(root.val);
    }

    public static int goodNodesUsingStack(TreeNode root){
        if(root == null) return 0;
        int count = 0;
        Deque<Object[]> stack = new ArrayDeque<>();
        stack.push(new Object[]{root, Integer.MIN_VALUE});

        while(!stack.isEmpty()){
            Object[] curr = stack.pop();
            TreeNode node = (TreeNode) curr[0];
            int maxVal = (int) curr[1];
            if(node.val >= maxVal){
                count++;
                maxVal = node.val;
            }
            if(node.right != null) stack.push(new Object[]{node.right, maxVal});
            if(node.left != null) stack.push(new Object[]{node.left, maxVal});
        }
        return count;
    }
    public static void main(String[] args) {
        
    }
}
