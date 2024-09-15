import java.util.ArrayList;
import java.util.Collections;

public class LongestZigZagPathInBinaryTree {
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
    // Solution from Leetcode
    private static int max1 = 0;
    public static int longestZigZag1(TreeNode root){
        dfs1(root, true, 0);
        return max1;
    }
    public static void dfs1(TreeNode node, boolean isLeft, int length){
        if(node == null) return;
        max1 = Math.max(max1, length);
        dfs1(node.left, true, !isLeft ? length + 1 : 1);
        dfs1(node.right, false, isLeft ? length + 1 : 1);
    }
    // Solution from chatGPT
    private int max = 0;
    public int longestZigZag(TreeNode root){
        if(root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        dfs(root, true, 0);
        dfs(root, false, 0);
        return max;
    }
    public void dfs(TreeNode node, boolean isLeft, int length){
        if(node == null) return;
        max = Math.max(max, length);
        // If current direction is left, go left, then switch to right
        if(isLeft){
            dfs(node.left, false, length + 1);
            dfs(node.right, true, 1);
        } else {
            dfs(node.right, true, length + 1);
            dfs(node.left, false, 1);
        }
    }
    /**
     * ZigZag path:
     * - Choose any node in the binary tree and a direction (right or left)
     * - If the current direction is right, move to the right child of the current node; otherwise, move to the left child
     * - Change the direction from right to left or from left to right
     * - Change the direction from right to left or from left to right.
     * - Repeat the second and the third steps until you can't move in the tree
     * Zigzag length is the number of nodes visited
     * Return the longest ZigZag path contained in the tree.
     * @param root
     * @return
     */
    /**
     * The approach you have implemented has a few issues that prevent it from finding the correct longest ZigZag path in the tree. Specifically:
You are resetting count to 0 every time you finish processing one direction, which causes you to lose track of the correct count.
The way you handle recursion (countingHelper) does not alternate between left and right correctly after each move.
You do not account for the fact that each node can be the start of a ZigZag path, not just the leftmost or rightmost nodes.
     */
    static int count = 0;  
    static ArrayList<Integer> list = new ArrayList<>();
    public static int longestZigZagHenry(TreeNode root){
              
        while(root.left != null){
            boolean isLeft = true;
            countingHelperHenry(root, isLeft);
            root = root.left;
        }
        while(root.right != null){
            boolean isLeft = false;
            countingHelperHenry(root, isLeft);
            root = root.right;
        }
        return list.isEmpty() ? 0 : Collections.max(list);
    }
    public static void countingHelperHenry(TreeNode node, boolean isLeft){
        if(isLeft && node.left != null){
            count++;
            isLeft = false;
            countingHelperHenry(node.left, isLeft);
        }
        if(!isLeft && node.right != null){
            count++;
            isLeft = true;
            countingHelperHenry(node.right, isLeft);
        }
        list.add(count);
        count = 0;
        return;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        root.right.right.left.right = new TreeNode(7);
        root.right.right.left.right.right = new TreeNode(8);
        System.out.println(longestZigZag(root));
    }
}
