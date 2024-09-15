public class DeleteMiddleNodeOfLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
    public ListNode deleteMiddle(ListNode head){
        ListNode temp = head;
        int count = 0;
        while(temp != null){
            temp = temp.next;
            count++;
        }
        ListNode newList = head;
        ListNode result = newList;
        if (count == 2){
            newList.next = null;
            return result;
        }
        if (count == 1) return null;
        count = (int) Math.floor(count/2);
        for(int i = 0; i < count-1; i++){
            newList = newList.next;
        }
        
        newList.next = newList.next.next;
        return result;
    }
    public ListNode deleteMiddleGoodSolution(ListNode head){
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode temp = even;
        while(odd.next!= null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = temp;
        return head;
    }
    public static void main(String[] args) {
        
    }
}
