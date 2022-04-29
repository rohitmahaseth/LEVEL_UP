class MyLinkedList {
    private class Node{
        int val= 0;
        Node next= null;
        
        Node(int val){
            this.val= val;
        }
    }
    
    private Node head= null;
    private Node tail= null;
    private int size= 0;
    
    public MyLinkedList() {
        
    }
    
    public int get(int index) {
        if(index< 0 || index>= this.size) return -1;
        
        Node node= getNode(index);
        return node.val;
    }
    
    public void addAtHead(int val) {
        Node node= new Node(val);
        if(this.head== null){
            this.head= this.tail= node; 
        } 
        else{
            node.next= this.head;
            this.head= node;
        }
        this.size++;
    }
    
    public void addAtTail(int val) {
        Node node= new Node(val);
        if(this.head== null){
            this.head= this.tail= node;
        }
        else{
            this.tail.next= node;
            this.tail= node;
        }
        size++;
    }
    
    private Node getNode(int index) {
        Node temp= head;
        while(index--> 0){
            temp= temp.next;
        }
        return temp;
    }
    
    public void addAtIndex(int index, int val) {
        if(index< 0 || index> size) return;
        if(index== 0){
            addAtHead(val);
        }
        else if(index== this.size){
            addAtTail(val);
        }
        else{
            Node node= new Node(val);
            Node prevNode= getNode(index- 1);
            Node nextNode= prevNode.next;

            prevNode.next= node;
            node.next= nextNode; 
            this.size++;
        }        
    }
    
    public void deleteAtIndex(int index) {
        if(index< 0 || index>= size) return; 
        if(size== 1){
            this.head= this.tail= null;
        }
        else if(index== 0){
            Node nextNode= this.head.next;
            this.head.next= null;
            this.head= nextNode;
        } 
        else if(index== size- 1){
            Node prevNode= getNode(index- 1);
            prevNode.next= null;
            this.tail= prevNode;
        }
        else{
            Node prevNode= getNode(index- 1);
            Node rn= prevNode.next;
            
            prevNode.next= rn.next;
            rn.next= null;
        }
        size--;        
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
