package common;

public class SnakeUtils {
	
	
	/**
	 * Perform the desired move on the snake in the given direction*/
	public static void move(SnakeLinkedList snake, char direction, int unitSize) {
		if (snake.isEmpty()) {
			return;
		
		}
		
		//moving the body parts except the head
		Element temp = snake.getTail(); // pointing at the tail (the last element)
		while (temp.getPrevious() != null) {
			// the coordinates of a node will take the ones of the previous (the closest to the head)
			/*
			 * temp.getPrevious().setX(temp.getX()); temp.getPrevious().setY(temp.getY());
			 */
			temp.setX(temp.getPrevious().getX());
			temp.setY(temp.getPrevious().getY());
			temp = temp.getPrevious(); // Browsing from tail to head
		}
		
		// Moving the head
		temp = snake.getHead(); // pointing at the head. (Using the same object 'temp' to save memory)
		
		switch(direction) {
		case 'U':
			temp.setY(temp.getY() - unitSize); 
			break;
		case 'D':
			temp.setY(temp.getY() + unitSize); 
			break;
		case 'R':
			temp.setX(temp.getX() + unitSize);
			break;
		case 'L':
			temp.setX(temp.getX() - unitSize);
			break;
		}
	}
	
	/**
	 * Check whether the snake's head collides with its body or with the border
	 * of the screen*/
	public static boolean isSnakeCollides(SnakeLinkedList snake, Grid screen) {
		// checks if head collides with body
		Element head = snake.getHead();
		if(head == null)
			return false;
		Element temp2 = head.getNext();
		while (temp2 != null) {
			if (temp2.getX() == head.getX() && temp2.getY() == head.getY())
				return true;
			temp2 = temp2.getNext();
		}
		
		// checks if the head touches the left border
		if (head.getX() <0) {
			return true;
		}
		// checks if the head touches the right border
		if (head.getX() > screen.getWIDTH()) {
			return true;
		}
		// checks if head touches top border
		if (head.getY() < 0) {
			return true;
		}
		// checks if head touches bottom border
		if (head.getY() > screen.getHEIGHT()) {
			return true;
		}
		
		// No collision detected
		return false;
	}
	
	/**
	 * Add a new part to the snake after the tail depending on its current position*/
	public void addPart() {
		//TODO
	}
}
