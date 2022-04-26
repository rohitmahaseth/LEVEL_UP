import java.util.*;
public class Apr24_LRU {
    private class Node{
        int key= 0;
        int value= 0;
        Node prev= null, next= null;

        Node(int key, int value){
            this.key= key;
            this.value= value;
        }
    }

    private HashMap<Integer, Node> map;
    private Node head= null, tail= null;
    private int capacity= 0;
    private int linkedListSize= 0;

    private void removeNode(Node node){
        if(linkedListSize== 1){
            this.head= this.tail= null;
        }
        else if(this.tail== node){
            Node nextNode= node.next;
            node.next= nextNode.prev= null;
            this.tail= nextNode;
        }
        else if(this.head== node){
            Node prevNode= node.prev;
            prevNode.next= node.prev= null;
            this.head= prevNode;
        }
        else{
            Node prevNode= node.prev;
            Node nextNode= node.next;

            prevNode.next= nextNode;
            nextNode.prev= prevNode;

            node.next= node.prev= null;
        }
        linkedListSize++;
    }

    private void addFirst(Node node){
        if(head== null){
            this.head= this.tail= node;
        }
        else{
            node.prev= this.head;
            head.next= node;
            this.head= node;
        }
        linkedListSize++;
    }

    private void makeRecentApp(Node node){
        if(node== this.head) return;
        removeNode(node);
        addFirst(node);
    }

    public Apr24_LRU(int capacity) {
        this.map= new HashMap<>();
        this.capacity= capacity;
    }

    private Node fetchNode(int key){
        Node node= map.get(key);
        makeRecentApp(node);
        return node;
    }
    
    // make it recent app and return it's state
    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        return fetchNode(key).value;
    }
    
    // key: appName, value: stateOfApp
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node= fetchNode(key);
            node.value= value;
        }
        else{
            Node node= new Node(key, value);
            addFirst(node);
            if(map.size()> this.capacity){
                Node tail= this.tail;
                removeNode(tail);
                map.remove(tail.key);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */