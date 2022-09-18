public class Apr11_BasicLL{
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //876(second mid) Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    // Notes:-
    // Use two pointer approach..... slow and fast
    // when size is odd that time we will get mid-node when fast.next pointer is null....
    // when size is even that time we will get mid-node when fast pointer is null....

    //first mid
    public ListNode middleNode1(ListNode head){
        if(head== null || head.next== null) return head;
        ListNode slow= head;
        ListNode fast= head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }

    public int length(ListNode head) {
        ListNode curr = head;
        int idx = 0;
        while (curr != null) {
            curr = curr.next;
            idx++;
        }

        return idx;
    }
    // Notes:-
    // 1---> 2---> 3---> 4---> 5--->null
    // Think like this....when u will jump from node "1" that time idx will be at 1....so when you will be at null that time 
    // idx will be at "5" b/c it has jumped from 5th node so..... size of linked list will be 5.

    //206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode forw = curr.next;
            curr.next = pre;

            pre = curr;
            curr = forw;
        }

        return pre;
    }
    // Notes:-
    // Use 3 pointer....pre, cur, fwd...."pre as null"...."cur as head"...."fwd as cur.next"
    // first store "cur.next".....fwd= cur.next....so that u don't lose that rest of the linked list
    // Now connect cur.next to pre....
    // so we actuall removing one node at time which is at cur at reversing it's direction ans so on.....

    // 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode mid = middleNode1(head), nHead = mid.next;
        mid.next = null;
        nHead = reverseList(nHead);

        ListNode c1 = head, c2 = nHead;
        boolean flag = true;

        // whatever the condition is...2nd half could be smaller or equal so checking c2 as null is enough
        while (c2 != null) {
            if (c1.val == c2.val) {
                c1 = c1.next;
                c2 = c2.next;
            } else {
                flag = false;
                break;
            }
        }
        mid.next = reverseList(nHead);
        return flag;
    }
    // Notes:-
    // Take out the first mid node and break the original linked list in 2 parts.....
    // Now, reverse the 2nd part...
    // Just using loop check for their palindrome...
    // If the size of linked list is odd then size of first part will be one more than 2nd part.....but no need to worry 1---> 0----1 will be palindrme as from first part last node
    // does not needs to be checked if size if different

    // 143. Reorder List
    public void reorderList(ListNode head) {
        ListNode mid = middleNode1(head), nHead = mid.next;
        mid.next = null;
        nHead = reverseList(nHead);

        // This is how reorder will be done....
        ListNode c1 = head, c2 = nHead;

        while (c2 != null) {
            ListNode f1 = c1.next;
            c1.next = c2;
            c1 = f1;

            ListNode f2 = c2.next;
            c2.next = c1;
            c2 = f2;
        }
    }
    // Notes:-
    // Part1: 1---> 2---> 3---> null  Part2: 6---> 5---> 4---> null
    // Reorder part1 and part2: 1---> 6---> 2--->5---> 3---> 4---> null


    // 21. Merge Two Sorted Lists 
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        ListNode dNode = new ListNode(-1);
        ListNode pre = dNode, c1 = list1, c2 = list2;

        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                pre.next = c1;
                c1 = c1.next;
            } else {
                pre.next = c2;
                c2 = c2.next;
            }
            pre = pre.next;
        }

        pre.next = c1 == null ? c2 : c1;
        return dNode.next;
    }
    // Notes:- *****
    // dNode is holding the previous node of the answer...
    
   
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/linked-list/unfold-of-linkedlist/ojquestion
    public void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode h1 = head, h2 = head.next, c1 = h1, c2 = h2;

        while (c2 != null && c2.next != null) {
            ListNode f = c2.next;
            c1.next = f;
            c2.next = f.next;

            c1 = f;
            c2 = f.next;
        }

        c1.next = reverseList(h2);
    }
    // Notes:- 
    // We can acheive this by just reversing all the steps we took for reordering a linked list
    // Just make head as first part1 head and head2.next as 2nd part head
    // Lastly reverse 2nd part and do this h1.next= h2.....(No need to make fist part tail as null)

    // 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = length(head);
        if (len == n) {
            return head.next;
        } else {
            len -= n;
        }

        ListNode cur = head;
        while (len > 1) {
            cur = cur.next;
            len--;
        }

        cur.next = cur.next.next;
        return head;
    }
    // Notes:- Try to get the previous node which has to be deleted....

    // (2nd method)
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            ListNode rn = head;
            head = head.next;
            rn.next = null;
            return head;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rn = slow.next;
        slow.next = rn.next;
        rn.next = null;
        return head;
    }

    // (Follow up)
    public void removeNthFromEnd1_followUp(ListNode head, int n){
        if(head== null || head.next== null) return;
        ListNode a= head, b= head;
        while(n--> 0){
            b= b.next;
        }

        if(b== null){
            a.val= a.next.val;
            ListNode rn= a.next;
            a.next= rn.next;
            rn.next= null;
            return;
        }

        while(b.next!= null){
            a= a.next;
            b= b.next;
        }  

        ListNode rn= a.next;
        a.next= rn.next;
        rn.next= null;
    }

    // 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode ans = new ListNode(-1);
        ListNode curr = ans;
        ListNode c1 = l1, c2 = l2;
        int carry = 0;
        while (c1 != null || c2 != null || carry == 1) {
            int val = carry;
            if (c1 != null) {
                val += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                val += c2.val;
                c2 = c2.next;
            }

            curr.val = val % 10;
            carry = val / 10;

            if (carry == 1 || c1 != null || c2 != null) {
                ListNode rans = new ListNode(carry);
                curr.next = rans;
                curr = rans;
            }
        }

        return reverseList(ans);
    }
    // Notes:- 
    // You only need to add the node in ans if there is a need....(line 277 to line 281)
    // If there exist a carry there then also u should consider it....but can be handled by.....(line 278)

    // subtract two LinkedList
    public int isBiggerList(ListNode l1, ListNode l2){
        int len1= length(l1), len2= length(l2);
        if(len1== len2){
            ListNode c1= l1, c2= l2;
            while(c1!= null){
                if(c1.val!= c2.val) return c1.val- c2.val;
                c1= c1.next;
                c2= c2.next;               
            }
        }

        return len1- len2;
    }

    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if(isBiggerList(l1, l2)< 0){
            ListNode temp= l1;
            l1= l2;
            l2= temp;
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
   
    // pepcoding
    public static ListNode removeDuplicates(ListNode head) {
        if(head== null || head.next== null) return head; 
        ListNode prev= head, curr= head.next;
        
        while(curr!= null){
            if(prev.val!= curr.val){
                prev.next= curr;
                prev= curr;
            }
            curr= curr.next;
        }
        prev.next= null;
        
        return head;
    }


    // pepcoding
    


}