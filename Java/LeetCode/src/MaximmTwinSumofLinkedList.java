import java.util.Stack;

public class MaximmTwinSumofLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
    /**
     * Two pointer, Stack
     * Linkedlist of size n which is even
     * The ith node is the twin of the (n-i-1)th node
     * Return the maximum twin sum of the linked list
     * @param head
     * @return
     */
    public static int pairSum(ListNode head){
        if(head == null) return 0;
        Stack<ListNode> stack = new Stack<>();
        ListNode left = head;
        // This will push all the node.
        // Actually we can only push half of the list to the stack
        while(head!= null){
            stack.add(head);
            head = head.next;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= stack.size(); i++){
            ListNode stackNode = stack.pop();
            int sum = stackNode.val + left.val;
            max = Math.max(sum, max);
            left = left.next;
        }
        return max;
    }
    public static int pairSumHalfOfList(ListNode head){
        if(head == null) return 0;
        Stack<ListNode> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null){
            stack.add(slow);
            slow = slow.next;
            fast = fast.next.next;
        }
        int max = Integer.MIN_VALUE;
        while(slow != null){
            max = Math.max(max, slow.val + stack.pop().val);
            slow = slow.next;
        }
        return max;
    }
    public static void main(String[] args) {
        int[] num = {7,57,13,31,17,65,32,3,97,22,7,20,69,35,69,75,13,33,50,80,64,71,15,28,2,27,39,48,13,22,84,5,51,46,26,78,56,63};
    }
}
