public class LInkedList {
    private class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public LInkedList(){
        this.size = 0;
    }
    public void add(int x){
        Node newNode = new Node(x);
        if(head == null){
            head = tail=newNode;
        } else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, int x){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(index == size){
            add(x);
            return;
        }
        Node newNode = new Node(x);
        if(index == 0){
            newNode.next = head;
            head = newNode;
        } else{
            Node current = head;
            for(int i = 0; i < index-1; i++){
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public int remove(int index){
        if(index < 0 || index > size){ throw new IndexOutOfBoundsException();}
        int removed;
        if(index == 0){
            removed = head.data;
            head = head.next;
            if(head==null) tail = null;
        } else {
            Node current = head;
            for(int i = 0; i < index-1; i++){
                current = current.next;
            }
            removed = current.next.data;
            current.next = current.next.next;
            if(current==null) tail = current;
        }
        size--;
        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public boolean contains(int x) {
        Node current = head;
        while (current != null) {
            if (current.data == x) return true;
            current = current.next;
        }
        return false;
    }
}
