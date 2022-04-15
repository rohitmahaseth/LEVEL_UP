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
        ListNode curr= head;
        int idx= 0;
        while(curr!= null){
            curr= curr.next;
            idx++;
        }

        return idx;
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

    // 143. Reorder List
    public void reorderList(ListNode head) {
        ListNode mid= middleNode1(head), nHead= mid.next;
        mid.next= null;
        nHead= reverseList(nHead);
        ListNode c1= head, c2= nHead;
       
        while(c2!= null){
            ListNode f1= c1.next;
            c1.next =c2;
            c1= f1;
            
            ListNode f2= c2.next;
            c2.next= c1;
            c2= f2;
        }            
    }

    // leetcode 21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode curr= new ListNode();
        if(list1.val< list2.val){
            curr= list1;
            list1= list1.next;
        }
        else{
            curr= list2;
            list2= list2.next;
        }
        
        while(list1!= null && list2!= null){
            if(list1.val<= list2.val){
                curr.next= list1;
                list1= list1.next;
            }
            else{
                curr.next= list2;
                list2= list2.next;
            }
        }
        
        if(list1!= null)
            curr.next= list1;
        else
            curr.next= list2;
        
        return curr;
    }

    //leetcode 21
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if(list1== null || list2== null){
           return list1== null ? list2: list1;
        } 
        
        ListNode dNode= new ListNode(-1);
        ListNode pre= dNode, c1= list1, c2= list2;
        
        while(c1!= null && c2!= null){
            if(c1.val<= c2.val){
                pre.next= c1;
                c1= c1.next;
            }
            else{
                pre.next= c2;
                c2= c2.next;
            }
            pre= pre.next;
        }
        
        pre.next= c1== null ? c2: c1;
        return dNode.next;      
    }

    //pepcoding Unfold LinkedList
    public void unfold(ListNode head) {
        if(head== null || head.next== null) return;
        ListNode h1= head, h2= head.next, c1= h1, c2= h2;
        
        while(c2!= null && c2.next!= null){
            ListNode f= c2.next;
            c1.next= f;
            c2.next= f.next;
            
            c1= f;
            c2= f.next;
        }
        
        c1.next= reverseList(h2);
        
    }

    public void unfold2(ListNode head) {
        if(head== null || head.next== null) return;
        ListNode dN1= new ListNode(-1);
        ListNode dN2= new ListNode(-1);
        ListNode pre1= dN1, pre2= dN2, c1= head, c2= head.next;
        
        while(c1!= null && c2!= null){
            pre1.next= c1;
            pre1= c1;
            c1= c2.next;
            
            pre2.next= c2;
            pre2= c2;
            if(c1!= null){
                c2= c1.next;
            }
    
        }
        
        if(c2!= null){
            c2= c2.next= null;
        }
    
        pre1.next= c1;
        pre2.next= c2;
        if(c1!= null){
            pre1= c1;
        }
    
        ListNode head2= reverseList(dN2.next);
        pre1.next= head2;
        
        head= dN1.next;
    }

    //leetcode 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head== null || head.next== null) return null;
        int tn= length(head);
        if(n== tn){
            return head.next;
        }
        int idx= 1;
        ListNode curr= head;
        while(idx< tn- n){
            curr= curr.next;
            idx++;
        }
        ListNode h2= curr.next.next;
        curr.next.next= null;
        curr.next= h2;
        
        return head;
    }

    //leetcode 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {  
        ListNode ans= new ListNode(-1);
        ListNode curr= ans;
        ListNode c1= l1 , c2= l2;
        int carry= 0;
        while(c1!= null || c2!= null){
            int val= carry;
            if(c1!= null){
                val+= c1.val;
                c1= c1.next;
            }
            if(c2!= null){
                val+= c2.val;
                c2= c2.next;
            }
            
            curr.val= val% 10;
            carry= val/ 10;
            
            if(carry== 1 || c1!= null || c2!= null){
                ListNode rans= new ListNode(carry);
                curr.next= rans;
                curr= rans; 
            }                     
        }
        return ans;    
    }

    // subtract two LinkedList
    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if(l1.val== 0 || l2.val== 0){
            return l1.val== 0 ? l2: l1;
        }
        
        ListNode h1= reverseList(l1);
        ListNode h2= reverseList(l2);
        ListNode ans= new ListNode(-1);
        ListNode curr= ans, c1= h1, c2= h2;
        if(length(h1)< length(h2)){
            c1= h2;
            c2= h1;
        }
        int carry= 0;
        while(c1!= null ){
            int val= carry;
            val+= c1.val;
            val-= (c2!= null ? c2.val: 0);
            if(val< 0){
                val+= 10;
                carry= -1;
            }
            else{
                carry= 0;
            }
            
            c1= c1.next;
            c2= (c2!= null ? c2.next: null);
            
            curr.val= val;
            if(c1!= null){
                curr= curr.next= new ListNode(-1);
            }
        }
        curr= reverseList(ans);
        while(curr!= null && curr.val== 0){
            curr= curr.next;
        }
        
        return curr== null? new ListNode(0): curr;
        
      }

}