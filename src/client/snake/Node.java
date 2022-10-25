package client.snake;

import java.awt.Color;
import java.io.Serializable;

public class Node implements Serializable {
    private int x;
    private int y;
    private Color color;
    private Node next; // a 'pointer' to the next node
    private Node previous =null; // a 'pointer' to the previous node


    public Node() {
        super();
    }

    public Node(int x, int y, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Node(Color color) {
        super();
        this.color = color;
    }


    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }


    public Node getNext() {
        return next;
    }


    public void setNext(Node next) {
        this.next = next;
    }



    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Check whether the element has a next node or not*/
    public boolean hasNext() {
        return (this.getNext() != null);
    }

    /**
     * Check whether the element has a previous node or not*/
    public boolean hasPrevious() {
        return (this.getPrevious() != null);
    }
}
