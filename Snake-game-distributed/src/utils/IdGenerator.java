package utils;

public class IdGenerator {
	public static int id = -1;
	/**
	 * Generate a unique id*/
	public static int getId() {
		id += 1;
		return id;
	}
}	
