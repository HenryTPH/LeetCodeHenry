public class ReverseLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
    public static ListNode reverseList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode reversedList = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }
    public static void main(String[] args) {
        
    }
}
