package client.snake;

public class SnakeLinkedList {
    private Node head;

    public SnakeLinkedList() {
        super();
    }

    public SnakeLinkedList(Node head) {
        super();
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return (getSize() == 0);
    }

    /**
     * Add the given node to the tail of the list.
     * If the list is empty, create a new list and add the node*/
    public void addLast(Node node) {
        if (this.head == null) {
            head = node;
            return;
        }
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        node.setPrevious(temp);
        temp.setNext(node);


    }

    /**
     * Return the size of the snake (The number of parts)*/
    public int getSize() {
        if (this.head == null)
            return 0;

        int sz = 1; // the size iterator
        Node temp = head;
        while (temp.getNext() != null) {
            sz++;
            temp = temp.getNext();
        }

        return sz;
    }



    /**
     * Return the last part of the linked list (The Tail)*/
    public Node getTail() {
        if(head == null)
            return null;

        Node temp = head;
        while (temp.getNext() != null)
            temp = temp.getNext();
        return temp;
    }
}
