package common;

import java.awt.Color;

public class Element {
	private int x;
	private int y;
	private Color color;
	private Element next; // a 'pointer' to the next node
	private Element previous =null; // a 'pointer' to the previous node
	
	
	public Element() {
		super();
	}
	
	public Element(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
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


	public Element getNext() {
		return next;
	}


	public void setNext(Element next) {
		this.next = next;
	}
	
	
	
	public Element getPrevious() {
		return previous;
	}

	public void setPrevious(Element previous) {
		System.out.println("Setting the previous: " + previous);
		this.previous = previous;
		System.out.println("**Previous: " + this.previous);
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
