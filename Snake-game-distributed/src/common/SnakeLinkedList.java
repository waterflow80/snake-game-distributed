package common;

public class SnakeLinkedList {
	private Element head;
	private char direction = 'R'; // The direction where the snake is heading

	public SnakeLinkedList() {
		super();
	}

	public SnakeLinkedList(Element head) {
		super();
		this.head = head;
	}

	public Element getHead() {
		return head;
	}

	public void setHead(Element head) {
		this.head = head;
	}
	
	public boolean isEmpty() {
		return (getSize() == 0);
	}
	
	
	
	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	/**
	 * Add the given node a the tail of the list.
	 * If the list is empty, create a new list and add the node*/
	public void addLast(Element node) {
		if (this.head == null) {
			head = node;
			return;
		}
		Element temp = head;
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
		Element temp = head;
		while (temp.getNext() != null) {
			sz++;
			temp = temp.getNext();
		}
			
		return sz;
	}
	
	
	
	/**
	 * Return the last part of the linked list (The Tail)*/
	public Element getTail() {
		if(head == null)
			return null;
		
		Element temp = head;
		while (temp.getNext() != null)
			temp = temp.getNext();
		return temp;
	}
	
}
