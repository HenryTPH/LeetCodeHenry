public class OddEvenLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
    public static ListNode oddEvenList(ListNode head){
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        while(odd.next.next != null){
            odd.next = odd.next.next;
        }
        while(even.next.next != null){
            even.next = even.next.next;
        }
        return head;
    }
    public static void main(String[] args) {
        
    }
}
