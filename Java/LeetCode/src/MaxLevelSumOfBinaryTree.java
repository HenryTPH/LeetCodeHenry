import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

import javax.naming.LinkException;

public class MaxLevelSumOfBinaryTree {
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
    public static int maxLevelSum(TreeNode root){
        if(root == null) return 0;
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        bfsSum(queue, list);
        return list.indexOf(Collections.max(list))+1;
    }
    public static void bfsSum(Queue<TreeNode> queue, List<Integer> list){
        int temp = 0;
        Queue<TreeNode> newQqueue = new LinkedList<>();
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            temp += curr.val;
            if(curr.left != null){
                newQqueue.add(curr.left);
            }
            if(curr.right != null){
                newQqueue.add(curr.right);
            }            
        }
        list.add(temp);
        if(newQqueue.isEmpty()) return;
        bfsSum(newQqueue, list);
    }
    public static void main(String[] args) {
        TreeNode nodes = new TreeNode(1);
        nodes.left = new TreeNode(7);
        nodes.left.left = new TreeNode(7);
        nodes.left.right = new TreeNode(-8);
        nodes.right = new TreeNode(0);
        System.out.println(maxLevelSum(nodes));
    }
}
