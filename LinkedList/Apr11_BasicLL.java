public class Apr11_BasicLL{
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //876(second mid)
    public ListNode middleNode(ListNode head) {
        if(head== null || head.next== null) return head;
        ListNode slow= head;
        ListNode fast= head;
        while(fast!= null && fast.next!= null){
            slow= slow.next;
            fast= fast.next.next;
        }
        
        return slow;
    }

    //first mid
    public ListNode middleNode1(ListNode head){
        if(head== null || head.next== null) return head;
        ListNode slow= head;
        ListNode fast= head;
        while(fast.next!= null && fast.next.next!= null){
            slow= slow.next;
            fast= fast.next.next;
        }

        return slow;
    }

    public int length(ListNode head){
        ListNode curr= new ListNode();
        curr= head;
        int len= 0;
        while(curr!= null){
            curr= curr.next;
            len++;
        }

        return len;
    }

    //206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if(head== null || head.next== null) return head;
        ListNode pre= null;
        ListNode curr= head;
        
        while(curr!= null){
            ListNode forw= curr.next;
            curr.next= pre;
            
            pre= curr;
            curr= forw;                
        }
        
        return pre;
    }

    // 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        if(head== null || head.next== null) return true;
        ListNode mid= middleNode1(head), nHead= mid.next;
        mid.next= null;        
        nHead= reverseList(nHead);
        
        ListNode c1= head, c2= nHead;
        boolean flag= true;
        while(c2!= null){
            if(c1.val== c2.val){
                c1= c1.next;
                c2= c2.next;
            }
            else{
                flag= false;
                break;
            }
        }
        
        mid.next= reverseList(nHead);
        return flag;
        
    }

}