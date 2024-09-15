import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeRightSideView {
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
    public static List<Integer> rightSideView(TreeNode root){
        List<Integer> nodes = new ArrayList<>();
        if(root == null) return nodes;
        nodes.add(root.val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        bfsHelper(queue, nodes);
        return nodes;
    }
    public static void bfsHelper(Queue<TreeNode> queue, List<Integer> nodes){
        List<Integer> subList = new ArrayList<>();
        Queue<TreeNode> newQueue = new LinkedList<>();
        if(queue.isEmpty()) return;
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(curr.right != null){
                subList.add(curr.right.val);
                newQueue.add(curr.right);                
            }
            if(curr.left != null){
                subList.add(curr.left.val);
                newQueue.add(curr.left);                
            }
        }
        if(subList.isEmpty()) return;
        nodes.add(subList.get(0));
        bfsHelper(newQueue, nodes);
    }
    public static void main(String[] args) {
        TreeNode nodes = new TreeNode(1);
        nodes.left = new TreeNode(2);
        nodes.right = new TreeNode(3);
        nodes.left.right = new TreeNode(5);
        nodes.right.right = new TreeNode(4);
        System.out.println(rightSideView(nodes));
    }
}
